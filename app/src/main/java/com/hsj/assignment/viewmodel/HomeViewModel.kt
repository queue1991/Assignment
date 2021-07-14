package com.hsj.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.hsj.assignment.base.BaseKotlinViewModel
import com.hsj.assignment.common.CommonFireStorePath

class HomeViewModel(private val homeRepository : HomeRepository) : BaseKotlinViewModel(homeRepository) {
    private var _introInfo = MutableLiveData<String>()
    val introInfo: LiveData<String> get() = _introInfo

    private lateinit var docRef : DocumentReference

    /**
     * Firestore에서 앱소개 data 호출 및 UI 적용 (Databinding)
     */
    fun getIntroData(){
        docRef = FirebaseFirestore.getInstance().collection(CommonFireStorePath.COLLECTION_INTRO).document("app1")
        docRef.addSnapshotListener { value, error ->
            _introInfo.postValue(value!!.get(CommonFireStorePath.FILED_INTRO).toString())
        }
    }
}