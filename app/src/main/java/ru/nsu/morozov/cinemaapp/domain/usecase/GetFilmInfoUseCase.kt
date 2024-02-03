package ru.nsu.morozov.cinemaapp.domain.usecase

import ru.nsu.morozov.cinemaapp.domain.entity.Film
import ru.nsu.morozov.cinemaapp.domain.repository.FilmRepository

class GetFilmInfoUseCase(
    private val repository: FilmRepository
) {
    suspend operator fun invoke(filmId: Long): Film = repository.getFilmInfo(filmId)
}