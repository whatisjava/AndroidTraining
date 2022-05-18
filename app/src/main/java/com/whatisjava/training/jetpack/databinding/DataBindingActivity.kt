package com.whatisjava.training.jetpack.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityDataBindingBinding

private const val TAG = "DataBindingActivity"
class DataBindingActivity : AppCompatActivity() {

    private val user = User("张三", 20)
    private val person = Person("张三", 20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_data_binding)
        val dataBinding = DataBindingUtil.setContentView<ActivityDataBindingBinding>(this, R.layout.activity_data_binding)
        dataBinding.user = user
        dataBinding.person = person
        Log.d(TAG, user.toString())
        Log.d(TAG, person.toString())
        dataBinding.lifecycleOwner = this


        dataBinding.button.setOnClickListener {
            user.name = "李四"
            user.age = 30
            Log.d(TAG, user.toString())
            person.name = "李四"
            person.age = 30
            Log.d(TAG, person.toString())
        }
    }

}