package com.whatisjava.training.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    private var i = 0
    private val testNum = MutableLiveData<Int>()

    fun getTestNum(): LiveData<Int>{
        return testNum
    }

    fun add(){
        i++
        testNum.value = i
    }

}