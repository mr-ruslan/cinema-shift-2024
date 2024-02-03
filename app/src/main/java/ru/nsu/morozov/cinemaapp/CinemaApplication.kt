package ru.nsu.morozov.cinemaapp

import android.app.Application

class CinemaApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
