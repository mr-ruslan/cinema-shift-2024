package ru.nsu.morozov.cinemaapp

import ru.nsu.morozov.cinemaapp.domain.repository.FilmRepository

interface AppContainer {
    val filmRepository: FilmRepository
}
