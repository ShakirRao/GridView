package com.example.gridview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {

    val pointer by lazy { MutableLiveData<Int>().apply{value = -1} }

}