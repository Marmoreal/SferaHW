package com.example.sferahw.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sferahw.databinding.ItemListMomentsBinding
import com.example.sferahw.domain.model.Image

class MomentsListAdapter :
    ListAdapter<Image, MomentsListAdapter.MomentsViewHolder>(PhotoItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return MomentsViewHolder(
            ItemListMomentsBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MomentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MomentsViewHolder(
        private val binding: ItemListMomentsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) {
            binding.moment.load(item.url)
        }
    }

    private object PhotoItemCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }
}