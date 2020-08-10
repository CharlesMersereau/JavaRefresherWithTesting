package com.revature.javarefresherwithtesting.utilities;

import android.app.Application;

import com.revature.javarefresherwithtesting.data.api.PostRepository;
import com.revature.javarefresherwithtesting.data.api.PostsApi;
import com.revature.javarefresherwithtesting.data.database.PostDatabase;
import com.revature.javarefresherwithtesting.ui.addpost.AddEditPostViewModelFactory;
import com.revature.javarefresherwithtesting.ui.posts.PostsViewModelFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class InjectorUtils {

    public static PostsViewModelFactory providePostsViewModelFactory(Application application) {
        return new PostsViewModelFactory(
                new PostRepository(
                        PostDatabase.getInstance(application),
                        new Retrofit.Builder()
                            .baseUrl("https://jsonplaceholder.typicode.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(PostsApi.class)
                )
        );
    }

    public static PostsViewModelFactory providePostsViewModelFactoryWithTestRepository(Application application) {
        return new PostsViewModelFactory(
                new PostRepository(
                        PostDatabase.getInstance(application),
                        new Retrofit.Builder()
                                .baseUrl("https://jsonplaceholder.typicode.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(PostsApi.class)
                )
        );
    }

    public static AddEditPostViewModelFactory provideAddPostViewModelFactory(Application application) {
        return new AddEditPostViewModelFactory(
                new PostRepository(
                        PostDatabase.getInstance(application),
                        new Retrofit.Builder()
                                .baseUrl("https://jsonplaceholder.typicode.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(PostsApi.class)
                )
        );
    }

    public static AddEditPostViewModelFactory provideAddPostViewModelFactoryWithTestRepository(Application application) {
        return new AddEditPostViewModelFactory(
                new PostRepository(
                        PostDatabase.getInstance(application),
                        new Retrofit.Builder()
                                .baseUrl("https://jsonplaceholder.typicode.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(PostsApi.class)
                )
        );
    }

}
