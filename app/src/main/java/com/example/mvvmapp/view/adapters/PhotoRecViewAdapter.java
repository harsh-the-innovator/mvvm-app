package com.example.mvvmapp.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmapp.databinding.PhotoLayoutItemBinding;
import com.example.mvvmapp.repository.model.entities.Photo;

import java.util.List;

public class PhotoRecViewAdapter extends RecyclerView.Adapter<PhotoRecViewAdapter.MyViewHolder>{

    private static final String TAG = "PhotoRecViewAdapter";
    private Context context;
    private List<Photo> photoList;

    public PhotoRecViewAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PhotoLayoutItemBinding binding = PhotoLayoutItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Photo photo = photoList.get(position);
       // Log.i(TAG, "onBindViewHolder: " + "BINDING = " + holder.binding + "POSITION = " + position  + " PHOTO = "+ photo.toString());
        holder.binding.txtPhotoId.setText(String.valueOf(photo.getId()));
        holder.binding.txtPhotoTitle.setText(photo.getTitle());

        Glide.with(context)
                .load(photo.getImage())
                .into(holder.binding.image);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void setPhotoList(List<Photo> photoList){
        this.photoList = photoList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public PhotoLayoutItemBinding binding;
        public MyViewHolder(PhotoLayoutItemBinding photoLayoutItemBinding){
            super(photoLayoutItemBinding.getRoot());
            binding = photoLayoutItemBinding;
        }
    }
}
