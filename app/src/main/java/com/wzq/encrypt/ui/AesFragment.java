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
import com.wzq.encrypt.aes.AesUtil;

import javax.crypto.Cipher;

public class AesFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1 = view.findViewById(R.id.btn_1);

        btn1.setOnClickListener(v -> {
            String str = "0123456789abcdefghijklmnopqrstuvwxyz";
            Log.i(TAG, "cbc src: " + str);

            byte en[] = AesUtil.process(str.getBytes(), AesUtil.DEFAULT_KEY, AesUtil.DEFAULT_IV, Cipher.ENCRYPT_MODE);
            String enStr = Base64.encodeToString(en, Base64.DEFAULT);
            Log.i(TAG, "en: " + enStr);

            byte de[] = AesUtil.process(en, AesUtil.DEFAULT_KEY, AesUtil.DEFAULT_IV, Cipher.DECRYPT_MODE);
            Log.i(TAG, "de: " + new String(de));
        });

    }
}
