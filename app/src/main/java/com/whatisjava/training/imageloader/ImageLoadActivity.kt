package com.whatisjava.training.imageloader

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityImageLoadBinding
import jp.wasabeef.blurry.Blurry

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

        Thread.sleep(1000)

        Blurry.with(this)
            .radius(25)
            .sampling(8)
            .color(Color.argb(66, 255, 255, 0))
            .async()
            .animate(500)
            .onto(binding.linearLayout)

        // Sync
//        val bitmap = Blurry.with(this)
//            .radius(10)
//            .sampling(8)
//            .capture(binding.imageView).get()
//        binding.imageView1.setImageDrawable(BitmapDrawable(resources, bitmap))

        // Async
//        Blurry.with(this)
//            .radius(25)
//            .sampling(4)
//            .color(Color.argb(66, 255, 255, 0))
//            .capture(binding.imageView)
//            .getAsync {
//                binding.imageView1.setImageDrawable(BitmapDrawable(resources, it))
//            }
    }

}