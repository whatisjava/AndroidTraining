package com.whatisjava.training.jetpack.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.whatisjava.training.R

class LeftViewModelFragment : Fragment() {

//    private val leftViewModel by lazy {
//        ViewModelProvider(this).get(MyViewModel::class.java)
//    }

    private val leftViewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_model_left, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.text_view)

        leftViewModel.getTestNum().observe(viewLifecycleOwner) {
            textView.text = "item: $it"
        }
    }

}