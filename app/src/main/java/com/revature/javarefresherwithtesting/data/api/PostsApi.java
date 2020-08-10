package com.revature.javarefresherwithtesting.data.api;

import com.revature.javarefresherwithtesting.data.entities.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsApi {

    @GET("posts")
    Call<List<Post>> getPosts();

}
