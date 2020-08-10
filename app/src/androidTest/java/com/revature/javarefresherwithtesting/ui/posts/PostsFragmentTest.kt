package com.revature.javarefresherwithtesting.ui.posts

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsFragmentTest {
    @Before
    @Throws(Exception::class)
    fun setUp() {
        val scenario = launchFragmentInContainer<PostsFragment>()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }
}