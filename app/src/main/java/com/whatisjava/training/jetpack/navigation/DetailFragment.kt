package com.whatisjava.training.jetpack.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.whatisjava.training.R
import com.whatisjava.training.databinding.FragmentDetailBinding
import com.whatisjava.training.databinding.FragmentHomeBinding

private const val TAG = "DetailFragment"
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.action_detailFragment_to_homeFragment)
        }

        val name = arguments?.getString("name")
        Log.d(TAG, "onViewCreated: $name")
    }
}