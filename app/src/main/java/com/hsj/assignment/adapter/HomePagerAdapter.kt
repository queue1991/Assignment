package com.hsj.assignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hsj.assignment.fragment.home.GoodsListFragment
import com.hsj.assignment.fragment.home.IntroFragment

/**
 * HomeFragment 에서 TabLayout / Viewpager 처리 Adapter
 */
open class HomePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    var fragments: MutableList<Fragment> = ArrayList()
    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    init {
        fragments.add(GoodsListFragment())
        fragments.add(IntroFragment())
    }
}