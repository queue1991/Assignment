package com.hsj.assignment.fragment.home

import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.*
import com.hsj.assignment.R
import com.hsj.assignment.base.BaseKotlinFragment
import com.hsj.assignment.common.CommonFireStorePath.COLLECTION_INTRO
import com.hsj.assignment.common.CommonFireStorePath.FILED_INTRO
import com.hsj.assignment.databinding.FragmentIntroBinding
import com.hsj.assignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_intro.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroFragment : BaseKotlinFragment<FragmentIntroBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_intro
    override val viewModel: HomeViewModel by viewModel()

    private lateinit var docRef : DocumentReference

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        docRef = FirebaseFirestore.getInstance().collection(COLLECTION_INTRO).document("app1")
        docRef.addSnapshotListener { value, error ->
            tv_intro.text = value!!.get(FILED_INTRO).toString()
        }
    }

}