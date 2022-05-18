package com.whatisjava.training.jetpack.paging.adpt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.whatisjava.training.databinding.ItemRepoBinding
import com.whatisjava.training.jetpack.paging.model.Item

class PagingAdapter : PagingDataAdapter<Item, PagingAdapter.PagingViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        holder.nameText.text = item?.name
        holder.descText.text = item?.description
        holder.starCountText.text = item?.stargazers_count.toString()
    }

    class PagingViewHolder(binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameText = binding.nameText
        val descText = binding.descriptionText
        val starCountText = binding.starCountText

    }

}