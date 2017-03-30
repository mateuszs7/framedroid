package com.framedroid.framework.api;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Mateusz - Limtel on 2016-01-26.
 */
public abstract class ResponseHandler extends AsyncHttpResponseHandler {
    private static final String TAG = "Xsneak.ResponseHandle";

    public abstract void onSuccess(JSONObject jsonObject);
    public abstract void onFailure(JSONObject jsonObject);

    public void onFailure() {
        onFailure(new JSONObject());
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            if (responseBody == null) {
                onFailure();
                return;
            }

            JSONObject jsonObject = new JSONObject(new String(responseBody));

//            if(Config.getBool("api.log"))
//                Log.i(TAG, jsonObject.toString());
            if (jsonObject.optString("status").equals("ok")) {
                onSuccess(jsonObject);
                return;
            }

        } catch (JSONException e) {
            onFailure();
            e.printStackTrace();
        }

        onFailure();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        JSONObject jsonObject = null;
        if(responseBody != null){
            try {
                jsonObject = new JSONObject(new String(responseBody));
//                if(Config.getBool("api.log"))
//                    Log.i(TAG, jsonObject.toString());
                onFailure(jsonObject);
            }
            catch (JSONException e){
                onFailure();
                e.printStackTrace();
                return;
            }
        }
        if(statusCode == 0) {
            onFailure();
        }
        else
            onFailure();

        error.printStackTrace();
    }


}
