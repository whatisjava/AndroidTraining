package com.whatisjava.training.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.whatisjava.training.animation.DynamicAnimationActivity
import com.whatisjava.training.databinding.ActivityMainBinding
import com.whatisjava.training.http.HttpActivity
import com.whatisjava.training.imageloader.ImageLoadActivity
import com.whatisjava.training.jetpack.lifecycle.LifeCycleActivity
import com.whatisjava.training.jetpack.livedata.LiveDataActivity
import com.whatisjava.training.jetpack.viewmodel.ViewModelActivity
import com.whatisjava.training.mediaselect.MediaSelectActivity
import com.whatisjava.training.palette.PaletteActivity
import com.whatisjava.training.printer.PrinterActivity
import com.whatisjava.training.recyclerview.GridLayoutActivity
import com.whatisjava.training.room.RoomActivity
import com.whatisjava.training.storage.FileStorageActivity
import com.whatisjava.training.textview.TextViewActivity
import com.whatisjava.training.translucent.TranslucentActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val items = mutableListOf(
        "图片加载框架测试",
        "横向GridLayoutManager",
        "打印图片",
        "透明状态栏",
        "弹簧动画",
        "获取图片主色调",
        "文件存储",
        "Retrofit+LiveData",
        "字重设置",
        "Jetpack Room",
        "多媒体选择",
        "LifeCycle",
        "LiveData",
        "ViewModel",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MainAdapter(this, onItemClickListener, items)

    }

    private val onItemClickListener = object : MainAdapter.OnItemClickListener {
        override fun onClick(position: Int) {
            when (position) {
                0 -> {
                    startActivity(Intent(this@MainActivity, ImageLoadActivity::class.java))
                }
                1 -> {
                    startActivity(Intent(this@MainActivity, GridLayoutActivity::class.java))
                }
                2 -> {
                    startActivity(Intent(this@MainActivity, PrinterActivity::class.java))
                }
                3 -> {
                    startActivity(Intent(this@MainActivity, TranslucentActivity::class.java))
                }
                4 -> {
                    startActivity(Intent(this@MainActivity, DynamicAnimationActivity::class.java))
                }
                5 -> {
                    startActivity(Intent(this@MainActivity, PaletteActivity::class.java))
                }
                6 -> {
                    startActivity(Intent(this@MainActivity, FileStorageActivity::class.java))
                }
                7 -> {
                    startActivity(Intent(this@MainActivity, HttpActivity::class.java))
                }
                8 -> {
                    startActivity(Intent(this@MainActivity, TextViewActivity::class.java))
                }
                9 -> {
                    startActivity(Intent(this@MainActivity, RoomActivity::class.java))
                }
                10 -> {
                    startActivity(Intent(this@MainActivity, MediaSelectActivity::class.java))
                }
                11 -> {
                    startActivity(Intent(this@MainActivity, LifeCycleActivity::class.java))
                }
                12 -> {
                    startActivity(Intent(this@MainActivity, LiveDataActivity::class.java))
                }
                13 -> {
                    startActivity(Intent(this@MainActivity, ViewModelActivity::class.java))
                }
                else -> {

                }
            }
        }

    }
}