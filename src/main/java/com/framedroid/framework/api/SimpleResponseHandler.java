package com.framedroid.framework.api;

import android.widget.Toast;


import com.framedroid.framework.FD;

import org.json.JSONObject;

/**
 * Created by mateu on 16/03/2017.
 */

public abstract class SimpleResponseHandler extends ResponseHandler {
    @Override
    public void onFailure(JSONObject jsonObject) {
        FD.toast("Something went wront");
        FD.e(jsonObject);
    }
}
