package com.hsj.assignment

import com.hsj.assignment.base.BaseKotlinActivity
import com.hsj.assignment.databinding.ActivityMainBinding
import com.hsj.assignment.fragment.home.HomeFragment
import com.hsj.assignment.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: HomeViewModel by viewModel()
    override val fragmentPlace: Int
        get() = R.id.container

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
        replaceFragment(HomeFragment())
    }
}