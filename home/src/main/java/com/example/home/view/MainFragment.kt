package com.example.home.view

import android.os.Bundle
import android.preference.PreferenceManager
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
import com.example.base.presentation.TabFragment
import com.example.base.subscriber.ResultState
import com.example.detail.view.DetailFragment
import com.example.domain.entities.PostEntites
import com.example.home.R
import com.example.home.databinding.HomeFragmentBinding
import com.example.home.di.DaggerHomePageComponent
import com.example.home.view.adapter.PostAdapter
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.ArrayList

import javax.inject.Inject


class MainFragment : TabFragment<PostViewModel>(), PostAdapter.OnClickListener {

    lateinit var mAdapter: PostAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    internal var postResultsList: MutableList<PostEntites> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = HomeFragmentBinding.inflate(inflater, container, false)
        viewModel.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        tabLayout = binding.root.findViewById(R.id.tab)
        pager = binding.root.findViewById(R.id.pager)
        setHasOptionsMenu(true)
        return binding.root
    }

    @Inject
    override lateinit var viewModel: PostViewModel

    override fun injectComponent() {
        DaggerHomePageComponent.builder()
            .baseComponent(getBaseComponent(BaseComponent::class.java))
            .baseNavigationComponent(getAppSubComponent(BaseNavigationComponent::class.java))
            .build()
            .inject(this)
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.postEntites.observe(this, Observer {
            onResultLoaded(it)
        })
    }

    private fun onResultLoaded(resultState: ResultState<List<PostEntites>>?) {
        when (resultState) {
            is ResultState.Loading -> {
                showToast("loading")
            }
            is ResultState.Success -> {
                onGetPostData(resultState.data)
            }
            is ResultState.Error -> {
                resultState.throwable.message?.let { showToast(it) }
            }
        }
    }

    private fun onGetPostData(postEntites: List<PostEntites>) {
        postResultsList.addAll(postEntites)
        initPostAdapter()
    }

    private fun initPostAdapter() {
        mAdapter = PostAdapter(postResultsList)
        mAdapter.onClickListener = this
        rvPosts.adapter = mAdapter
        linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        rvPosts.layoutManager = linearLayoutManager
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    override fun onItemClicked(data: PostEntites?) {
        val bundle = bundleOf(Pair(DetailFragment.PARAM_DETAIL_FRAGMENT, data))
        findNavController().navigate(R.id.action_placeholder_to_detailFragment, bundle)
    }
}