package com.whatisjava.training.http.viewmodel

import androidx.lifecycle.MutableLiveData
import com.whatisjava.training.http.ResState
import com.whatisjava.training.http.base.BaseViewModel
import com.whatisjava.training.http.model.Msg
import com.whatisjava.training.http.repository.DemoRepository

class DemoViewModel : BaseViewModel() {

    val msgLiveData = MutableLiveData<Msg>()

    private val mRepository = DemoRepository()

    fun sendVerifyCode(
        areaCode: String,
        mobile: String,
        type: String
    ) {
        launch(
            {
                val state = mRepository.sendVerifyCode(areaCode, mobile, type)
                if (state is ResState.Success) {
                    msgLiveData.postValue(state.data!!)
                } else if (state is ResState.Error) {
                    errorLiveData.postValue(state.exception)
                }
            },
            {
                errorLiveData.postValue(it)
            },
            {
                loadingLiveData.postValue(false)
            }
        )
    }
}