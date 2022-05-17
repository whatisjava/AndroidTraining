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
import com.whatisjava.training.databinding.FragmentViewModelLeftBinding
import com.whatisjava.training.databinding.FragmentViewModelRightBinding

class RightViewModelFragment : Fragment() {

//    private val rightViewModel by lazy {
//        ViewModelProvider(this).get(MyViewModel::class.java)
//    }

    private var _binding: FragmentViewModelRightBinding? = null
    private val binding get() = _binding!!

    private val rightViewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewModelRightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rightViewModel.getTestNum().observe(viewLifecycleOwner) {
            binding.textView.text = "item: $it"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}