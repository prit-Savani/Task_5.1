package com.example.news;

import android.os.Parcel;
import android.os.Parcelable;

public class Item_news implements Parcelable {
    private String title;
    private String description;
    private String imageUrl;
    private int imageResourceId;

    public Item_news(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageResourceId = 0;
    }

    public Item_news(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageUrl = null;
        this.imageResourceId = imageResourceId;
    }

    protected Item_news(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        imageResourceId = in.readInt();
    }

    public static final Creator<Item_news> CREATOR = new Creator<Item_news>() {
        @Override
        public Item_news createFromParcel(Parcel in) {
            return new Item_news(in);
        }

        @Override
        public Item_news[] newArray(int size) {
            return new Item_news[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean hasLocalImage() {
        return imageResourceId != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeInt(imageResourceId);
    }
}