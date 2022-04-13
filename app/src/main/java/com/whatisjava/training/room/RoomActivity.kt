package com.whatisjava.training.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.whatisjava.training.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}