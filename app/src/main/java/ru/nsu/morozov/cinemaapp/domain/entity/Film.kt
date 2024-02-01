package ru.nsu.morozov.cinemaapp.domain.entity


data class Film(
    val id: Long,
    val name: String,
    val originalName: String,
    val description: String,
    val releaseDate: String,
    val genres: List<String>,
    val rating: Map<String, String>,
    val image: String,
    val country: String,
    val runtime: Long,
    val ageRating: String,
)