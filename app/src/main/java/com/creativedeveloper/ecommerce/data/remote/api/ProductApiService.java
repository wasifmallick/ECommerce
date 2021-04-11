package com.creativedeveloper.ecommerce.data.remote.api;

import com.creativedeveloper.ecommerce.data.local.entity.ProductEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ProductApiService {


    @GET("item-list")
    Observable<ProductEntity> fetchProductsList();
}
