package ru.nsu.morozov.cinemaapp.presentation

import ru.nsu.morozov.cinemaapp.domain.entity.Film

sealed interface FilmInfoState {
    data object Initial : FilmInfoState

    data object Loading : FilmInfoState

    data class Content(val item: Film) : FilmInfoState

    data class Error(val msg: String) : FilmInfoState
}