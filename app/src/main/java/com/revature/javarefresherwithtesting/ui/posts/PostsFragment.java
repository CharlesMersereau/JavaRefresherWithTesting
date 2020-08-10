package com.revature.javarefresherwithtesting.ui.posts;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.revature.javarefresherwithtesting.R;
import com.revature.javarefresherwithtesting.data.entities.Post;
import com.revature.javarefresherwithtesting.databinding.FragmentPostsBinding;
import com.revature.javarefresherwithtesting.utilities.InjectorUtils;

public class PostsFragment extends Fragment implements PostAdapter.OnPostItemClickListener {

    private PostsViewModel mViewModel;
    private FragmentPostsBinding mFragmentPostsBinding = null;
    private RecyclerView mPostRecyclerView;
    private PostAdapter mPostAdapter;

    public static PostsFragment newInstance() {
        return new PostsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mFragmentPostsBinding = FragmentPostsBinding.inflate(inflater);

        setHasOptionsMenu(true);

        initializeUI();

        return mFragmentPostsBinding.getRoot();
    }

    private void initializeUI() {
        mFragmentPostsBinding.fabFragmentpostsAddpost.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(PostsFragmentDirections.actionPostsFragmentToAddEditPostFragment(null));
        });

        mPostRecyclerView = mFragmentPostsBinding.rvFragmentpostsPosts;
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPostAdapter = new PostAdapter(getActivity());
        mPostAdapter.setOnPostItemClickListener(this);
        mPostRecyclerView.setAdapter(mPostAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(
                requireActivity(),
                InjectorUtils.providePostsViewModelFactory(requireActivity().getApplication())
        ).get(PostsViewModel.class);

        mViewModel.getAllPosts().observe(getViewLifecycleOwner(), posts -> {
            if (posts != null && posts.size() > 0) {
                mPostAdapter.submitList(posts);
                mFragmentPostsBinding.tvFragmentpostsNoposts.setVisibility(View.GONE);
            } else {
                mFragmentPostsBinding.tvFragmentpostsNoposts.setVisibility(View.VISIBLE);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.deletePost(mPostAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Snackbar.make(mFragmentPostsBinding.getRoot(), "Post deleted", Snackbar.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mPostRecyclerView);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentPostsBinding = null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_delete_all_notes:
                mViewModel.deleteAllPosts();
                Snackbar.make(mFragmentPostsBinding.getRoot(), "All posts deleted", Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onPostItemClick(Post post) {
        NavHostFragment.findNavController(this).navigate(PostsFragmentDirections.actionPostsFragmentToAddEditPostFragment(post));
    }
}
