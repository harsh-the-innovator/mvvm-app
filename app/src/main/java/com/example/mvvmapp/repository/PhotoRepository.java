package com.example.mvvmapp.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.mvvmapp.repository.model.entities.Photo;
import com.example.mvvmapp.repository.model.dao.PhotoDao;
import com.example.mvvmapp.repository.model.database.PhotoDatabase;
import com.example.mvvmapp.repository.remote.Api;
import com.example.mvvmapp.repository.remote.ApiClient;
import com.example.mvvmapp.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepository {
    private static final String TAG = "PhotoRepository";

    private PhotoDatabase database;
    private Api api;

    public PhotoRepository(Application application) {
        database = PhotoDatabase.getInstance(application);
        api = ApiClient.getClient().create(Api.class);
    }

    public LiveData<List<Photo>> getAllPhotos(){
        return database.photoDao().getAllPhotos();
    }

    private void insertPhotos(List<Photo> photoList){
        Log.i(TAG, "insertPhotos: ");
        Thread insertionWorkerThread = new Thread(() -> {
            database.photoDao().insert(photoList);
        });

        insertionWorkerThread.start();
    }

    public void fetchDataFromRemoteService() {
        Log.i(TAG, "fetchDataFromRemoteService: ");
        // runs in background thread because of executor service
        Call<List<Photo>> call = api.getAllPhotosFromApi();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                // runs in main thread
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse: ");
                    // insert photos list in database after fetching from api
                    insertPhotos(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e(TAG, "onFailure: ");
            }
        });
    }

}
