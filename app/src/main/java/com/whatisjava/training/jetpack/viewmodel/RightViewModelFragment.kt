package com.whatisjava.training.jetpack.viewmodel

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.whatisjava.training.R

class RightViewModelFragment : Fragment() {

//    private val rightViewModel by lazy {
//        ViewModelProvider(this).get(MyViewModel::class.java)
//    }

    private val rightViewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_model_right, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.text_view)

        rightViewModel.getTestNum().observe(viewLifecycleOwner) {
            textView.text = "item: $it"
        }
    }

}