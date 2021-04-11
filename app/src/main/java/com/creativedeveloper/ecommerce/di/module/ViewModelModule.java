package com.creativedeveloper.ecommerce.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.creativedeveloper.ecommerce.factory.ViewModelFactory;
import com.creativedeveloper.ecommerce.ui.login.LoginViewModel;
import com.creativedeveloper.ecommerce.ui.productlisting.ProductListViewModel;
import com.creativedeveloper.ecommerce.ui.splash.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    protected abstract ViewModel splashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    protected abstract ViewModel loginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel.class)
    protected abstract ViewModel productListViewModel(ProductListViewModel productListViewModel);
}
