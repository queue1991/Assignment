package com.hsj.assignment.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.hsj.assignment.R
import com.hsj.assignment.adapter.DetailPagerAdapter
import com.hsj.assignment.adapter.HomePagerAdapter
import com.hsj.assignment.base.BaseKotlinFragment
import com.hsj.assignment.databinding.FragmentDetailBinding
import com.hsj.assignment.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseKotlinFragment<FragmentDetailBinding, DetailViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_detail
    override val viewModel: DetailViewModel by viewModel()

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewDataBinding.viewModel = viewModel
        this.viewDataBinding.fragment = this

        tabLayout = tablayout_detail
        viewPager = viewpager_detail

        setTabLayout()
        setViewPager()

    }

    private fun setTabLayout(){
        tabLayout.addTab(tabLayout.newTab().setText("상품 소개"))
        tabLayout.addTab(tabLayout.newTab().setText("리뷰 목록"))

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
        viewPager.adapter = DetailPagerAdapter(activity!!.supportFragmentManager)
        viewPager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabLayout){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
    }

}