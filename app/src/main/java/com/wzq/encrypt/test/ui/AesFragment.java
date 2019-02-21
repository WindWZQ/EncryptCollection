package com.wzq.encrypt.test.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wzq.encrypt.R;
import com.wzq.encrypt.aes.AesUtil;
import com.wzq.encrypt.test.Constant;
import com.wzq.encrypt.test.base.BaseFragment;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;

public class AesFragment extends BaseFragment {
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
            cbc(Constant.s1);
            cbc(Constant.s2);
            cbc(Constant.s3);
        });
    }

    private void cbc(String str) {
        logger("aes cbc");
        byte en[] = AesUtil.process(str.getBytes(StandardCharsets.UTF_8), AesUtil.DEFAULT_KEY, AesUtil.DEFAULT_IV, Cipher.ENCRYPT_MODE);
        String enStr = Base64.encodeToString(en, Base64.DEFAULT);
        logger("en: " + enStr);

        byte de[] = AesUtil.process(en, AesUtil.DEFAULT_KEY, AesUtil.DEFAULT_IV, Cipher.DECRYPT_MODE);
        logger("de: " + new String(de, StandardCharsets.UTF_8));
    }

}
