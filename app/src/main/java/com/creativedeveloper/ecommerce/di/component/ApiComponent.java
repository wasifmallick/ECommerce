package com.creativedeveloper.ecommerce.di.component;

import android.app.Application;

import com.creativedeveloper.ecommerce.AppController;
import com.creativedeveloper.ecommerce.di.module.ActivityModule;
import com.creativedeveloper.ecommerce.di.module.ApiModule;
import com.creativedeveloper.ecommerce.di.module.DbModule;
import com.creativedeveloper.ecommerce.di.module.FragmentModule;
import com.creativedeveloper.ecommerce.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApiModule.class, DbModule.class, ViewModelModule.class,
        AndroidSupportInjectionModule.class, ActivityModule.class, FragmentModule.class})
public interface ApiComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder apiModule(ApiModule apiModule);

        @BindsInstance
        Builder dbModule(DbModule dbModule);

        ApiComponent build();
    }

    void inject(AppController appController);
}



