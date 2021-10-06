package com.example.home

import androidx.navigation.fragment.NavHostFragment
import com.example.base.presentation.BaseActivity
import com.example.base.presentation.BaseViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<BaseViewModel>() {

    private lateinit var navHostFragment: NavHostFragment

    @Inject
    override lateinit var viewModel: BaseViewModel
    override val resourceLayout: Int?
        get() = R.layout.activity_home

    override fun onInitViews() {
        super.onInitViews()
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
    }
}