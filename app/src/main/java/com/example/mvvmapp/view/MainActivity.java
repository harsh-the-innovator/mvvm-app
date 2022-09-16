package com.example.mvvmapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mvvmapp.databinding.ActivityMainBinding;
import com.example.mvvmapp.repository.model.entities.Photo;
import com.example.mvvmapp.view.adapters.PhotoRecViewAdapter;
import com.example.mvvmapp.viewmodel.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private PhotoViewModel photoViewModel;
    private PhotoRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initialize view model
        photoViewModel = new ViewModelProvider(this).get(PhotoViewModel.class);

        // attach observer
        photoViewModel.getPhotos().observe(this,photoListObserver);

        // initialize adapter with empty list
        adapter = new PhotoRecViewAdapter(this, new ArrayList<>());

        // configure recycler view
        binding.recView.setHasFixedSize(true);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setItemAnimator(new DefaultItemAnimator());
        binding.recView.setAdapter(adapter);
        
        // network call
        fetchData();
    }

    private void fetchData(){
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                // make network request after 5 seconds of delay
                Log.e(TAG, "run: RUNNING EXECUTOR: " + Thread.currentThread().getName());
                // runs in background thread
                photoViewModel.fetchData();
            }
        },5, TimeUnit.SECONDS);
    }

    private final Observer<List<Photo>> photoListObserver = new Observer<List<Photo>>() {
        @Override
        public void onChanged(List<Photo> photoList) {
            // whenever the data changes show in recycler view
            Toast.makeText(MainActivity.this, "DATA UPDATED", Toast.LENGTH_SHORT).show();
            adapter.setPhotoList(photoList);
        }
    };
}