package com.example.islami.radio

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.islami.CONSTANTS
import com.example.islami.databinding.FragmentRadioBinding
import com.example.islami.radio.api.ApiManger
import com.example.islami.radio.api.model.RadioResponse
import com.example.islami.radio.api.model.RadiosItem
import com.example.islami.radio.service.RadioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RadioFragment : Fragment() {
    lateinit var binding: FragmentRadioBinding
    lateinit var adapter: RadioAdapter
    private lateinit var api: ApiManger
    private var radioList: MutableList<RadiosItem?>? = null
    private var radioService: RadioService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as RadioService.MyBinder
            radioService = binder.getRadioServices()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        bindService()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getRadiosFromApi()
        onStartRadioClick()
    }

    private fun initData() {
        adapter.bindItems(radioList)
    }

    private fun getRadiosFromApi() {
        api = ApiManger()
        api.getWebServices().getRadios().enqueue(object : Callback<RadioResponse> {
            override fun onResponse(call: Call<RadioResponse>, response: Response<RadioResponse>) {
                Log.e("api", "onResponse:${response.body()?.radios} ")
                radioList = response.body()?.radios?.toMutableList()
                initData()
                binding.progressBar.visibility = ProgressBar.GONE
            }

            override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                Toast.makeText(activity, "api error : ${t.message}", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = ProgressBar.GONE
            }

        })

    }

    private fun initViews() {
        adapter = RadioAdapter(null)
        binding.radioRecyclerView.adapter = adapter
        binding.radioRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.radioRecyclerView)
    }


    private fun startService(radio: RadiosItem, action: String) {
        val intent = Intent(this.context, RadioService::class.java)
        intent.putExtra(CONSTANTS.RADIO_NAME, radio.name)
        intent.putExtra(CONSTANTS.RADIO_URL, radio.url)
        intent.putExtra(CONSTANTS.RADIO_ACTION, action)
        activity?.startService(intent)
    }

    private fun bindService() {
        val intent = Intent(this.context, RadioService::class.java)
        activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun onStartRadioClick() {
        adapter.onPlayStopRadioClick =
            RadioAdapter.OnPlayStopRadioClick { position, radio ->
                startRadio(radio)
            }
    }


    private fun startRadio(radio: RadiosItem) {
        if (isBound && radio.name != null && radio.url != null)
            radioService?.playMediaPlayer(radio.url, radio.name)
    }

    private fun stopRadio() {
        radioService?.stopMediaPlayer()
        isBound = false
    }
}