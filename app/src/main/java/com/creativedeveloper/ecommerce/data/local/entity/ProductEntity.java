package com.creativedeveloper.ecommerce.data.local.entity;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = ("id"))
public class ProductEntity implements Parcelable {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("itemRate")
    @Expose
    private int itemRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemRate() {
        return itemRate;
    }

    public void setItemRate(int itemRate) {
        this.itemRate = itemRate;
    }

    protected ProductEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        price = in.readDouble();
        name = in.readString();
        description = in.readString();
        itemRate = in.readInt();
    }

    public ProductEntity() {

    }

    public static final Creator<ProductEntity> CREATOR = new Creator<ProductEntity>() {
        @Override
        public ProductEntity createFromParcel(Parcel in) {
            return new ProductEntity(in);
        }

        @Override
        public ProductEntity[] newArray(int size) {
            return new ProductEntity[size];
        }
    };

    public boolean isLastPage() {
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeValue(this.itemRate);
        dest.writeDouble(this.price);
        dest.writeString(this.name);
        dest.writeString(this.description);
    }
}
