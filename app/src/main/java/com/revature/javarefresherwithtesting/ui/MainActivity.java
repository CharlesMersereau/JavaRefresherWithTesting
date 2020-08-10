package com.revature.javarefresherwithtesting.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.revature.javarefresherwithtesting.R;
import com.revature.javarefresherwithtesting.databinding.ActivityMainBinding;
import com.revature.javarefresherwithtesting.ui.posts.PostsViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(LayoutInflater.from(this));

        setContentView(binding.getRoot());
    }
}
