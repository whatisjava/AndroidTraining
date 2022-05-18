package com.whatisjava.training.jetpack.paging

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.luck.picture.lib.utils.ToastUtils
import com.whatisjava.training.databinding.ActivityPaging3Binding
import com.whatisjava.training.jetpack.paging.adpt.FooterAdapter
import com.whatisjava.training.jetpack.paging.adpt.PagingAdapter
import com.whatisjava.training.jetpack.paging.viewmodel.GithubRepoViewModel
import kotlinx.coroutines.launch

private const val TAG = "Paging3Activity"

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

        binding.recyclerView.adapter = repoAdapter.withLoadStateFooter(FooterAdapter {
            repoAdapter.retry()
        })

        val githubServerData = repoViewModel.getGithubServerData()
        lifecycleScope.launch {
            githubServerData.collect {
                repoAdapter.submitData(lifecycle, it)
            }
        }

        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    Log.d(TAG, "${state.error.message}")
                    ToastUtils.showToast(this, "${state.error.message}")
                }
            }
        }

    }

}