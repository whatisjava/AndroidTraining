package com.whatisjava.training.recyclerview

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityGridLayoutBinding

class GridLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridLayoutBinding

    private val items = mutableListOf<String>()

    private fun initData() {
        for (i in 0..20) {
            items.add("item $i")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initData()

//        val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
//        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                return if (position == 4) {
//                    1
//                } else {
//                    1
//                }
//            }
//        }

//        val itemDecoration = SpacesItemDecoration(this, SpacesItemDecoration.HORIZONTAL)
//        itemDecoration.setDrawable(R.drawable.divider_w8)
//        binding.recyclerView.addItemDecoration(itemDecoration)

        val layoutManager = LooperLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        layoutManager.setLooperEnable(true)
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.adapter = LooperLayoutAdapter(this, items)

        val speedScroll = 100L
        val handler = Handler()
        val runnable = object: Runnable {
            var count = 0
            var flag = true
            override fun run() {
//                if (count < binding.recyclerView.adapter?.itemCount!!) {
//                    if (count == binding.recyclerView.adapter?.itemCount!! - 1) {
//                        flag = false
//                    } else if(count == 0) {
//                        flag = true
//                    }
//                    if (flag) count++ else count--
//                    binding.recyclerView.smoothScrollBy(10, 0) // .smoothScrollToPosition(count)
//                    handler.postDelayed(this, speedScroll)
//                }
                binding.recyclerView.smoothScrollBy(10, 0)
                handler.postDelayed(this, speedScroll)
            }
        }
        handler.postDelayed(runnable, speedScroll)
    }

}