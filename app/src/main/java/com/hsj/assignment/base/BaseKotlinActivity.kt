package com.hsj.assignment.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


/**
 * ViewDataBinding, BaseKotlinViewModel 를 유형매개변수로 받는 abstract class
 * 1. inflate View
 * 2. databinding 준비
 */
abstract class BaseKotlinActivity<T : ViewDataBinding, R : BaseKotlinViewModel> : AppCompatActivity() {

    lateinit var viewDataBinding: T

    /**
     * setContentView로 호출할 Layout의 리소스 Id.
     */
    abstract val layoutResourceId: Int

    /**
     * viewModel 로 쓰일 변수.
     */
    abstract val viewModel: R

    /**
     * fragment를 담을 view
     */
    abstract val fragmentPlace: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)

        // ViewModel에서 postValue(LiveData) 를 해주는 순간 UI가 업데이트 되기 때문에 따로 observe를 할 필요가 없게 함
        viewDataBinding.lifecycleOwner = this

        initStartView()
        initDataBinding()
        initAfterBinding()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    /**
     * 레이아웃을 띄운 직후 호출.
     * 뷰나 액티비티의 속성 등을 초기화.
     * ex) 리사이클러뷰, 툴바, 드로어뷰..
     */
    abstract fun initStartView()

    /**
     * 두번째로 호출.
     * 데이터 바인딩 및 rxjava 설정.
     * ex) rxjava observe, databinding observe..
     */
    abstract fun initDataBinding()

    /**
     * 바인딩 이후에 할 일을 여기에 구현.
     * 그 외에 설정할 것이 있으면 이곳에서 설정.
     * 클릭 리스너도 이곳에서 설정.
     */
    abstract fun initAfterBinding()

    fun replaceFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(fragmentPlace, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
    }

    override fun onBackPressed() {

    }

}