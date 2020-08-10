package com.revature.javarefresherwithtesting.ui.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.revature.javarefresherwithtesting.data.api.PostRepository;
import com.revature.javarefresherwithtesting.data.entities.Post;

import java.util.List;

public class PostsViewModel extends ViewModel {

    private PostRepository postRepository;
    private LiveData<List<Post>> posts;

    PostsViewModel(PostRepository postRepository) {
        super();
        this.postRepository = postRepository;
        posts = postRepository.getAllPosts();
    }

    public void insertPost(Post post) {
        postRepository.insertPost(post);
    }

    public void updatePost(Post post) {
        postRepository.updatePost(post);
    }

    public void deletePost(Post post) {
        postRepository.deletePost(post);
    }

    public void deleteAllPosts() {
        postRepository.deleteAllPosts();
    }

    public LiveData<List<Post>> getAllPosts() {
        return posts;
    }

}
