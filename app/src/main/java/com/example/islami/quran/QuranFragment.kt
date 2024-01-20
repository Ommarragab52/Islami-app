package com.example.islami.quran

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.islami.CONSTANTS
import com.example.islami.databinding.FragmentQuranBinding
import com.example.islami.quran.quranContent.SuraContentActivity


class QuranFragment : Fragment() {
    private lateinit var binding: FragmentQuranBinding
    private lateinit var adapter: QuranAdapter
    private lateinit var suraList: List<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViews()
        onSuraClick()
    }

    private fun onSuraClick() {
        adapter.onItemClick = QuranAdapter.OnItemClick { suraName, position ->
            val intent = Intent(this@QuranFragment.context, SuraContentActivity::class.java)
            intent.putExtra(CONSTANTS.SURA_NAME,suraName)
            intent.putExtra(CONSTANTS.SURA_INDEX,position)
            startActivity(intent)
        }
    }

    private fun initData() {
        suraList = listOf(
            "الفاتحه",
            "البقرة",
            "آل عمران",
            "النساء",
            "المائدة",
            "الأنعام",
            "الأعراف",
            "الأنفال",
            "التوبة",
            "يونس",
            "هود",
            "يوسف",
            "الرعد",
            "إبراهيم",
            "الحجر",
            "النحل",
            "الإسراء",
            "الكهف",
            "مريم",
            "طه",
            "الأنبياء",
            "الحج",
            "المؤمنون",
            "النّور",
            "الفرقان",
            "الشعراء",
            "النّمل",
            "القصص",
            "العنكبوت",
            "الرّوم",
            "لقمان",
            "السجدة",
            "الأحزاب",
            "سبأ",
            "فاطر",
            "يس",
            "الصافات",
            "ص",
            "الزمر",
            "غافر",
            "فصّلت",
            "الشورى",
            "الزخرف",
            "الدّخان",
            "الجاثية",
            "الأحقاف",
            "محمد",
            "الفتح",
            "الحجرات",
            "ق",
            "الذاريات",
            "الطور",
            "النجم",
            "القمر",
            "الرحمن",
            "الواقعة",
            "الحديد",
            "المجادلة",
            "الحشر",
            "الممتحنة",
            "الصف",
            "الجمعة",
            "المنافقون",
            "التغابن",
            "الطلاق",
            "التحريم",
            "الملك",
            "القلم",
            "الحاقة",
            "المعارج",
            "نوح",
            "الجن",
            "المزّمّل",
            "المدّثر",
            "القيامة",
            "الإنسان",
            "المرسلات",
            "النبأ",
            "النازعات",
            "عبس",
            "التكوير",
            "الإنفطار",
            "المطفّفين",
            "الإنشقاق",
            "البروج",
            "الطارق",
            "الأعلى",
            "الغاشية",
            "الفجر",
            "البلد",
            "الشمس",
            "الليل",
            "الضحى",
            "الشرح",
            "التين",
            "العلق",
            "القدر",
            "البينة",
            "الزلزلة",
            "العاديات",
            "القارعة",
            "التكاثر",
            "العصر",
            "الهمزة",
            "الفيل",
            "قريش",
            "الماعون",
            "الكوثر",
            "الكافرون",
            "النصر",
            "المسد",
            "الإخلاص",
            "الفلق",
            "الناس"
        )
    }

    private fun initViews() {
        adapter = QuranAdapter(suraList)
        binding.quranRecyclerView.adapter = adapter
        binding.quranRecyclerView.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false)
    }

}