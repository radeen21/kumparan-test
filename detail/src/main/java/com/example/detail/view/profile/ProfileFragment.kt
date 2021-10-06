package com.example.detail.view.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.di.component.BaseComponent
import com.example.base.di.component.BaseNavigationComponent
import com.example.base.presentation.BaseFragment
import com.example.base.subscriber.ResultState
import com.example.detail.databinding.ProfileFragmentBinding
import com.example.detail.di.DaggerDetailPageComponent
import com.example.detail.view.adapter.ProfileAdapter
import com.example.domain.entities.AlbumDataEntities
import com.example.domain.entities.UserDataEntites
import kotlinx.android.synthetic.main.profile_fragment.*
import java.util.*
import javax.inject.Inject

class ProfileFragment: BaseFragment<ProfileViewModel>() {

    companion object {
        const val PARAM_PROFILE_FRAGMENT = "profileFragment"
    }

    var profileEntites: UserDataEntites? =  null
    internal var albumResultsList: MutableList<AlbumDataEntities> = ArrayList()
    lateinit var mAdapter: ProfileAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    override lateinit var  viewModel: ProfileViewModel
    override fun injectComponent() {
        DaggerDetailPageComponent.builder()
            .baseComponent(getBaseComponent(BaseComponent::class.java))
            .baseNavigationComponent(getAppSubComponent(BaseNavigationComponent::class.java))
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileEntites = arguments?.getParcelable<UserDataEntites>(PARAM_PROFILE_FRAGMENT)
        viewModel.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        tvProfile.text = profileEntites?.name
        tvUserEmail.text = profileEntites?.email
        tvUserAddress.text = profileEntites?.address?.street
        tvUserCompany.text = profileEntites?.company?.name
        viewModel.refreshPhotoData()
        viewModel.refreshAlbumData(profileEntites?.id ?: 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =  ProfileFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.albumEntites.observe(this, Observer {
            onResultLoaded(it)
        })
    }

    private fun onResultLoaded(resultState: ResultState<List<AlbumDataEntities>>?) {
        when (resultState) {
            is ResultState.Loading -> {
                showToast("loading")
            }
            is ResultState.Success -> {
                onGetAlbumData(resultState.data)
            }
            is ResultState.Error -> {
                resultState.throwable.message?.let { showToast(it) }
            }
        }
    }

    private fun onGetAlbumData(albumEntites: List<AlbumDataEntities>) {
        albumResultsList.addAll(albumEntites)
        initAdapter()
    }

    fun initAdapter() {
        mAdapter = ProfileAdapter(albumResultsList)
        rvAlbums.adapter = mAdapter
        linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        rvAlbums.layoutManager = linearLayoutManager
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}