package com.whatisjava.training.textview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.whatisjava.training.databinding.ActivityTextViewBinding

class TextViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}