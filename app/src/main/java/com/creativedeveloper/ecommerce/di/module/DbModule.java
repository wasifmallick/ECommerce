package com.creativedeveloper.ecommerce.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.creativedeveloper.ecommerce.data.local.AppDatabase;
import com.creativedeveloper.ecommerce.data.local.dao.ProductDao;
import com.creativedeveloper.ecommerce.utils.SessionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    AppDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, "Ecommerce.db")
                .allowMainThreadQueries().build();
    }
    @Provides
    @Singleton
    ProductDao provideProductDao(@NonNull AppDatabase appDatabase) {
        return appDatabase.productListDao();
    }
    @Provides
    @Singleton
    SessionManager provideSessionManager(@NonNull SessionManager sessionManager) {
        return sessionManager;
    }
}
