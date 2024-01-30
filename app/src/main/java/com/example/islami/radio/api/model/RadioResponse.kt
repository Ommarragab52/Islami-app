package com.example.islami.radio.api.model

import com.google.gson.annotations.SerializedName

data class RadioResponse(

    @field:SerializedName("radios")
    val radios: List<RadiosItem?>? = null
)