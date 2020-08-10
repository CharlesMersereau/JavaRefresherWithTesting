package com.revature.javarefresherwithtesting.ui.addpost;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.revature.javarefresherwithtesting.data.api.PostRepository;

public class AddEditPostViewModelFactory implements ViewModelProvider.Factory {

    private PostRepository postRepository;

    public AddEditPostViewModelFactory(PostRepository postRepository) {
        super();
        this.postRepository = postRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddEditPostViewModel(postRepository);
    }
}
