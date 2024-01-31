package ru.nsu.morozov.cinemaapp.domain.entity

import java.time.LocalDateTime

data class Film(
    val id: Long,
    val name: String,
    val description: String,
    val releaseDate: LocalDateTime,
    val genres: String,
    val rating: Map<String, Double>,
    val image: String,
    val country: String,
    val runtime: Long,
    val ageRating: String,
)