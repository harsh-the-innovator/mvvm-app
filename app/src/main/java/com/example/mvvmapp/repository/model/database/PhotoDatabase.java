package com.example.mvvmapp.repository.model.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvmapp.repository.model.entities.Photo;
import com.example.mvvmapp.repository.model.dao.PhotoDao;

@Database(entities = {Photo.class},version = 1)
public abstract class PhotoDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "PHOTO_DATABASE";
    private static volatile PhotoDatabase INSTANCE;

    public static PhotoDatabase getInstance(Context context){
        if(INSTANCE==null){
            synchronized (PhotoDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context,PhotoDatabase.class,DATABASE_NAME)
                            .addCallback(callback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract PhotoDao photoDao();

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // delete all the data after creating in the background thread
            PhotoDao photoDao = INSTANCE.photoDao();

            Thread deleteInBackgroundThread = new Thread(photoDao::deleteAll);

            deleteInBackgroundThread.start();

        }
    };

}
