package com.example.islami.hadeth.hadethContent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.islami.CONSTANTS
import com.example.islami.databinding.ActivityHadethContentBinding

class HadethContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHadethContentBinding
    private lateinit var hadethName: String
    private lateinit var hadethContent: List<String>
    private lateinit var adapter: HadethContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHadethContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        initViews()
        initData()
        bindData()
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    private fun bindData() {
        adapter.bindItems(hadethContent)
    }

    private fun initData() {
        hadethName = intent.getStringExtra(CONSTANTS.HADETH_NAME).toString()
        val hadeth = intent.getStringExtra(CONSTANTS.HADETH_CONTENT).toString()
        hadethContent = hadeth.substringAfter("\n").lines()
    }

    private fun initViews() {
        binding.hadethNameTv.text = hadethName
        adapter = HadethContentAdapter(null)
        binding.hadethRecyclerView.adapter = adapter
        binding.hadethRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}