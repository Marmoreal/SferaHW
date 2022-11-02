package com.example.sferahw.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.sferahw.R
import com.example.sferahw.databinding.ItemListSubscriptionsBinding
import com.example.sferahw.domain.model.User
import javax.inject.Inject

class PeopleListAdapter @Inject constructor(
    private val glide: RequestManager
) : ListAdapter<User, PeopleListAdapter.PeopleViewHolder>(PeopleItemCallback) {

    private var onItemClick: ((Int) -> Unit)? = null

    fun setOnItemClick(callback: (Int) -> Unit) {
        onItemClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return PeopleViewHolder(
            ItemListSubscriptionsBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PeopleViewHolder(
        private val binding: ItemListSubscriptionsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            with(binding) {
                glide.load(item.url).into(image)
                txtName.text = item.name
                btnSubscribe.setOnClickListener {
                    onItemClick?.invoke(item.id)
                }

                when (item.isSubscribed) {
                    true -> {
                        with(btnSubscribe) {
                            text = context.getString(R.string.unsubscribe)
                            setTextColor(getColor(context, R.color.on_surface60))
                        }
                    }
                    false -> {
                        with(btnSubscribe) {
                            text = context.getString(R.string.subscribe)
                            setTextColor(getColor(context, R.color.purple_200))
                        }
                    }
                }
            }
        }
    }

    private object PeopleItemCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: User, newItem: User): Any? {
            return if (oldItem.isSubscribed != newItem.isSubscribed) true else null
        }
    }
}
