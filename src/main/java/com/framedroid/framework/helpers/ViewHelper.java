package com.framedroid.framework.helpers;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mateu on 23/03/2017.
 */

public class ViewHelper {
    ViewHolder mCurrentView;

    public void setCurrentView(ViewHolder mCurrentView) {
        this.mCurrentView = mCurrentView;
    }

    public void multiClick(Activity activity, View.OnClickListener listener, int... resIds) {
            setMultiClick(new ViewHolder(activity), listener, resIds);
        }

    public void multiClick(View view, View.OnClickListener listener, int... resIds) {
        setMultiClick(new ViewHolder(view), listener, resIds);
    }

    public void click(View view, View.OnClickListener listener, int resId) {
        multiClick(view, listener, resId);
    }
    public void click(Activity activity, View.OnClickListener listener, int resId) {
        multiClick(activity, listener, resId);
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


    public static ViewWork list(Activity activity, int...resIds) {
        return list(new ViewHolder(activity), resIds);
    }

    public static ViewWork list(View view, int...resIds) {
        return list(new ViewHolder(view), resIds);
    }

    private static ViewWork list(ViewHolder viewHolder, int...resIds) {
        return new ViewWork(viewHolder, resIds);
    }

    public static class ViewWork {
        private View[] views;

        public ViewWork(ViewHolder viewHolder, int[] resIds) {
            views = new View[resIds.length];
            for (int i = 0; i < resIds.length; i++) {
                views[i] = viewHolder.findView(resIds[i]);
            }
        }

        public ViewWork set(Format format) {
            for (View view : views) {
                format.make(view);
            }
            return this;
        }
    }

    public interface Format<T> {
        void make(T view);
    }

    /**
     * Set text to TextView
     */
    public ViewHelper text(@IdRes int resId, String text) {
        checkCurrentView();
        TextView textView = (TextView) mCurrentView.findView(resId);
        textView.setText(text);
        return this;
    }

    /**
     * Get text from TextView
     */
    public CharSequence text(@IdRes int resId) {
        checkCurrentView();
        TextView textView = (TextView) mCurrentView.findView(resId);
        return textView.getText();
    }


    private void checkCurrentView() {
        if (mCurrentView == null)
            throw new RuntimeException("You have to initialize view like FD.view(this) to call this method. (i.e. FD.view(parent).text(R.id.myTextView); )");
    }

    public <T extends View> T get(@IdRes int resId) {
        checkCurrentView();
        return (T)mCurrentView.findView(resId);
    }
}
