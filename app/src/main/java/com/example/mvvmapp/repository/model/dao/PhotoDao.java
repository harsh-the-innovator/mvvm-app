package com.example.mvvmapp.repository.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvmapp.repository.model.entities.Photo;

import java.util.List;

@Dao
public interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Photo> photoList);

    @Query(value = "SELECT * FROM PHOTO")
    LiveData<List<Photo>> getAllPhotos();

    @Query("DELETE FROM PHOTO")
    void deleteAll();
}


