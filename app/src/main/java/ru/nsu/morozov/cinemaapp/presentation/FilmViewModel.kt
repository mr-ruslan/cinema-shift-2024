package ru.nsu.morozov.cinemaapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nsu.morozov.cinemaapp.domain.usecase.GetFilmInfoUseCase

class FilmViewModel(private val getFilmInfoUseCase: GetFilmInfoUseCase) : ViewModel() {

    private val _state = MutableLiveData<FilmInfoState>(FilmInfoState.Initial)
    val state: LiveData<FilmInfoState> = _state

    fun loadData(filmId: Long) {
        viewModelScope.launch {
            _state.value = FilmInfoState.Loading
            try {
                val film = getFilmInfoUseCase(filmId)
                _state.value = FilmInfoState.Content(film)
            } catch (e: Exception) {
                _state.value = FilmInfoState.Error(e.localizedMessage.orEmpty())
            }
        }
    }

    fun setError() {
        _state.value = FilmInfoState.Error("Application error")
    }

}