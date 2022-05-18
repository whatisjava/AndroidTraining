package com.whatisjava.training.jetpack.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.whatisjava.training.databinding.ActivityRoomBinding
import com.whatisjava.training.jetpack.room.entity.User
import java.util.*

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding

    private val userDao by lazy {
        DatabaseManager.getInstance(this).getUserDao()
    }

    private var usersLiveData = MutableLiveData<List<User>?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addButton.setOnClickListener {
            Thread{
                val user = User(id = UUID.randomUUID().toString(), name = "张三", age = 22)
                userDao.addUser(user)
            }.start()
        }

        binding.delButton.setOnClickListener {
            Thread{
                val users = userDao.queryUsers()
                if (!users.isNullOrEmpty()) {
                    userDao.deleteUser(users[0])
                }
            }.start()
        }

        binding.updateButton.setOnClickListener {
            Thread{
//                val user = userDao.findById(1)
                val users = userDao.queryUsers()
                if (!users.isNullOrEmpty()) {
                    val user = users[0]
                    user.name = "李四"
                    user.age = 32
                    userDao.updateUser(user)
                }
            }.start()
        }

        binding.queryButton.setOnClickListener {
            Thread{
                val users = userDao.queryUsers()
                usersLiveData.postValue(users)
            }.start()
        }

        usersLiveData.observe(this) {
            binding.textView.text = it.toString()
        }
    }

}