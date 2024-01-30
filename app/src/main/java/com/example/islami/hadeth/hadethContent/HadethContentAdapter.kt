package com.example.islami.hadeth.hadethContent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.databinding.ItemHadethContentBinding

class HadethContentAdapter(private var hadethList: List<String>? = null) :
    Adapter<HadethContentAdapter.HadethContentViewHolder>() {
    class HadethContentViewHolder(private val binding: ItemHadethContentBinding) :
        ViewHolder(binding.root) {
        fun bind(hadeth: String) {
            binding.hadethTv.text = hadeth
        }
    }

    fun bindItems(list: List<String>) {
        hadethList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadethContentViewHolder {
        val binding =
            ItemHadethContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HadethContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HadethContentViewHolder, position: Int) {
        val item = hadethList?.get(position)
        holder.bind(item ?: "")
    }

    override fun getItemCount(): Int {
        return hadethList?.size ?: 0
    }
}

