package com.revature.javarefresherwithtesting.data.api;

import androidx.lifecycle.LiveData;

import com.revature.javarefresherwithtesting.data.database.PostDatabase;
import com.revature.javarefresherwithtesting.data.entities.Post;

import java.util.List;

public class FakePostRepository extends PostRepository {

    public FakePostRepository(PostDatabase postDatabase, PostsApi postsApi) {
        super(postDatabase, postsApi);
    }

    @Override
    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }
}
