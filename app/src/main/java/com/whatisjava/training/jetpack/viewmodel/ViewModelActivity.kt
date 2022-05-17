package com.whatisjava.training.jetpack.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityLiveDataBinding
import com.whatisjava.training.databinding.ActivityViewModelBinding

class ViewModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewModelBinding

    private val myViewModel by lazy {
        ViewModelProvider(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        myViewModel.getTestNum().observe(this) {
            binding.textView.text = "$it"
        }

        binding.button.setOnClickListener {
//            i++
//            binding.textView.text = i.toString()
            myViewModel.add()
        }

    }
}