package com.framedroid.framework.api;

import android.content.Context;
import android.util.Log;

import com.framedroid.framework.Config;
import com.framedroid.framework.FD;
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


public abstract class API extends FD {
    private static Context mContext;
    private static String mUrl;
    private static String apiKey;

    public static void init(Context context, String url, String apiKey) {
        mContext = context;
        mUrl = url;
        API.apiKey = apiKey;
    }

    protected static AsyncHttpClient getClient() {
        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
        client.setTimeout(Config.apiClientTimeout);
        return client;
    }

    protected static RequestHandle get(String path, ResponseHandler responseHandler) {
        logRequest(path, new JSONObject());
        AsyncHttpClient client = getClient();
        return client.get(mContext, mUrl + path + getApiKey(), responseHandler);
    }

    protected static RequestHandle post(String path, JSONObject json, ResponseHandler responseHandler) {
        logRequest(path, json);
        AsyncHttpClient client = getClient();
        return client.post(mContext, mUrl + path + getApiKey(), json != null ? convertJsonToEntity(json) : null, "application/json", responseHandler);
    }

    protected static RequestHandle put(String path, JSONObject json, ResponseHandler responseHandler) {
        logRequest(path, json);
        AsyncHttpClient client = getClient();
        return client.put(mContext, mUrl + path + getApiKey(), json != null ? convertJsonToEntity(json) : null, "application/json", responseHandler);
    }

    protected static RequestHandle delete(String path, ResponseHandler responseHandler) {
        logRequest(path, new JSONObject());
        AsyncHttpClient client = getClient();
        return client.delete(mContext, mUrl + getApiKey(), responseHandler);
    }

    private static void logRequest(String path, JSONObject json) {
        if (json == null) {
            if (Config.apiLogRequest) Log.i("API.log", "mUrl: " + mUrl + path + "; Entity: null");
            return;
        }
        if (Config.apiLogRequest) Log.i("API.log", "mUrl: " + mUrl + path + "; Entity: " + json.toString());
    }


    protected static HttpEntity convertJsonToEntity(JSONObject jsonObject) {
        HttpEntity httpEntity = null;
        try {
            httpEntity = new StringEntity(jsonObject.toString(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpEntity;
    }

    public static String getApiKey() {
        return "?" + Config.apiKeyParameterName + "=" + apiKey;
    }
}
