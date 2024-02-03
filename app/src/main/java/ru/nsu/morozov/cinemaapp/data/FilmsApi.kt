package ru.nsu.morozov.cinemaapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface FilmsApi {
    @GET("/cinema/today")
    suspend fun getToday(): FilmListResponse

    @GET("/cinema/film/{filmId}")
    suspend fun getFilm(@Path("filmId") filmId: Long): FilmSingleResponse
}