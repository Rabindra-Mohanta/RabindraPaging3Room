package com.example.tipukutta.screens.home.pagingAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tipukutta.R
import com.example.tipukutta.databinding.ItemTipuBinding
import com.example.tipukutta.screens.home.model.PhotosData

class HomeAdapter() : PagingDataAdapter<PhotosData, HomeAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.bind<ItemTipuBinding>(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_tipu, parent, false
            )
        )
        return ViewHolder(binding!!)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bindData(it)
        }

    }

    inner class ViewHolder(private val binding: ItemTipuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: PhotosData) {
            binding.textRabi = data.title
        }

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PhotosData>() {
            override fun areItemsTheSame(oldItem: PhotosData, newItem: PhotosData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotosData, newItem: PhotosData): Boolean {
                return oldItem == newItem
            }

        }
    }


}

