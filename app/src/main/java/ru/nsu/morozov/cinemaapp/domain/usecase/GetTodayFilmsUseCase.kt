package ru.nsu.morozov.cinemaapp.domain.usecase

import ru.nsu.morozov.cinemaapp.domain.entity.Film
import ru.nsu.morozov.cinemaapp.domain.repository.FilmRepository

class GetTodayFilmsUseCase(
    private val repository: FilmRepository
) {
    suspend operator fun invoke(): List<Film> = repository.getToday()
}