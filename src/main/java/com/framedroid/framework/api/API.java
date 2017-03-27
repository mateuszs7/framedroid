package com.framedroid.framework.api;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by mateusz on 10.03.2017.
 */


public abstract class API {
    private static Context mContext;
    private static String mUrl;

    public static void init(Context context, String url) {
        mContext = context;
        mUrl = url;
    }

    protected static AsyncHttpClient getClient() {
        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
        client.setTimeout(20000);
        return client;
    }

    protected static RequestHandle get(String path, ResponseHandler responseHandler) {
        return get(path, responseHandler, false);
    }

    protected static RequestHandle get(String path, ResponseHandler responseHandler, boolean simpleToken) {
        logRequest(path, new JSONObject());
        AsyncHttpClient client = getClient();
        addHeaders(client, path, "", simpleToken);
        return client.get(mContext, mUrl + path, responseHandler);
    }

    protected static RequestHandle post(String path, ResponseHandler responseHandler) {
        return post(path, null, responseHandler, false);
    }

    protected static RequestHandle post(String path, JSONObject json, ResponseHandler responseHandler) {
        return post(path, json, responseHandler, false);
    }

    protected static RequestHandle delete(String path, ResponseHandler responseHandler) {
        logRequest(path, new JSONObject());
        AsyncHttpClient client = getClient();
        addHeaders(client, path, "", false);
        return client.delete(mContext, mUrl + path, responseHandler);
    }

    protected static RequestHandle post(String path, JSONObject json, ResponseHandler responseHandler, boolean simpleToken) {
        logRequest(path, json);
        AsyncHttpClient client = getClient();
        addHeaders(client, path, json != null ? json.toString() : null, simpleToken);
        return client.post(mContext, mUrl + path, json != null ? convertJsonToEntity(json) : null, "application/json", responseHandler != null ? responseHandler : simpleResponseHandler);
    }

    private static void logRequest(String path, JSONObject json) {
        if (json == null) {
            if (Config.getBool("api.log")) Log.i("Xsneak.API.log", "mUrl: " + mUrl + path + "; Entity: null");
            return;
        }
        if (Config.getBool("api.log")) Log.i("Xsneak.API.log", "mUrl: " + mUrl + path + "; Entity: " + json.toString());
    }

    protected static JSONObject getSimpleJson(String key, String value) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(key, value);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    protected static HttpEntity convertJsonToEntity(JSONObject jsonObject) {
        HttpEntity httpEntity = null;
        try {
            httpEntity = new StringEntity(jsonObject.toString(), "UTF-8");

        } catch (Exception e) {
//            Dialogs.problem(activity, true);
            e.printStackTrace();
        }
        return httpEntity;
    }

    private static void addHeaders(AsyncHttpClient client, String path, String data, boolean simpleToken) {
        if (simpleToken) {
            String token =  Config.hashToSha256(data + Config.getParameter("api.token"));
            if (Config.getBool("api.log")) Log.i("token", token);
            client.addHeader("X-token", token);
            return;
        }

        if (Config.getPreference(Config.PUBLIC_KEY) == null || Config.getPreference(Config.PRIVATE_KEY) == null) {
            if (Config.getBool("api.log")) Log.i("Xsneak.API.log", "hmac ommited, publicKey or privateKey is null");
            return;
        }

        Log.i("X-public", Config.getPreference(Config.PUBLIC_KEY));
        Log.i("X-hmac", calcHmac(Config.getParameter("api.path") + path + data));


        client.addHeader("X-public", Config.getPreference(Config.PUBLIC_KEY));
//        client.addHeader("X-hmac", calcHmac(Config.getParameter("api.path") + path + data));
        client.addHeader("X-hmac", "skip3214");
    }

    private static String calcHmac(String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec sk = new SecretKeySpec(Config.getPreference(Config.PRIVATE_KEY).getBytes(), mac.getAlgorithm());
            mac.init(sk);
            byte[] result = mac.doFinal((Config.getPreference(Config.PUBLIC_KEY) + data).getBytes());

            StringBuffer hash = new StringBuffer();

            for (int i = 0; i < result.length; i++) {
                String hex = Integer.toHexString(0xFF & result[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            return hash.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ResponseHandler simpleResponseHandler = new ResponseHandler() {
        @Override
        public void onSuccess(JSONObject jsonObject) {

        }

        @Override
        public void onFailure(JSONObject jsonObject) {

        }
    };

}
