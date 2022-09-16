package com.example.mvvmapp.repository.remote;


import com.example.mvvmapp.repository.model.entities.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/photos")
    Call<List<Photo>> getAllPhotosFromApi();
}
