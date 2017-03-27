package com.framedroid.framework.api;

import android.widget.Toast;

import com.xsneak.Config;
import com.xsneak.R;

import org.json.JSONObject;

/**
 * Created by mateu on 16/03/2017.
 */

public abstract class SimpleResponseHandler extends ResponseHandler {
    @Override
    public void onFailure(JSONObject jsonObject) {
        Toast.makeText(Config.getContext(), Config.getString(R.string.error_something_wrong), Toast.LENGTH_SHORT).show();
    }
}