package com.example.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.di.component.BaseComponent
import com.example.base.di.component.BaseNavigationComponent
import com.example.base.navigation.CommonNavigation
import com.example.base.presentation.BaseFragment
import com.example.base.subscriber.ResultState
import com.example.detail.R
import com.example.detail.databinding.DetailFragmentBinding
import com.example.detail.di.DaggerDetailPageComponent
import com.example.detail.view.adapter.DetailAdapter
import com.example.detail.view.profile.ProfileFragment
import com.example.domain.entities.CommentEntites
import com.example.domain.entities.PostEntites
import com.example.domain.entities.UserDataEntites
import kotlinx.android.synthetic.main.detail_fragment.*
import java.util.ArrayList
import javax.inject.Inject

class DetailFragment: BaseFragment<DetailViewModel>() {

    companion object {
        const val PARAM_DETAIL_FRAGMENT = "detailFragment"
    }

    var detailPostEntites: PostEntites? =  null
    lateinit var mAdapter: DetailAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    internal var commentResultsList: MutableList<CommentEntites> = ArrayList()

    @Inject
    lateinit var commonNavigation: CommonNavigation

    @Inject
    override lateinit var  viewModel: DetailViewModel
    override fun injectComponent() {
        DaggerDetailPageComponent.builder()
            .baseComponent(getBaseComponent(BaseComponent::class.java))
            .baseNavigationComponent(getAppSubComponent(BaseNavigationComponent::class.java))
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailPostEntites = arguments?.getParcelable<PostEntites>(PARAM_DETAIL_FRAGMENT)
        tvTitleDetail.text = detailPostEntites?.title
        tvTitleBody.text = detailPostEntites?.body
        tvProfileNameIcon.text = detailPostEntites?.user?.name?.first().toString()
        tvProfileName.text = detailPostEntites?.user?.name
        viewModel.refreshCommentData(detailPostEntites?.id ?: 0)
        linUserProfile.setOnClickListener {
            onProfileClicked(detailPostEntites?.user)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =  DetailFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.commentEntites.observe(this, Observer {
            onResultLoaded(it)
        })
    }

    private fun onResultLoaded(resultState: ResultState<List<CommentEntites>>?) {
        when (resultState) {
            is ResultState.Loading -> {
                showToast("loading")
            }
            is ResultState.Success -> {
                onGetCommentData(resultState.data)
            }
            is ResultState.Error -> {
                resultState.throwable.message?.let { showToast(it) }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    private fun onGetCommentData(commentEntites: List<CommentEntites>) {
        commentResultsList.addAll(commentEntites)
        tvCommentCount.text = "Comment ( ${commentEntites.size} )"
        initAdapter()
    }

    fun initAdapter() {
        mAdapter = DetailAdapter(commentResultsList)
        rvComments.adapter = mAdapter
        linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        rvComments.layoutManager = linearLayoutManager
    }

     fun onProfileClicked(data: UserDataEntites?) {
         data?.let {
             commonNavigation.navigateToProfile(findNavController(), data)
         }
    }

}