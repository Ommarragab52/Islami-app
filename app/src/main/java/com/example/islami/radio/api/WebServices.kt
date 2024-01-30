package com.example.islami.radio.api

import com.example.islami.radio.api.model.RadioResponse
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {
    @GET("radios")
    fun getRadios(): Call<RadioResponse>

}