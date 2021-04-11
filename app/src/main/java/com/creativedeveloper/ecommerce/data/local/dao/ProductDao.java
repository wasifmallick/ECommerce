package com.creativedeveloper.ecommerce.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.creativedeveloper.ecommerce.data.local.entity.ProductEntity;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertProducts(List<ProductEntity> products);

    @Query("SELECT * FROM ProductEntity where id = :id")
    ProductEntity getProductsById(Long id);

    @Query("SELECT * FROM ProductEntity")
    List<ProductEntity> getProducts();
}
