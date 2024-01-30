package com.example.islami.radio.api

import android.util.Log
import com.example.islami.CONSTANTS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManger {
    companion object {
        private var retrofit: Retrofit? = null
    }

    private fun getInstance(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.d("Api", message) }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit!!
    }

    fun getWebServices(): WebServices {
        return getInstance().create(WebServices::class.java)
    }

}