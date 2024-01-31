package ru.nsu.morozov.cinemaapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nsu.morozov.cinemaapp.domain.usecase.GetTodayFilmsUseCase

class CatalogViewModel(private val getTodayFilmsUseCase: GetTodayFilmsUseCase) : ViewModel() {

    private val _state = MutableLiveData<CatalogState>(CatalogState.Initial)
    val state: LiveData<CatalogState> = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = CatalogState.Loading
            try {
                val films = getTodayFilmsUseCase()
                _state.value = CatalogState.Content(films)
            } catch (e: Exception) {
                _state.value = CatalogState.Error(e.localizedMessage.orEmpty())
            }
        }
    }

}