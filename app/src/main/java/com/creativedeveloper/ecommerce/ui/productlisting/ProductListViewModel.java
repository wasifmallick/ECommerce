package com.creativedeveloper.ecommerce.ui.productlisting;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.creativedeveloper.ecommerce.data.Resource;
import com.creativedeveloper.ecommerce.data.local.dao.ProductDao;
import com.creativedeveloper.ecommerce.data.local.entity.ProductEntity;
import com.creativedeveloper.ecommerce.data.remote.api.ProductApiService;
import com.creativedeveloper.ecommerce.data.remote.model.ProductListingResponse;
import com.creativedeveloper.ecommerce.data.repository.ProductListRepository;
import com.creativedeveloper.ecommerce.ui.base.BaseViewModel;
import com.creativedeveloper.ecommerce.utils.SessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

public class ProductListViewModel extends BaseViewModel {

    @Inject
    public ProductListViewModel( ProductDao productDao, ProductApiService productApiService) {
        productListRepository = new ProductListRepository(productDao, productApiService);

    }

    private ProductListRepository productListRepository;
    private MutableLiveData<Resource<List<ProductEntity>>> productLiveData = new MutableLiveData<>();

    public ProductListingResponse loadJSONFromAsset(Context context) {
        String json = null;
        ProductListingResponse productListingResponse = new ProductListingResponse();
        try {
            InputStream is = context.getAssets().open("productListing.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            productListingResponse = gson.fromJson(json, ProductListingResponse.class);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return productListingResponse;
    }

    public void loadMoreProducts(Long currentPage) {
        productListRepository.loadProducts(currentPage, "")
                .doOnSubscribe(disposable -> addToDisposable(disposable))
                .subscribe(resource -> getProductsLiveData().postValue(resource));
    }

    public boolean isLastPage() {
        return productLiveData.getValue() != null &&
                !productLiveData.getValue().data.isEmpty() ?
                productLiveData.getValue().data.get(0).isLastPage() :
                false;
    }

    public MutableLiveData<Resource<List<ProductEntity>>> getProductsLiveData() {
        return productLiveData;
    }

    public  void insertData(List<ProductEntity> itemEntities){
        productListRepository.insertData(itemEntities);
    }
}
