package com.revature.javarefresherwithtesting.data.database;

import androidx.room.Database;

import com.revature.javarefresherwithtesting.data.entities.Post;

@Database(entities = Post.class, version = 1)
public abstract class TestPostDatabase extends PostDatabase {

    abstract TestPostDAO testPostDAO();

    @Override
    public PostDAO postDAO() {
        return testPostDAO();
    }

    

}
