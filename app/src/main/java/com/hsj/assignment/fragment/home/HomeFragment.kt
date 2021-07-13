package com.hsj.assignment.fragment.home

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.hsj.assignment.R
import com.hsj.assignment.adapter.HomePagerAdapter
import com.hsj.assignment.base.BaseKotlinFragment
import com.hsj.assignment.databinding.FragmentHomeBinding
import com.hsj.assignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseKotlinFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModel()

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tabLayout = tablayout_home
        viewPager = viewpager_home

        setTabLayout()
        setViewPager()

    }

    private fun setTabLayout(){
        tabLayout.addTab(tabLayout.newTab().setText("상품 목록"))
        tabLayout.addTab(tabLayout.newTab().setText("앱 소개"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position;
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setViewPager(){
        viewPager.adapter = HomePagerAdapter(activity!!.supportFragmentManager)
        viewPager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabLayout){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
    }


}