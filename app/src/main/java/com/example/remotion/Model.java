package com.example.remotion;

import android.view.Display;

public class Model {

    String title,image;

    public Model() {
    }

    public Model(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
