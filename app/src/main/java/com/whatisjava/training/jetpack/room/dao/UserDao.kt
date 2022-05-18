package com.whatisjava.training.jetpack.room.dao

import androidx.room.*
import com.whatisjava.training.jetpack.room.entity.User

@Dao
interface UserDao {

    @Insert()
    fun addUser(vararg user: User)

    @Delete()
    fun deleteUser(user: User)

    @Update()
    fun updateUser(user: User)

    @Query(value = "select * from t_user")
    fun queryUsers(): List<User>?

    @Query(value = "select * from t_user where id = :id")
    fun findById(id: Int): User?
}