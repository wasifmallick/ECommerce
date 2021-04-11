package com.creativedeveloper.ecommerce.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.creativedeveloper.ecommerce.data.local.entity.CartEntity;
import com.creativedeveloper.ecommerce.data.local.entity.ProductEntity;

import java.util.List;

@Dao
public interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertCartProducts(List<CartEntity> cartEntities);

    /*@Query("SELECT * FROM CartEntity where id = :id")
    ProductEntity getCartProductById(Long id);*/

 /*   @Query("SELECT * FROM CartEntity")
    ProductEntity getCartProducts();*/
}
