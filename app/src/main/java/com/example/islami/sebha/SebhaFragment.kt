package com.example.islami.sebha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentSebhaBinding
import kotlin.properties.Delegates


class SebhaFragment : Fragment() {
    private lateinit var binding: FragmentSebhaBinding
    private var sebhaCounter by Delegates.notNull<Int>()
    private var userSebhaCounter by Delegates.notNull<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSebhaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeSeb7aCounter()
    }

    private fun changeSeb7aCounter() {
        sebhaCounter = 0
        userSebhaCounter = 0

        binding.seb7aBodyImg.setOnClickListener {
            it.rotation += 10f
            sebhaCounter++
            userSebhaCounter++
            checkChanges()
            binding.sebhaCounterTv.text = userSebhaCounter.toString()
        }
    }

    private fun checkChanges() {
        when (sebhaCounter) {
            in 1..33 -> {
                if (sebhaCounter==1) userSebhaCounter=1
                binding.sebhaZekrTv.text = "سبحان الله"
            }

            in 34..66 -> {
                if (sebhaCounter==34) userSebhaCounter=1
                binding.sebhaZekrTv.text = "الحمد لله"
            }

            in 67..99 -> {
                if (sebhaCounter==67) userSebhaCounter=1
                binding.sebhaZekrTv.text = "الله اكبر"
            }

            100 -> {
                userSebhaCounter=1
                binding.sebhaZekrTv.text =
                    "لا إله إلا الله وحده لا شريك له، له الملك، وله الحمد، وهو على كل شيء قدير"
                sebhaCounter =0
            }


        }
    }
}