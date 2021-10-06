package com.example.detail.view.profile

import com.example.domain.interactors.AlbumInteractors
import com.example.domain.interactors.PhotoInteractors
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel

    @Mock
    private lateinit var albumInteractors: AlbumInteractors

    @Mock
    private lateinit var photoInteractors: PhotoInteractors

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = ProfileViewModel(albumInteractors, photoInteractors)
    }

    @Test
    fun `view model`() {
        viewModel.refreshPhotoData()
        viewModel.refreshAlbumData(0)
    }
}