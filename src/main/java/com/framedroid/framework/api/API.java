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
//        if (json == null) {
//            if (Config.getBool("api.log")) Log.i("API.log", "mUrl: " + mUrl + path + "; Entity: null");
//            return;
//        }
//        if (Config.getBool("api.log")) Log.i("API.log", "mUrl: " + mUrl + path + "; Entity: " + json.toString());
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
