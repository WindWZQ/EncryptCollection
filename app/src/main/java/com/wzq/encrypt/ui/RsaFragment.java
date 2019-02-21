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
import com.wzq.encrypt.rsa.RsaUtil;

import java.security.KeyPair;

import javax.crypto.Cipher;

public class RsaFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

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
            Log.i(TAG, priKeyStr);
            Log.i(TAG, pubKeyStr);

            KeyPair newKeyPair = new KeyPair(RsaUtil.key2PublicKey(pubKeyStr), RsaUtil.key2PrivateKey(priKeyStr));
            Log.i(TAG, newKeyPair.getPrivate().toString());
            Log.i(TAG, newKeyPair.getPublic().toString());
        });
        btn2.setOnClickListener(v -> {
            KeyPair keyPair = RsaUtil.generateKeyPair(2048);
            String str = "0123456789abcdefghijklmnopqrstuvwxyz";
            Log.i(TAG, "src: " + str);

            byte en[] = RsaUtil.process(str.getBytes(), keyPair.getPublic(), Cipher.ENCRYPT_MODE);
            String enStr = Base64.encodeToString(en, Base64.DEFAULT);
            Log.i(TAG, "en: " + enStr);

            byte de[] = RsaUtil.process(en, keyPair.getPrivate(), Cipher.DECRYPT_MODE);
            Log.i(TAG, "de: " + new String(de));
        });
        btn3.setOnClickListener(v -> {
            KeyPair keyPair = RsaUtil.generateKeyPair(2048);
            String str = "0123456789abcdefghijklmnopqrstuvwxyz";
            Log.i(TAG, "src: " + str);

            byte en[] = RsaUtil.process(str.getBytes(), keyPair.getPrivate(), Cipher.ENCRYPT_MODE);
            String enStr = Base64.encodeToString(en, Base64.DEFAULT);
            Log.i(TAG, "en: " + enStr);

            byte de[] = RsaUtil.process(en, keyPair.getPublic(), Cipher.DECRYPT_MODE);
            Log.i(TAG, "de: " + new String(de));
        });
    }
}
