package com.example.sferahw.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sferahw.databinding.ItemListChroniclesBinding
import com.example.sferahw.domain.model.Image

class ChroniclesListAdapter :
    ListAdapter<Image, ChroniclesListAdapter.ChroniclesViewHolder>(ChroniclesItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChroniclesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ChroniclesViewHolder(
            ItemListChroniclesBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChroniclesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ChroniclesViewHolder(
        private val binding: ItemListChroniclesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) {
            Glide.with(binding.root.context).load(item.url).into(binding.chronicles)
        }
    }

    private object ChroniclesItemCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }
}