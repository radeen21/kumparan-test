package com.example.kumparantest.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.base.navigation.CommonNavigation
import com.example.detail.view.DetailFragment
import com.example.detail.view.profile.ProfileFragment
import com.example.domain.entities.PostEntites
import com.example.domain.entities.UserDataEntites
import com.example.kumparantest.R


class CommonNavigationImplementation : CommonNavigation {
    override fun navigateToDetail(findNavController: NavController, data: Any) {
        if (data is PostEntites) {
            val bundle = bundleOf(Pair(DetailFragment.PARAM_DETAIL_FRAGMENT, data))
            findNavController.navigate(R.id.action_mainFrament_to_detailFragment, bundle)
        }
    }

    override fun navigateToProfile(findNavController: NavController, data: Any) {
        if (data is UserDataEntites) {
            val bundle = bundleOf(Pair(ProfileFragment.PARAM_PROFILE_FRAGMENT, data))
            findNavController.navigate(R.id.action_detailFragment_to_profileFragment, bundle)
        }
    }
}