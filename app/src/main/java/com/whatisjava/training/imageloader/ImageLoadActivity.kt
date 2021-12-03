package com.whatisjava.training.imageloader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityImageLoadBinding

class ImageLoadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageLoadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageLoadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // https://sp.simullink.com/FhvXiuJi1xpKoiDETrKUEjJPsWTy

        binding.simpleDraweeView.setImageURI("https://sp.simullink.com/FhvXiuJi1xpKoiDETrKUEjJPsWTy")

        val picasso = Picasso.get()
        picasso.setIndicatorsEnabled(true)
        picasso.load("https://sp.simullink.com/FhvXiuJi1xpKoiDETrKUEjJPsWTy?imageslim")
            .placeholder(R.drawable.bg_default_image)
            .error(R.drawable.bg_default_image)
            .into(binding.imageView)

    }

}