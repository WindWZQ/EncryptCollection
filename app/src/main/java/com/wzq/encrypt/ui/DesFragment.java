package com.wzq.encrypt.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wzq.encrypt.R;
import com.wzq.encrypt.des.DesUtil;

public class DesFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_des, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1 = view.findViewById(R.id.btn_1);
        Button btn2 = view.findViewById(R.id.btn_2);
        Button btn3 = view.findViewById(R.id.btn_3);

        btn1.setOnClickListener(v -> {
            String str = "0123456789abcdefghijklmnopqrstuvwxyz";
            Log.i(TAG, "src: " + str);
            byte en[] = DesUtil.encrypt(str.getBytes(), DesUtil.DEFAULT_KEY, DesUtil.DES_ECB);
            String enStr = Base64.encodeToString(en, Base64.DEFAULT);
            Log.i(TAG, "en: " + enStr);
            byte de[] = DesUtil.decrypt(en, DesUtil.DEFAULT_KEY, DesUtil.DES_ECB);
            Log.i(TAG, "de: " + new String(de));
        });
        btn2.setOnClickListener(v -> {
            String str = "0123456789abcdefghijklmnopqrstuvwxyz";
            Log.i(TAG, "src: " + str);
            byte en[] = DesUtil.encrypt(str.getBytes(), DesUtil.DEFAULT_KEY, DesUtil.DES_CBC);
            String enStr = Base64.encodeToString(en, Base64.DEFAULT);
            Log.i(TAG, "en: " + enStr);
            byte de[] = DesUtil.decrypt(en, DesUtil.DEFAULT_KEY, DesUtil.DES_CBC);
            Log.i(TAG, "de: " + new String(de));
        });
        btn3.setOnClickListener(v -> {

        });

    }
}
