package com.example.islami.hadeth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.databinding.ItemHadethNameBinding

class HadethAdapter(private val ahadethNamesList: List<String>? = null) :
    Adapter<HadethAdapter.HadethViewHolder>() {
    class HadethViewHolder(private val binding: ItemHadethNameBinding) : ViewHolder(binding.root) {
        fun bind(name: String) {
            binding.hadethNameTv.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadethViewHolder {
        val binding = ItemHadethNameBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HadethViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ahadethNamesList?.size ?: 0
    }
    var onHadethItemClick : OnHadethItemClick?=null

    override fun onBindViewHolder(holder: HadethViewHolder, position: Int) {
        val item = ahadethNamesList?.get(position)
        holder.bind(item?:"")
        holder.itemView.setOnClickListener {
            onHadethItemClick?.onClick(item?:"",position)
        }
    }

    interface OnHadethItemClick{
        fun onClick(hadethName: String, position: Int)
    }

}