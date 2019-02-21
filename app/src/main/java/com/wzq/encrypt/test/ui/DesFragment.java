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
import com.wzq.encrypt.des.DesUtil;
import com.wzq.encrypt.test.Constant;
import com.wzq.encrypt.test.base.BaseFragment;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;

public class DesFragment extends BaseFragment {

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

        btn1.setOnClickListener(v -> {
            cbc(Constant.s1);
            cbc(Constant.s2);
            cbc(Constant.s3);
        });
        btn2.setOnClickListener(v -> {
            ede(Constant.s1);
            ede(Constant.s2);
            ede(Constant.s3);
        });
    }

    private void cbc(String str) {
        logger("des cbc");
        byte en[] = DesUtil.process(str.getBytes(StandardCharsets.UTF_8), DesUtil.DEFAULT_KEY, DesUtil.DEFAULT_IV, Cipher.ENCRYPT_MODE);
        String enStr = Base64.encodeToString(en, Base64.DEFAULT);
        logger("en: " + enStr);

        byte de[] = DesUtil.process(en, DesUtil.DEFAULT_KEY, DesUtil.DEFAULT_IV, Cipher.DECRYPT_MODE);
        logger("de: " + new String(de, StandardCharsets.UTF_8));
    }

    private void ede(String str) {
        logger("des ede");
        byte en[] = DesUtil.processEde(str.getBytes(StandardCharsets.UTF_8), DesUtil.DEFAULT_KEY_EDE, DesUtil.DEFAULT_IV, Cipher.ENCRYPT_MODE);
        String enStr = Base64.encodeToString(en, Base64.DEFAULT);
        logger("en: " + enStr);

        byte de[] = DesUtil.processEde(en, DesUtil.DEFAULT_KEY_EDE, DesUtil.DEFAULT_IV, Cipher.DECRYPT_MODE);
        logger("de: " + new String(de, StandardCharsets.UTF_8));
    }

}
