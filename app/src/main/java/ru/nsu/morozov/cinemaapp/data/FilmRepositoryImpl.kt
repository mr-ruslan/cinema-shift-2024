package ru.nsu.morozov.cinemaapp.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nsu.morozov.cinemaapp.domain.entity.Film
import ru.nsu.morozov.cinemaapp.domain.repository.FilmRepository
import java.util.concurrent.TimeUnit

class FilmRepositoryImpl : FilmRepository {
    private companion object {

        const val BASE_URL = "https://shift-backend.onrender.com/"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
    }

    private val gson = GsonBuilder()
        .create()

    private val retrofit = Retrofit.Builder()
        .client(provideOkHttpClientWithProgress())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private fun provideOkHttpClientWithProgress(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    private val filmApi by lazy {
        retrofit.create(FilmsApi::class.java)
    }


    private val converter = FilmConverter()
    override suspend fun getToday(): List<Film> =
        filmApi.getToday().films.map { converter.convert(it) }

}