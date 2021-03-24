package com.example.onlinemoneypay;

public class UserWishListProductModel {
    public UserWishListProductModel() {
    }
    private int product_ID_;
    private String id;

    public UserWishListProductModel(int product_ID_, String id) {
        this.product_ID_ = product_ID_;
        this.id = id;
    }

    public int getProduct_ID_() {
        return product_ID_;
    }

    public void setProduct_ID_(int product_ID_) {
        this.product_ID_ = product_ID_;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
