package com.wzq.encrypt.test.base;

import android.support.v4.app.Fragment;
import android.util.Log;

public abstract class BaseFragment extends Fragment {
    protected String TAG = this.getClass().getSimpleName();

    protected void logger(String content) {
        Log.i(TAG, content);
    }

}
