package com.creativedeveloper.ecommerce.ui.productlisting;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.creativedeveloper.ecommerce.R;
import com.creativedeveloper.ecommerce.data.local.entity.ProductEntity;
import com.creativedeveloper.ecommerce.databinding.ProductListingFragmentBinding;
import com.creativedeveloper.ecommerce.factory.ViewModelFactory;
import com.creativedeveloper.ecommerce.ui.base.BaseFragment;
import com.creativedeveloper.ecommerce.ui.base.recyclerview.RecyclerItemClickListener;
import com.creativedeveloper.ecommerce.ui.base.recyclerview.RecyclerViewPaginator;
import com.creativedeveloper.ecommerce.ui.productlisting.adapter.ProductVerticalListAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ProductListFragment extends BaseFragment implements RecyclerItemClickListener.OnRecyclerViewItemClickListener {
    ProductListViewModel productListViewModel;
    private ProductListingFragmentBinding binding;
    @Inject
    ViewModelFactory viewModelFactory;
    List<ProductEntity> itemListItems;
    private ProductVerticalListAdapter productVerticalListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        initialiseViewModel();
    }

    private void initialiseViewModel() {

        productListViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductListViewModel.class);

        productListViewModel.getProductsLiveData().observe(this, resource -> {
            if (resource.isLoading()) {

            } else if (!resource.data.isEmpty()) {
                updateProductsList(resource.data);

            } else handleErrorResponse("");
        });
    }

    private void initialiseView() {
        itemListItems = productListViewModel.loadJSONFromAsset(getActivity()).getItemList();
        productListViewModel.insertData(itemListItems);

        productVerticalListAdapter = new ProductVerticalListAdapter(activity);
        binding.rvVerticalGrid.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        binding.rvVerticalGrid.setAdapter(productVerticalListAdapter);
        binding.rvVerticalGrid.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), this));

        /* RecyclerViewPaginator to handle pagination */
        binding.rvVerticalGrid.addOnScrollListener(new RecyclerViewPaginator(binding.rvVerticalGrid) {
            @Override
            public boolean isLastPage() {
                return productListViewModel.isLastPage();
            }

            @Override
            public void loadMore(Long page) {
                productListViewModel.loadMoreProducts(page);
            }

            @Override
            public void loadFirstData(Long page) {
                //displayLoader();
                productListViewModel.loadMoreProducts(page);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_productlisting, container, false);
        initialiseView();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void handleErrorResponse(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View parentView, View childView, int position) {

    }

    private void updateProductsList(List<ProductEntity> productEntities) {

        productVerticalListAdapter.setItems(productEntities);
    }
}
