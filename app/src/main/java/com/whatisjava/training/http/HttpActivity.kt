package com.whatisjava.training.http

import android.os.Bundle
import android.widget.Toast
import com.whatisjava.training.databinding.ActivityHttpBinding
import com.whatisjava.training.http.base.BaseActivity
import com.whatisjava.training.http.viewmodel.DemoViewModel

class HttpActivity : BaseActivity() {

    private lateinit var binding: ActivityHttpBinding

    private lateinit var demoViewModel: DemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        demoViewModel = getViewModel(DemoViewModel::class.java)
        demoViewModel.msgLiveData.observe(this, {
            binding.textView.text = it.toString()
        })
        demoViewModel.errorLiveData.observe(this, {
            Toast.makeText(this@HttpActivity, "${it.message}", Toast.LENGTH_SHORT).show()
        })

        binding.loginButton.setOnClickListener {
            demoViewModel.sendVerifyCode("+86", "18610122319", "LOGIN")
        }

    }
}