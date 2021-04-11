package com.creativedeveloper.ecommerce.ui.main.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import com.creativedeveloper.ecommerce.AppController;
import com.creativedeveloper.ecommerce.R;
import com.creativedeveloper.ecommerce.databinding.MainActivityBinding;
import com.creativedeveloper.ecommerce.ui.base.BaseActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    private MainActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initialiseView();
    }

    private void initialiseView() {
        //  setSupportActionBar(binding.includedLayout.toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        hideToolbar();
        setStatusBarColor();
    }


    public void displayToolbar() {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Store Home");
        //binding.includedLayout.toolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //  binding.includedLayout.toolbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    private void setStatusBarColor() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.background));
    }
}
