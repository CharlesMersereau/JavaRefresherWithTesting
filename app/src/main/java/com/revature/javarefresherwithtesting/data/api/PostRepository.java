package com.revature.javarefresherwithtesting.data.api;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.revature.javarefresherwithtesting.data.database.PostDAO;
import com.revature.javarefresherwithtesting.data.database.PostDatabase;
import com.revature.javarefresherwithtesting.data.entities.Post;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private PostDAO postDAO;
    LiveData<List<Post>> allPosts;
    private PostsApi postsApi;

    public PostRepository(PostDatabase postDatabase, PostsApi postsApi) {
        postDAO = postDatabase.postDAO();
        this.postsApi = postsApi;
        allPosts = postDAO.getPosts();
    }

    public void insertPost(Post post) {
        new InsertPostAsyncTask(postDAO).execute(post);
    }

    public void updatePost(Post post) {
        new UpdatePostAsyncTask(postDAO).execute(post);
    }

    public void deletePost(Post post) {
        new DeletePostAsyncTask(postDAO).execute(post);
    }

    public void deleteAllPosts() {
        new DeleteAllPostsAsyncTask(postDAO).execute();
    }

    public LiveData<List<Post>> getAllPosts() {
        new SyncPostsAsyncTask(postDAO, postsApi).execute();
        return allPosts;
    }

    private static class SyncPostsAsyncTask extends AsyncTask<Void, Void, Void> {

        private PostDAO postDAO;
        private PostsApi postsApi;

        private SyncPostsAsyncTask(PostDAO postDAO, PostsApi postsApi) {
            this.postDAO = postDAO;
            this.postsApi = postsApi;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Call<List<Post>> postsCall = postsApi.getPosts();

            postsCall.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    List<Post> posts = response.body() == null ? new ArrayList<>() : response.body();
                    Post[] postArray = new Post[posts.size()];
                    postArray = posts.toArray(postArray);
                    new InsertListOfPostsAsyncTask(postDAO).execute(postArray);
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    Log.d("PostRepository", "Failed to load posts");
                }
            });

            return null;
        }
    }

    private static class InsertPostAsyncTask extends AsyncTask<Post, Void, Void> {

        private PostDAO postDAO;

        private InsertPostAsyncTask(PostDAO postDAO) {
            this.postDAO = postDAO;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDAO.insertPost(posts[0]);
            return null;
        }
    }

    private static class InsertListOfPostsAsyncTask extends AsyncTask<Post, Void, Void> {

        private PostDAO postDAO;

        private InsertListOfPostsAsyncTask(PostDAO postDAO) {
            this.postDAO = postDAO;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDAO.insertListOfPosts(posts);
            return null;
        }
    }

    private static class UpdatePostAsyncTask extends AsyncTask<Post, Void, Void> {

        private PostDAO postDAO;

        private UpdatePostAsyncTask(PostDAO postDAO) {
            this.postDAO = postDAO;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDAO.updatePost(posts[0]);
            return null;
        }
    }

    private static class DeletePostAsyncTask extends AsyncTask<Post, Void, Void> {

        private PostDAO postDAO;

        private DeletePostAsyncTask(PostDAO postDAO) {
            this.postDAO = postDAO;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDAO.deletePost(posts[0]);
            return null;
        }
    }

    private static class DeleteAllPostsAsyncTask extends AsyncTask<Void, Void, Void> {

        private PostDAO postDAO;

        private DeleteAllPostsAsyncTask(PostDAO postDAO) {
            this.postDAO = postDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            postDAO.deleteAllPosts();
            return null;
        }
    }
}
