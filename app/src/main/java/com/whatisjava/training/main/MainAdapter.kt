package com.whatisjava.training.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whatisjava.training.databinding.ItemMainBinding

class MainAdapter(
    private val context: Context,
    private val onItemClickListener: OnItemClickListener,
    private val items: List<String>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.titleText.text = items[position]
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position)
        }
    }


    class MainViewHolder(binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleText = binding.titleText
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

}