package com.hsj.assignment.fragment.detail

import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.hsj.assignment.R
import com.hsj.assignment.base.BaseKotlinFragment
import com.hsj.assignment.common.CommonFireStorePath
import com.hsj.assignment.common.CommonFireStorePath.COLLECTION_INFO
import com.hsj.assignment.common.CommonFireStorePath.DOCUMENT_INFO
import com.hsj.assignment.databinding.FragmentGoodDetailBinding
import com.hsj.assignment.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_good_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoodDetailFragment : BaseKotlinFragment<FragmentGoodDetailBinding, DetailViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_good_detail
    override val viewModel: DetailViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewDataBinding.viewModel = viewModel
        this.viewDataBinding.fragment = this

        setGoodInfo()
    }

    private fun setGoodInfo(){
        tv_name.text = viewModel.goodName.value.toString()
        tv_price.text = viewModel.goodPrice.value.toString()
        tv_type.text = viewModel.goodType.value.toString()
    }

}