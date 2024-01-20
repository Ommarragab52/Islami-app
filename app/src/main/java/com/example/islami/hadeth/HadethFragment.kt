package com.example.islami.hadeth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.islami.CONSTANTS
import com.example.islami.databinding.FragmentHadethBinding
import com.example.islami.hadeth.hadethContent.HadethContentActivity


class HadethFragment : Fragment() {
    private lateinit var binding: FragmentHadethBinding
    private lateinit var adapter: HadethAdapter
    private lateinit var ahadethNamesList: List<String>
    private lateinit var  ahadethList : List<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHadethBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViews()
        onHadethClick()
    }

    private fun onHadethClick() {
        adapter.onHadethItemClick = object :HadethAdapter.OnHadethItemClick{
            override fun onClick(hadethName: String, position: Int) {
                val intent = Intent(this@HadethFragment.context,HadethContentActivity::class.java)
                intent.putExtra(CONSTANTS.HADETH_NAME,hadethName)
                intent.putExtra(CONSTANTS.HADETH_CONTENT,ahadethList[position])
                startActivity(intent)
            }
        }
    }

    private fun getDataFromAssets(): String {
        return activity
            ?.assets
            ?.open("ahadeth.txt")
            ?.bufferedReader()
            .use { it?.readText() ?: "" }
    }

    private fun initData() {
        val ahadeth = getDataFromAssets()
        ahadethList = ahadeth.split("#").map { it.trim() }.filter { it.isNotBlank() }
        ahadethNamesList = ahadethList.map {
            it.lines().firstOrNull().toString().trim()
        }
    }

    private fun initViews() {
        adapter = HadethAdapter(ahadethNamesList)
        binding.hadethRecyclerView.adapter = adapter
        binding.hadethRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }


}
