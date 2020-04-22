package com.mvvm.testproject.ui.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val homedatalist: ArrayList<String> = ArrayList()
    private val mutableLiveData: MutableLiveData<List<String>> =
        MutableLiveData<List<String>>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text

    fun getMutableLiveData(): MutableLiveData<List<String>> {
        val itemList: ArrayList<String> = ArrayList()
        itemList.add("कुंडली")
        itemList.add("कुंडली मिलन")
        itemList.add("राशी फल")
        itemList.add("वर्ष फल")
        itemList.add("लाल किताब")
        itemList.add("एस्ट्रोशाप")
        mutableLiveData.setValue(itemList);
        return mutableLiveData
    }
}