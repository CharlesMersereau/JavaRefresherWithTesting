package com.revature.javarefresherwithtesting.ui.posts;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.revature.javarefresherwithtesting.data.api.PostRepository;

public class PostsViewModelFactory implements ViewModelProvider.Factory {

    private PostRepository postRepository;

    public PostsViewModelFactory(PostRepository postRepository) {
        super();
        this.postRepository = postRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PostsViewModel(postRepository);
    }
}
