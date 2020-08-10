package com.revature.javarefresherwithtesting.ui.addpost;

import androidx.lifecycle.ViewModel;

import com.revature.javarefresherwithtesting.data.api.PostRepository;
import com.revature.javarefresherwithtesting.data.entities.Post;

class AddEditPostViewModel extends ViewModel {

    private PostRepository postRepository;

    AddEditPostViewModel(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    void addPost(Post post) {
        postRepository.insertPost(post);
    }
}
