package com.whatisjava.training.http

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TAG = "okhttp"

object RetrofitManager {

    private const val timeout = 10L

    private val mOkClientBuilder = OkHttpClient.Builder()
        .callTimeout(timeout, TimeUnit.SECONDS)
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .writeTimeout(timeout, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        .addInterceptor(HttpLoggingInterceptor { message ->
            Log.d(TAG, "$message")
        }.setLevel(HttpLoggingInterceptor.Level.BODY))

    private val mOkClient = mOkClientBuilder.build()

    private var mRetrofit: Retrofit? = null

    fun buildRetrofit(url: String = "https://test.blcclb.com"): RetrofitManager {
        mRetrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(mOkClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this
    }

    fun <T> getService(serviceClass: Class<T>): T {
        if (mRetrofit != null) {
            return mRetrofit!!.create(serviceClass)
        } else {
            throw UninitializedPropertyAccessException("Retrofit必须先初始化")
        }
    }
}