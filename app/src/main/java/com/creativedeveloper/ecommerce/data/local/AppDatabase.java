package com.creativedeveloper.ecommerce.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.creativedeveloper.ecommerce.data.local.dao.CartDao;
import com.creativedeveloper.ecommerce.data.local.dao.ProductDao;
import com.creativedeveloper.ecommerce.data.local.entity.CartEntity;
import com.creativedeveloper.ecommerce.data.local.entity.ProductEntity;

@Database(entities = {ProductEntity.class , CartEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao productListDao();
    public abstract CartDao cartDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "Ecommerce.db")
                .allowMainThreadQueries().build();
    }
}
