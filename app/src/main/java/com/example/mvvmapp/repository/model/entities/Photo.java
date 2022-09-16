package com.example.mvvmapp.repository.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "PHOTO",indices = {@Index(value = {"id"},unique = true)})
public class Photo {

    @PrimaryKey(autoGenerate = true)
    private int photoId;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("title")  // the object field name coming from the rest api
    @ColumnInfo(name = "title")   // column name in table
    private String title;

    @SerializedName("url")
    @ColumnInfo(name = "image")
    private String image;

    public Photo(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    @NonNull
    @Override
    public String toString() {
        return "Photo{" +
                "photoId=" + photoId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
