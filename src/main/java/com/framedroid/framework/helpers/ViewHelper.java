package com.framedroid.framework.helpers;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mateu on 23/03/2017.
 */

public class ViewHelper {

    public void multiClick(Activity activity, View.OnClickListener listener, int... resIds) {
        setMultiClick(new ViewHolder(activity), listener, resIds);
    }

    public void multiClick(View view, View.OnClickListener listener, int... resIds) {
        setMultiClick(new ViewHolder(view), listener, resIds);
    }

    private void setMultiClick(ViewHolder viewHolder, View.OnClickListener listener, int... resIds) {
        for (int resId : resIds) {
            viewHolder.findView(resId).setOnClickListener(listener);
        }
    }

    public String getValue(Activity activity, int resId) {
        View view = activity.findViewById(resId);

        if (view == null)
            throw new RuntimeException("Can't find view to get value.");

        TextView textView = (TextView) view;

        if (textView == null)
            throw new RuntimeException("Can't cast view to TextView.");

        return textView.getText().toString();

    }

    public static class ViewHolder {
        Activity activity;
        View view;

        public ViewHolder(Activity activity) {
            this.activity = activity;
        }

        public ViewHolder(View view) {
            this.view = view;
        }

        public View findView(int resId) {
            if (activity != null)
                return activity.findViewById(resId);
            if (view != null)
                return view.findViewById(resId);
            return null;
        }
    }
}
