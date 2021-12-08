package com.whatisjava.training.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whatisjava.training.databinding.ItemGridLayoutBinding

class LooperLayoutAdapter(
    private val context: Context,
    private val items: List<String>
) : RecyclerView.Adapter<LooperLayoutAdapter.GridLayoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridLayoutViewHolder {
        val binding = ItemGridLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridLayoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridLayoutViewHolder, position: Int) {
        holder.titleText.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class GridLayoutViewHolder(binding: ItemGridLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleText = binding.titleText
    }

}