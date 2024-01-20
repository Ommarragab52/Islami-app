package com.example.islami.quran.quranContent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.islami.CONSTANTS
import com.example.islami.databinding.ActivitySuraContentBinding
import kotlin.properties.Delegates

class SuraContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuraContentBinding
    private lateinit var adapter: SuraContentAdapter
    private lateinit var sura: List<String>
    private lateinit var suraName: String
    private var suraIndex by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuraContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setSuraContent()
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    private fun setSuraContent() {
        suraName = intent.getStringExtra(CONSTANTS.SURA_NAME) ?: ""
        suraIndex = intent.getIntExtra(CONSTANTS.SURA_INDEX, 1)
        binding.suraNameTv.text = "سورة " + suraName
        val suraFile = "$suraIndex.txt"
        val suraText = application
            .assets
            .open(suraFile)
            .bufferedReader()
            .use { it.readText() }
        sura = suraText
            .split("\n")
            .filter { it.isNotBlank() }
            .mapIndexed { index, ayah ->
                ayah + "(${index + 1})"
            }
        adapter = SuraContentAdapter(sura)
        binding.suraRecyclerView.adapter = adapter
        binding.suraRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}