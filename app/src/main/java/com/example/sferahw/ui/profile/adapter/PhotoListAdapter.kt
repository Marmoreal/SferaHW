package com.example.sferahw.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sferahw.databinding.ItemListProfilePhotoBinding
import com.example.sferahw.domain.model.Image

class PhotoListAdapter : ListAdapter<Image, PhotoListAdapter.PhotoViewHolder>(PhotoItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return PhotoViewHolder(
            ItemListProfilePhotoBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PhotoViewHolder(
        private val binding: ItemListProfilePhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) {
            Glide.with(binding.root.context).load(item.url).into(binding.photo)
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