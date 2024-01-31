package ru.nsu.morozov.cinemaapp.data

import ru.nsu.morozov.cinemaapp.domain.entity.Film
import ru.nsu.morozov.cinemaapp.domain.repository.FilmRepository

class FilmRepositoryImpl : FilmRepository {
    override suspend fun getToday(): List<Film> {
        TODO("Not yet implemented")
    }

}