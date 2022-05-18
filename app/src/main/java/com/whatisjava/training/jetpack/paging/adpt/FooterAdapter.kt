package com.whatisjava.training.jetpack.paging.adpt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.whatisjava.training.databinding.ItemFooterBinding

class FooterAdapter(val retry: () -> Unit): LoadStateAdapter<FooterAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): VH {
        val binding = ItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.retryButton.isVisible = loadState is LoadState.Error
        holder.retryButton.setOnClickListener {
            retry()
        }
    }

    class VH(binding: ItemFooterBinding) : RecyclerView.ViewHolder(binding.root){
        val progressBar = binding.progressBar
        val retryButton = binding.retryButton
    }

}