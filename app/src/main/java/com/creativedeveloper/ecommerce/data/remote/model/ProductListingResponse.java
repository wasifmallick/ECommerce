package com.creativedeveloper.ecommerce.data.remote.model;

import com.creativedeveloper.ecommerce.data.local.entity.ProductEntity;

import java.util.List;

public class ProductListingResponse {
	private List<ProductEntity> itemList;

	public void setItemList(List<ProductEntity> itemList){
		this.itemList = itemList;
	}

	public List<ProductEntity> getItemList(){
		return itemList;
	}

	@Override
 	public String toString(){
		return 
			"ProductListingResponse{" +
			"itemList = '" + itemList + '\'' +
			"}";
		}
}