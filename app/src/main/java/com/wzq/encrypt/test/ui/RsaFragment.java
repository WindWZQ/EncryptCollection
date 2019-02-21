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
import com.wzq.encrypt.rsa.RsaUtil;
import com.wzq.encrypt.test.Constant;
import com.wzq.encrypt.test.base.BaseFragment;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

import javax.crypto.Cipher;

public class RsaFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rsa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1 = view.findViewById(R.id.btn_1);
        Button btn2 = view.findViewById(R.id.btn_2);
        Button btn3 = view.findViewById(R.id.btn_3);

        btn1.setOnClickListener(v -> {
            KeyPair keyPair = RsaUtil.generateKeyPair(2048);
            String priKeyStr = Base64.encodeToString(keyPair.getPrivate().getEncoded(), Base64.DEFAULT);
            String pubKeyStr = Base64.encodeToString(keyPair.getPublic().getEncoded(), Base64.DEFAULT);
            logger(priKeyStr);
            logger(pubKeyStr);

            KeyPair newKeyPair = new KeyPair(RsaUtil.key2PublicKey(pubKeyStr), RsaUtil.key2PrivateKey(priKeyStr));
            logger(newKeyPair.getPrivate().toString());
            logger(newKeyPair.getPublic().toString());
        });
        btn2.setOnClickListener(v -> {
            new Thread(() -> {
                pri2pub(Constant.s1);
                pri2pub(Constant.s2);
                pri2pub(Constant.s3);
            }).start();
        });
        btn3.setOnClickListener(v -> {
            new Thread(() -> {
                pub2pri(Constant.s1);
                pub2pri(Constant.s2);
                pub2pri(Constant.s3);
            }).start();
        });
    }

    private void pri2pub(String str) {
        logger("rsa private en public de");
        KeyPair keyPair = RsaUtil.generateKeyPair(2048);
        byte en[] = RsaUtil.processSection(str.getBytes(StandardCharsets.UTF_8), keyPair.getPrivate(), 2048, Cipher.ENCRYPT_MODE);
        String enStr = Base64.encodeToString(en, Base64.DEFAULT);
        logger("en: " + enStr);

        byte de[] = RsaUtil.processSection(en, keyPair.getPublic(), 2048, Cipher.DECRYPT_MODE);
        logger("de: " + new String(de, StandardCharsets.UTF_8));
    }

    private void pub2pri(String str) {
        logger("rsa public en private de");
        KeyPair keyPair = RsaUtil.generateKeyPair(2048);
        byte en[] = RsaUtil.processSection(str.getBytes(StandardCharsets.UTF_8), keyPair.getPublic(), 2048, Cipher.ENCRYPT_MODE);
        String enStr = Base64.encodeToString(en, Base64.DEFAULT);
        logger("en: " + enStr);

        byte de[] = RsaUtil.processSection(en, keyPair.getPrivate(), 2048, Cipher.DECRYPT_MODE);
        logger("de: " + new String(de, StandardCharsets.UTF_8));
    }

}
