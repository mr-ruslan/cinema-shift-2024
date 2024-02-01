package ru.nsu.morozov.cinemaapp.data

import com.google.gson.annotations.SerializedName

data class FilmCountryModel(

    val id: Long,
    val name: String,
    val code: String,
    val code2: String
)