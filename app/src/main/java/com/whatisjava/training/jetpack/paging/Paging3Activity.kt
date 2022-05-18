package com.whatisjava.training.jetpack.paging

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.whatisjava.training.databinding.ActivityPaging3Binding
import com.whatisjava.training.jetpack.paging.adpt.PagingAdapter
import com.whatisjava.training.jetpack.paging.viewmodel.GithubRepoViewModel
import kotlinx.coroutines.launch

class Paging3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPaging3Binding

    private val repoViewModel by viewModels<GithubRepoViewModel>()

    private val repoAdapter by lazy {
        PagingAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaging3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.adapter = repoAdapter

        val githubServerData = repoViewModel.getGithubServerData()
        lifecycleScope.launch {
            githubServerData.collect {
                repoAdapter.submitData(lifecycle, it)
            }
        }

    }

}