package com.example.mvvmapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmapp.repository.PhotoRepository;
import com.example.mvvmapp.repository.model.entities.Photo;

import java.util.List;

public class PhotoViewModel extends AndroidViewModel {
    private PhotoRepository repository;

    public PhotoViewModel(@NonNull Application application) {
        super(application);
        repository = new PhotoRepository(application);
    }

    public LiveData<List<Photo>> getPhotos() {
        return repository.getAllPhotos();
    }

    public void fetchData(){
        repository.fetchDataFromRemoteService();
    }
}
