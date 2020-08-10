package com.revature.javarefresherwithtesting.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.revature.javarefresherwithtesting.data.entities.Post;

@Database(entities = Post.class, version = 3)
public abstract class PostDatabase extends RoomDatabase {

    private static PostDatabase instance;

    public abstract PostDAO postDAO();

    public static synchronized PostDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PostDatabase.class, "post_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

}
