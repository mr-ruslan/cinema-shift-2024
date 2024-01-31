package ru.nsu.morozov.cinemaapp.presentation

import ru.nsu.morozov.cinemaapp.domain.entity.Film

sealed interface CatalogState {
    data object Initial : CatalogState

    data object Loading : CatalogState

    data class Content(val items: List<Film>) : CatalogState

    data class Error(val msg: String) : CatalogState
}