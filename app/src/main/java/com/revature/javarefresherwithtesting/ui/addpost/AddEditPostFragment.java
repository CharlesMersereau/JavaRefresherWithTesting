package com.revature.javarefresherwithtesting.ui.addpost;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.revature.javarefresherwithtesting.R;
import com.revature.javarefresherwithtesting.data.entities.Post;
import com.revature.javarefresherwithtesting.databinding.FragmentAddEditPostBinding;
import com.revature.javarefresherwithtesting.utilities.InjectorUtils;
import com.revature.javarefresherwithtesting.utilities.KeyboardUtil;

public class AddEditPostFragment extends Fragment {

    private AddEditPostViewModel mViewModel;
    private FragmentAddEditPostBinding mFragmentAddEditPostBinding = null;
    private Post mPostToEdit = null;

    public static AddEditPostFragment newInstance() {
        return new AddEditPostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mFragmentAddEditPostBinding = FragmentAddEditPostBinding.inflate(inflater);

        initializeUI();

        return mFragmentAddEditPostBinding.getRoot();
    }

    private void initializeUI() {

        if (getArguments() != null) {
            mPostToEdit = AddEditPostFragmentArgs.fromBundle(getArguments()).getPostToEdit();

            if (mPostToEdit != null) {
                mFragmentAddEditPostBinding.etAddpostTitle.setText(mPostToEdit.getTitle());
                mFragmentAddEditPostBinding.etAddpostBody.setText(mPostToEdit.getBody());
                mFragmentAddEditPostBinding.btnAddpostAdd.setText(getString(R.string.button_savepost));
                mFragmentAddEditPostBinding.btnAddpostAdd.setEnabled(true);
            }

        }

        mFragmentAddEditPostBinding.btnAddpostAdd.setOnClickListener(v -> {
            String title = mFragmentAddEditPostBinding.etAddpostTitle.getText().toString().trim();
            String body = mFragmentAddEditPostBinding.etAddpostBody.getText().toString().trim();

            Post post = new Post();
            post.setTitle(title);
            post.setBody(body);
            if (mPostToEdit != null) {
                post.setId(mPostToEdit.getId());
                post.setUserId(mPostToEdit.getUserId());
            } else {
                post.setUserId(99);
            }

            mViewModel.addPost(post);

            KeyboardUtil.hideSoftKeyboard(requireContext(), mFragmentAddEditPostBinding.getRoot());
            NavHostFragment.findNavController(this).navigate(AddEditPostFragmentDirections.actionAddEditPostFragmentToPostsFragment());
        });

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                mFragmentAddEditPostBinding.btnAddpostAdd.setEnabled(checkValidFields());
            }
        };

        mFragmentAddEditPostBinding.etAddpostTitle.addTextChangedListener(watcher);
        mFragmentAddEditPostBinding.etAddpostBody.addTextChangedListener(watcher);
    }

    public boolean checkValidFields() {
        Editable title = mFragmentAddEditPostBinding.etAddpostTitle.getText();
        Editable body = mFragmentAddEditPostBinding.etAddpostBody.getText();
        return title != null && !title.toString().trim().isEmpty()
                && body != null && !body.toString().trim().isEmpty();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(
                requireActivity(),
                InjectorUtils.provideAddPostViewModelFactory(requireActivity().getApplication())
        ).get(AddEditPostViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentAddEditPostBinding = null;
    }
}
