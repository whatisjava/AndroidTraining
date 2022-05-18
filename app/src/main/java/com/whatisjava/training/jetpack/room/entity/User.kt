package com.whatisjava.training.jetpack.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_user")
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "id") val id: String? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "age") var age: Int? = null,
)