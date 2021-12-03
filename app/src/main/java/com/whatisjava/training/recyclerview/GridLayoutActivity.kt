package com.whatisjava.training.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.whatisjava.training.databinding.ActivityGridLayoutBinding

class GridLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridLayoutBinding

    private val items = mutableListOf(
        "test1", "test2", "test3", "test4", "test5"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 4) {
                    1
                } else {
                    1
                }
            }
        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(2, 10, true))
        binding.recyclerView.adapter = GridLayoutAdapter(this, items)

    }

}