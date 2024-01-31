package ru.nsu.morozov.cinemaapp.presentation

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ru.nsu.morozov.cinemaapp.CinemaApplication
import ru.nsu.morozov.cinemaapp.domain.usecase.GetFilmInfoUseCase
import ru.nsu.morozov.cinemaapp.domain.usecase.GetTodayFilmsUseCase

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            CatalogViewModel(GetTodayFilmsUseCase(cinemaApplication().container.filmRepository))
        }
        initializer {
            FilmViewModel(GetFilmInfoUseCase(cinemaApplication().container.filmRepository))
        }

    }
}

fun CreationExtras.cinemaApplication(): CinemaApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CinemaApplication)
