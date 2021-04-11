package com.creativedeveloper.ecommerce.data.local.entity;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = ("id"))
public class CartEntity implements Parcelable {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("totalprice")
    @Expose
    private double totalPrice;

    @SerializedName("size")
    @Expose
    private String size;

    @SerializedName("quantity")
    @Expose
    private int quantity;

    @SerializedName("productId")
    @Expose
    private int productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    public CartEntity() {
    }
    protected CartEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        totalPrice = in.readDouble();
        size = in.readString();
        quantity = in.readInt();
        productId = in.readInt();
    }

    public static final Parcelable.Creator<CartEntity> CREATOR = new Parcelable.Creator<CartEntity>() {
        @Override
        public CartEntity createFromParcel(Parcel in) {
            return new CartEntity(in);
        }

        @Override
        public CartEntity[] newArray(int size) {
            return new CartEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeValue(this.productId);
        dest.writeDouble(this.totalPrice);
        dest.writeString(this.size);
        dest.writeValue(this.quantity);
    }
}


