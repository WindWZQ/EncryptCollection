package com.wzq.encrypt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wzq.encrypt.test.ui.AesFragment;
import com.wzq.encrypt.test.ui.DesFragment;
import com.wzq.encrypt.test.ui.MD5Fragment;
import com.wzq.encrypt.test.ui.RsaFragment;
import com.wzq.encrypt.test.ui.SmFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ViewPager vpEncrypt = findViewById(R.id.vp_encrypt);
        TabLayout tabLayout = findViewById(R.id.tab_encrypt);
        tabLayout.setupWithViewPager(vpEncrypt);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new DesFragment());
        fragmentList.add(new AesFragment());
        fragmentList.add(new RsaFragment());
        fragmentList.add(new MD5Fragment());
        fragmentList.add(new SmFragment());

        List<String> titleList = new ArrayList<>();
        titleList.add("DES");
        titleList.add("AES");
        titleList.add("RSA");
        titleList.add("MD5");
        titleList.add("SM");

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        vpEncrypt.setAdapter(myPagerAdapter);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList;
        private List<String> mTitleList;

        private MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
            super(fm);
            this.mFragmentList = fragmentList;
            this.mTitleList = titleList;
        }

        @Override
        public Fragment getItem(int i) {
            return mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }

}
