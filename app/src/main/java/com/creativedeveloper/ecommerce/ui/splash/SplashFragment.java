package com.creativedeveloper.ecommerce.ui.splash;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativedeveloper.ecommerce.R;
import com.creativedeveloper.ecommerce.databinding.SplashFragmentBinding;
import com.creativedeveloper.ecommerce.factory.ViewModelFactory;
import com.creativedeveloper.ecommerce.ui.base.BaseFragment;
import com.creativedeveloper.ecommerce.utils.NavigationUtils;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class SplashFragment extends BaseFragment {
    SplashViewModel splashViewModel;
    private SplashFragmentBinding binding;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        initialiseViewModel();
    }

    private void initialiseViewModel() {
        splashViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        replaceFragment();
    }

    private void replaceFragment(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                NavigationUtils.replaceFragment(getActivity(),R.id.loginFragment);
            }
        }, 3000);
    }
}
