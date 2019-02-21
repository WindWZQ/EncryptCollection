package com.wzq.encrypt.test.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wzq.encrypt.R;
import com.wzq.encrypt.digest.Md5Util;
import com.wzq.encrypt.digest.Sha1Util;

public class MD5Fragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_md5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1 = view.findViewById(R.id.btn_1);
        Button btn2 = view.findViewById(R.id.btn_2);

        btn1.setOnClickListener(v -> {
            String str1 = "0";
            String str2 = "0123456789abcdefghijklmnopqrxtuvwxyz~!@#$%^&*()_+";
            String str3 = "贾师傅电力建设卡迪夫离开家现在穿从天使";

            Log.i(TAG, Md5Util.encode(str1));
            Log.i(TAG, Md5Util.encode(str2));
            Log.i(TAG, Md5Util.encode(str3));
        });
        btn2.setOnClickListener(v -> {
            String str1 = "0";
            String str2 = "0123456789abcdefghijklmnopqrxtuvwxyz~!@#$%^&*()_+";
            String str3 = "贾师傅电力建设卡迪夫离开家现在穿从天使";

            Log.i(TAG, Sha1Util.encode(str1));
            Log.i(TAG, Sha1Util.encode(str2));
            Log.i(TAG, Sha1Util.encode(str3));
        });
    }
}
