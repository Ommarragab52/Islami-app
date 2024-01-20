package com.example.islami.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.databinding.ItemSuraNameBinding

class QuranAdapter(private val suraList: List<String>?) :
    Adapter<QuranAdapter.QuranViewHolder>() {
    class QuranViewHolder(val binding: ItemSuraNameBinding) : ViewHolder(binding.root) {
        fun bind(name: String, position: Int) {
            binding.suraNameTv.text = name
            binding.suraNumberTv.text = "${position + 1}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuranViewHolder {
        val binding = ItemSuraNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuranViewHolder(binding)
    }
    lateinit var onItemClick: OnItemClick
    override fun onBindViewHolder(holder: QuranViewHolder, position: Int) {
        suraList?.get(position)?.let {
            holder.bind(it, position)
        }
        holder.binding.root.setOnClickListener {
            onItemClick.onClick(suraList?.get(position) ?: "None",position+1)
        }

    }

    override fun getItemCount() = suraList?.size ?: 0
   fun interface OnItemClick{
        fun onClick(suraName:String, position: Int)
    }
}