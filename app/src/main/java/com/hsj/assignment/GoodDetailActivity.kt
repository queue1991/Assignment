package com.hsj.assignment

import android.util.Log
import com.hsj.assignment.base.BaseKotlinActivity
import com.hsj.assignment.common.CommonIntentKey.INTENT_KEY_DOCUMENT_FROM_HOME_TO_DETAIL_ACTIVITY
import com.hsj.assignment.common.CommonIntentKey.INTENT_KEY_GOODNAME_FROM_HOME_TO_DETAIL_ACTIVITY
import com.hsj.assignment.common.CommonIntentKey.INTENT_KEY_GOODPRICE_FROM_HOME_TO_DETAIL_ACTIVITY
import com.hsj.assignment.common.CommonIntentKey.INTENT_KEY_GOODTYPE_FROM_HOME_TO_DETAIL_ACTIVITY
import com.hsj.assignment.databinding.ActivityDetailBinding
import com.hsj.assignment.fragment.detail.DetailFragment
import com.hsj.assignment.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoodDetailActivity : BaseKotlinActivity<ActivityDetailBinding, DetailViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_detail
    override val viewModel: DetailViewModel by viewModel()
    override val fragmentPlace: Int
        get() = R.id.container

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
        /**
         * ActionBar 삭제 / ToolBar 띄우기
         */
        supportActionBar?.let {
            it.hide()
        }

        viewModel.setDocument(intent.getStringExtra(INTENT_KEY_DOCUMENT_FROM_HOME_TO_DETAIL_ACTIVITY).toString())
        viewModel.setGoodName(intent.getStringExtra(INTENT_KEY_GOODNAME_FROM_HOME_TO_DETAIL_ACTIVITY).toString())
        viewModel.setGoodPrice(intent.getStringExtra(INTENT_KEY_GOODPRICE_FROM_HOME_TO_DETAIL_ACTIVITY).toString())
        viewModel.setGoodType(intent.getStringExtra(INTENT_KEY_GOODTYPE_FROM_HOME_TO_DETAIL_ACTIVITY).toString())

        replaceFragment(DetailFragment())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}