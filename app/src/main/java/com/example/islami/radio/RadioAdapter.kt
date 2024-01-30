package com.example.islami.radio

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.R
import com.example.islami.databinding.ItemRadioBinding
import com.example.islami.radio.api.model.RadiosItem

class RadioAdapter(private var radioList: MutableList<RadiosItem?>? = null) :
    Adapter<RadioAdapter.RadioViewHolder>() {
    class RadioViewHolder(val binding: ItemRadioBinding) : ViewHolder(binding.root) {

    }

    fun bindItems(radioList: MutableList<RadiosItem?>?) {
        this.radioList = radioList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val binding = ItemRadioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RadioViewHolder(binding)
    }

    var onPlayStopRadioClick: OnPlayStopRadioClick? = null
    var onBackwardClick: OnBackwardClick? = null
    var onForwardClick: OnForwardClick? = null

    override fun getItemCount(): Int {
        return radioList?.size ?: 0
    }


    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val radio = radioList?.get(position)
        holder.binding.radioTitle.text = radio?.name
        holder.binding.playStopRadio.let { playStop ->
            fun isNightMode(): Boolean {
                val uiModeFlag = playStop.resources.configuration
                    .uiMode and Configuration.UI_MODE_NIGHT_MASK
                return uiModeFlag == Configuration.UI_MODE_NIGHT_YES
            }

            playStop.setOnClickListener {
                onPlayStopRadioClick?.onClick(position, radio!!)
                playStop.setImageResource(if (!isNightMode()) R.drawable.ic_stop else R.drawable.ic_stop_night)
            }
        }
        holder.binding.backwardRadio.setOnClickListener {
            onBackwardClick?.onClick(position, radio!!)
        }
        holder.binding.forwardRadio.setOnClickListener {
            onForwardClick?.onClick(position, radio!!)
        }
    }

    fun interface OnPlayStopRadioClick {
        fun onClick(position: Int, radio: RadiosItem)
    }

    fun interface OnBackwardClick {
        fun onClick(position: Int, radio: RadiosItem)
    }

    fun interface OnForwardClick {
        fun onClick(position: Int, radio: RadiosItem)
    }
}