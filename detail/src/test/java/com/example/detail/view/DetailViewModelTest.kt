package com.example.detail.view

import com.example.domain.interactors.CommentInteractors
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var commentsInteractors: CommentInteractors

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(commentsInteractors)
    }

    @Test
    fun `view model`() {
        viewModel.refreshCommentData(0)
    }

}