package com.whatisjava.training.http

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.whatisjava.training.databinding.ActivityHttpBinding
import com.whatisjava.training.http.base.BaseActivity
import com.whatisjava.training.http.viewmodel.DemoViewModel

class HttpActivity : BaseActivity() {

    private lateinit var binding: ActivityHttpBinding

    private val demoViewModel: DemoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        demoViewModel.msgLiveData.observe(this) {
            binding.textView.text = it.toString()
        }
        demoViewModel.loadingLiveData.observe(this) {
            if (it) {
                Log.d("loading", "begin")
            } else {
                Log.d("loading", "end")
            }
        }
        demoViewModel.errorLiveData.observe(this) {
            Toast.makeText(this@HttpActivity, it.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.loginButton.setOnClickListener {
            demoViewModel.sendVerifyCode("+86", "18610122319", "LOGIN")
        }

    }
}