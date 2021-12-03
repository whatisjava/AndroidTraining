package com.whatisjava.training.palette

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityPaletteBinding


class PaletteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaletteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaletteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setContentView(R.layout.activity_palette)

        // 用来提取颜色的Bitmap
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_header)
        // Palette的部分
        val builder = Palette.from(bitmap)
        builder.generate { palette -> //获取到充满活力的这种色调
            val vibrant = palette!!.vibrantSwatch
            //根据调色板Palette获取到图片中的颜色设置到toolbar和tab中背景，标题等，使整个UI界面颜色统一
            binding.toolbar.setBackgroundColor(vibrant?.rgb!!)
            val window: Window = window
            window.statusBarColor = vibrant.rgb
            window.navigationBarColor = vibrant.rgb
        }
    }

}