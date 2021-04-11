package com.creativedeveloper.ecommerce.ui.productlisting.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.creativedeveloper.ecommerce.data.local.entity.ProductEntity;
import com.creativedeveloper.ecommerce.databinding.ProductListItemVerticalBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductVerticalListAdapter extends RecyclerView.Adapter<ProductVerticalListAdapter.CustomViewHolder> {

    private Activity activity;
    private List<ProductEntity> products;

    public ProductVerticalListAdapter(Activity activity) {
        this.activity = activity;
        this.products = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductVerticalListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProductListItemVerticalBinding itemBinding = ProductListItemVerticalBinding.inflate(layoutInflater, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(itemBinding);
        return viewHolder;
    }

    public void setItems(List<ProductEntity> products) {
        int startPosition = this.products.size();
        this.products.addAll(products);
        notifyItemRangeChanged(startPosition, products.size());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public ProductEntity getItem(int position) {
        return products.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVerticalListAdapter.CustomViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    protected class CustomViewHolder extends RecyclerView.ViewHolder {

        private ProductListItemVerticalBinding binding;

        public CustomViewHolder(ProductListItemVerticalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(ProductEntity productEntity) {
            binding.lblTotalPrice.setText(String.valueOf(productEntity.getPrice()));
            binding.lblManufacturer.setText(productEntity.getName());
            binding.lblProduct.setText(productEntity.getDescription());
        }
    }
}
