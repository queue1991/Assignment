package com.hsj.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hsj.assignment.base.BaseKotlinViewModel

class DetailViewModel(private val detailRepository : DetailRepository) : BaseKotlinViewModel(detailRepository) {
    /**
     * 상품 Review의 Collection Path를 찾기 위한 변수
     */
    private var _document = MutableLiveData<String>()
    val document: LiveData<String> get() = _document

    private var _goodName = MutableLiveData<String>()
    val goodName: LiveData<String> get() = _goodName

    private var _goodPrice = MutableLiveData<String>()
    val goodPrice: LiveData<String> get() = _goodPrice

    private var _goodType = MutableLiveData<String>()
    val goodType: LiveData<String> get() = _goodType

    /**
     * Toolbar 상품이름 Setting
     */
    fun setDocument(documentPath : String){
        _document.value = documentPath
    }

    fun setGoodName(goodName : String){
        _goodName.value = goodName
    }

    fun setGoodPrice(goodPrice : String){
        _goodPrice.value = goodPrice
    }

    fun setGoodType(goodType : String){
        _goodType.value = goodType
    }


}