package com.example.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.base.R
import com.google.android.material.tabs.TabLayout

abstract class TabFragment<VM : BaseViewModel> : BaseFragment<VM>() {

    protected var tabLayout: TabLayout? = null
    protected var pager: ViewPager? = null

    private var adapter: TabAdapter? = null
    protected lateinit var fragments: MutableList<Fragment>
    protected lateinit var titles: MutableList<String>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)
        tabLayout = view.findViewById(R.id.tab)
        pager = view.findViewById(R.id.pager)
        return view
    }

    override fun onInitViews() {
        super.onInitViews()
        fragments = mutableListOf()
        titles = mutableListOf()
    }

    protected fun initAdapter() {
        adapter = TabAdapter(
            fragments,
            titles,
            childFragmentManager
        )
    }

    protected suspend fun initPager() {
        if (isAdded) {
            adapter?.notifyDataSetChanged()
            pager!!.adapter = adapter
            tabLayout!!.setupWithViewPager(pager)
        }
    }

}
