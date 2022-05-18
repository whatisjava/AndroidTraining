package com.whatisjava.training.jetpack.databinding

import androidx.databinding.ObservableField

class Person(var name: String, var age: Int) {

    val _name = ObservableField<String>()
    val _age = ObservableField<Int>()

    init {
        _name.set(name)
        _age.set(age)
    }
}