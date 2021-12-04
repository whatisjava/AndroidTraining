package com.whatisjava.training.http.api

import com.whatisjava.training.http.model.ApiResponse
import com.whatisjava.training.http.model.Msg
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/simulv2/common/verifyCode")
    suspend fun sendVerifyCode(
        @Query("areaCode") areaCode: String,
        @Query("phone") mobile: String,
        @Query("type") type: String
    ): ApiResponse<Msg>

}