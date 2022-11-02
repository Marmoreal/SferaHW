package com.example.sferahw.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.sferahw.databinding.ItemListMomentsBinding
import com.example.sferahw.domain.model.Image
import javax.inject.Inject

class MomentsListAdapter @Inject constructor(
    private val glide: RequestManager
): ListAdapter<Image, MomentsListAdapter.MomentsViewHolder>(MomentsItemCallback) {

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
            glide.load(item.url).into(binding.moment)
        }
    }

    private object MomentsItemCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }
}