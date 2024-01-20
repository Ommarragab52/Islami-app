package com.example.islami.hadeth.hadethContent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.islami.CONSTANTS
import com.example.islami.databinding.ActivityHadethContentBinding

class HadethContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHadethContentBinding
    private lateinit var hadethName: String
    private lateinit var hadethContent: String
    private lateinit var adapter: HadethContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHadethContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setData()
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setData() {
        hadethName = intent.getStringExtra(CONSTANTS.HADETH_NAME).toString()
        hadethContent = intent.getStringExtra(CONSTANTS.HADETH_CONTENT).toString()
        val hadeth = hadethContent.substringAfter("\n")
        binding.hadethNameTv.text = hadethName
        adapter = HadethContentAdapter(listOf(hadeth))
        binding.hadethRecyclerView.adapter = adapter
        binding.hadethRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}