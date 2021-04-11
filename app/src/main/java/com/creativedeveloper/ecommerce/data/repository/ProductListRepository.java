package com.creativedeveloper.ecommerce.data.repository;

import android.support.annotation.NonNull;

import com.creativedeveloper.ecommerce.data.Resource;
import com.creativedeveloper.ecommerce.data.local.dao.ProductDao;
import com.creativedeveloper.ecommerce.data.local.entity.ProductEntity;
import com.creativedeveloper.ecommerce.data.remote.NetworkBoundResource;
import com.creativedeveloper.ecommerce.data.remote.api.ProductApiService;
import com.creativedeveloper.ecommerce.data.remote.model.ProductListingResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Singleton
public class ProductListRepository {
    private ProductDao productDao;
    private ProductApiService productApiService;

    public ProductListRepository(ProductDao productDao,
                                 ProductApiService productApiService) {
        this.productDao = productDao;
        this.productApiService = productApiService;
    }

    public Observable<Resource<List<ProductEntity>>> loadProducts(Long page,
                                                                  String type) {
        return new NetworkBoundResource<List<ProductEntity>, ProductListingResponse>() {

            @Override
            protected void saveCallResult(@NonNull ProductListingResponse item) {

                List<ProductEntity> productEntities = new ArrayList<>();

                for (ProductEntity productEntity : item.getItemList()) {

                    // ProductEntity storedproductEntity = productDao.getProducts();
                    /*if(storedproductEntity == null) productEntity.setCategoryTypes(Arrays.asList(type));
                    else {
                        List<String> categories = storedproductEntity.getCategoryTypes();
                        categories.add(type);
                        productEntity.setCategoryTypes(categories);
                    }

                    productEntity.setPage(item.getPage());
                    productEntity.setTotalPages(item.getTotalPages());*/
                    productEntities.add(productEntity);
                }

                productDao.insertProducts(productEntities);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<List<ProductEntity>> loadFromDb() {
                List<ProductEntity> productEntities = productDao.getProducts();
                if (productEntities == null || productEntities.isEmpty()) {
                    return Flowable.empty();
                }
                return Flowable.just(productEntities);
            }

            /*@NonNull
            @Override
            protected Observable<Resource<ProductListingResponse>> createCall() {
                return productApiService.fetchProductByType(type, page)
                        .flatMap(productApiResponse -> Observable.just(productApiResponse == null
                                ? Resource.error("", new ProductListingResponse())
                                : Resource.success(productApiResponse)));
            }*/
        }.getAsObservable();
    }


    public void insertData(List<ProductEntity> itemEntities) {
        List<ProductEntity> productEntities = new ArrayList<>();

        for (ProductEntity productEntity : itemEntities) {

            // ProductEntity storedproductEntity = productDao.getProducts();
                    /*if(storedproductEntity == null) productEntity.setCategoryTypes(Arrays.asList(type));
                    else {
                        List<String> categories = storedproductEntity.getCategoryTypes();
                        categories.add(type);
                        productEntity.setCategoryTypes(categories);
                    }

                    productEntity.setPage(item.getPage());
                    productEntity.setTotalPages(item.getTotalPages());*/
            productEntities.add(productEntity);
        }

        productDao.insertProducts(productEntities);
    }

/*
    public Observable<Resource<ProductEntity>> fetchProductDetails(Long productId) {
        return new NetworkBoundResource<ProductEntity, ProductEntity>() {
            @Override
            protected void saveCallResult(@NonNull ProductEntity item) {
                ProductEntity productEntity = productDao.getProductById(item.getId());
                if(productEntity == null) productDao.insertProduct(item);
                else productDao.updateProduct(item);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<ProductEntity> loadFromDb() {
                ProductEntity productEntity = productDao.getProductById(productId);
                if(productEntity == null) return Flowable.empty();
                return Flowable.just(productEntity);
            }

            @NonNull
            @Override
            protected Observable<Resource<ProductEntity>> createCall() {
                String id = String.valueOf(productId);
                return Observable.combineLatest(productApiService.fetchProductDetail(id),
                        productApiService.fetchProductVideo(id),
                        productApiService.fetchCastDetail(id),
                        productApiService.fetchSimilarProduct(id, 1),
                        productApiService.fetchProductReviews(id),
                        (productEntity, videoResponse, creditResponse, productApiResponse, reviewApiResponse) -> {

                            if(videoResponse != null) {
                                productEntity.setVideos(videoResponse.getResults());
                            }

                            if(creditResponse != null) {
                                productEntity.setCrews(creditResponse.getCrew());
                                productEntity.setCasts(creditResponse.getCast());
                            }

                            if(productApiResponse != null) {
                                productEntity.setSimilarProducts(productApiResponse.getResults());
                            }

                            if(reviewApiResponse != null) {
                                productEntity.setReviews(reviewApiResponse.getResults());
                            }
                            return Resource.success(productEntity);
                        });
            }
        }.getAsObservable();
    }
*/


   /* public Observable<Resource<List<ProductEntity>>> searchProducts(Long page,
                                                                String query) {
        return new NetworkBoundResource<List<ProductEntity>, ProductListingResponse>() {

            @Override
            protected void saveCallResult(@NonNull ProductListingResponse item) {
                List<ProductEntity> productEntities = new ArrayList<>();
                for(ProductEntity productEntity : item.getResults()) {

                    ProductEntity storedProductEntity = productDao.getProductById(productEntity.getId());
                    if(storedProductEntity == null) productEntity.setCategoryTypes(Arrays.asList(query));
                    else {
                        List<String> categories = storedProductEntity.getCategoryTypes();
                        categories.add(query);
                        productEntity.setCategoryTypes(categories);
                    }

                    productEntity.setPage(item.getPage());
                    productEntity.setTotalPages(item.getTotalPages());
                    productEntities.add(productEntity);
                }
                productDao.insertProducts(productEntities);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<List<ProductEntity>> loadFromDb() {
                List<ProductEntity> productEntities = productDao.getProductsByPage(page);
                if(productEntities == null || productEntities.isEmpty()) {
                    return Flowable.empty();
                }
                return Flowable.just(AppUtils.getProductsByType(query, productEntities));
            }

            @NonNull
            @Override
            protected Observable<Resource<ProductListingResponse>> createCall() {
                return productApiService.searchProductsByQuery(query, "1")
                        .flatMap(productApiResponse -> Observable.just(productApiResponse == null
                                ? Resource.error("", new ProductListingResponse())
                                : Resource.success(productApiResponse)));
            }
        }.getAsObservable();
    }*/

}
