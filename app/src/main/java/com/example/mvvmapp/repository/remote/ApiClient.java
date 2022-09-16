package com.example.mvvmapp.repository.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ApiClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit instance = null;

    public static Retrofit getClient(){
        if(instance==null){
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return instance;
    }
}

