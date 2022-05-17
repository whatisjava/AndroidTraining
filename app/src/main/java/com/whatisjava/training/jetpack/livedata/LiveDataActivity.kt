package com.whatisjava.training.jetpack.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityLifeCycleBinding
import com.whatisjava.training.databinding.ActivityLiveDataBinding

private const val TAG = "LiveDataActivity"

class LiveDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataBinding

    private val handler = Handler(Looper.getMainLooper())

    private val testLiveData = MutableLiveData<String>()

    private val mapLiveData = MutableLiveData<Int>()

    private val switchMapLiveData = MutableLiveData<Boolean>()

    private val liveData1 = MutableLiveData<String>()
    private val liveData2 = MutableLiveData<String>()

    private var switchMap = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        testLiveData.observe(this) {
            Log.d(TAG, "接收数据")
            binding.textView.text = it
        }

        binding.button.setOnClickListener {

//            handler.postDelayed({
//                Log.d(TAG, "发送数据")
//                testLiveData.value = "bbbb"
//            }, 3000)

//            testLiveData.value = "bbbb"

//            testLiveData.postValue("bbbb")

//            mapLiveData.value = 1000

            if (switchMap) {
                liveData1.value = "true的值"
            } else {
                liveData2.value = "false的值"
            }
            switchMapLiveData.value = switchMap
            switchMap = !switchMap
        }

        val liveData = Transformations.map(mapLiveData) {
            "test $it"
        }
        liveData.observe(this) {
            binding.textView.text = it
        }

        val switchMapLiveData = Transformations.switchMap(switchMapLiveData) { input ->
            if (input == true) {
                liveData1
            } else {
                liveData2
            }
        }

        switchMapLiveData.observe(this) {
            binding.textView.text = it
        }

    }
}