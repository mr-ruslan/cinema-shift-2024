package ru.nsu.morozov.cinemaapp.data

import retrofit2.http.GET

interface FilmsApi {
    @GET("/cinema/today")
    suspend fun getToday(): FilmListResponse

}