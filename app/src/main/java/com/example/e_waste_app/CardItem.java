package com.example.e_waste_app;

import android.graphics.Bitmap;

public class CardItem {
    private String userName;
    private Bitmap image;

    public CardItem(String userName, Bitmap image) {
        this.userName = userName;
        this.image = image;
    }

    public String getUserName() { return userName; }
    public Bitmap getImage() { return image; }
}
