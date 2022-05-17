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
import com.whatisjava.training.databinding.FragmentViewModelLeftBinding

class LeftViewModelFragment : Fragment() {

//    private val leftViewModel by lazy {
//        ViewModelProvider(this).get(MyViewModel::class.java)
//    }

    private var _binding: FragmentViewModelLeftBinding? = null
    private val binding get() = _binding!!

    private val leftViewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewModelLeftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leftViewModel.getTestNum().observe(viewLifecycleOwner) {
            binding.textView.text = "item: $it"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}