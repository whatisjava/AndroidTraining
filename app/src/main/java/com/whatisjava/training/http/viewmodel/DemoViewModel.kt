package com.whatisjava.training.http.viewmodel

import androidx.lifecycle.MutableLiveData
import com.whatisjava.training.http.RespState
import com.whatisjava.training.http.api.ApiPathName
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
                val respState = mRepository.sendVerifyCode(areaCode, mobile, type)
                if (respState is RespState.Success) {
                    msgLiveData.postValue(respState.data!!)
                } else if (respState is RespState.Error) {
                    errorLiveData.postValue(respState.exception)
                }
            },
            {
                it.pathName = ApiPathName.SEND_VERIFY_CODE
                errorLiveData.postValue(it)
            },
            {
                loadingLiveData.postValue(false)
            }
        )
    }
}