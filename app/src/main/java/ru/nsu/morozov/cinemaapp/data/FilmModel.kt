package ru.nsu.morozov.cinemaapp.data

import com.google.gson.annotations.SerializedName

data class FilmModel(

    val id: Long,
    val name: String,
    val description: String,
    val releaseDate: String,
    val genres: List<String>,
    @SerializedName("userRatings")
    val rating: Map<String, String>,
    @SerializedName("img")
    val image: String,
    //val country: String,
    val runtime: Long,
    val ageRating: String,
)