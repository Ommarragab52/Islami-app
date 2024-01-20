package com.example.islami.quran.quranContent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.databinding.ItemSuraContentBinding

class SuraContentAdapter(private val sura: List<String>? = null) :
    Adapter<SuraContentAdapter.SuraContentViewHolder>() {
    class SuraContentViewHolder(private val binding: ItemSuraContentBinding) : ViewHolder(binding.root) {
        fun bind(ayah: String) {
            binding.ayahTv.text = ayah
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraContentViewHolder {
        val binding =
            ItemSuraContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuraContentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sura?.size ?: 0
    }

    override fun onBindViewHolder(holder: SuraContentViewHolder, position: Int) {
        val item = sura?.get(position)
        holder.bind(item ?: "null")
    }
}