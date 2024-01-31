package ru.nsu.morozov.cinemaapp.domain.repository

import ru.nsu.morozov.cinemaapp.domain.entity.Film

interface FilmRepository {
    suspend fun getToday(): List<Film>
}