package com.example.home.view

import com.example.domain.interactors.PostInteractors
import com.example.domain.interactors.UserInteractors
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PostViewModelTest {

    private lateinit var viewModel: PostViewModel

    @Mock
    private lateinit var postInteractors: PostInteractors

    @Mock
    private lateinit var userInteractors: UserInteractors

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = PostViewModel(postInteractors, userInteractors)
    }

    @Test
    fun `view model`() {
        viewModel.refreshGetUser()
        viewModel.refreshPostData()
    }
}