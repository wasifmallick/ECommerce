package com.creativedeveloper.ecommerce.di.module;

import com.creativedeveloper.ecommerce.ui.login.LoginFragment;
import com.creativedeveloper.ecommerce.ui.productlisting.ProductListFragment;
import com.creativedeveloper.ecommerce.ui.splash.SplashFragment;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract SplashFragment contributeSplashFragment();

    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();

    @ContributesAndroidInjector
    abstract ProductListFragment contributeProductListFragment();
}
