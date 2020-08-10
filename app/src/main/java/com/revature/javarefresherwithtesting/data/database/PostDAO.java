package com.revature.javarefresherwithtesting.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.revature.javarefresherwithtesting.data.entities.Post;

import java.util.List;

@Dao
public interface PostDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(Post post);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertListOfPosts(Post... posts);

    @Update
    void updatePost(Post post);

    @Delete
    void deletePost(Post post);

    @Query("DELETE FROM post_table")
    void deleteAllPosts();

    @Query("SELECT * FROM post_table ORDER BY id DESC")
    LiveData<List<Post>> getPosts();
}
