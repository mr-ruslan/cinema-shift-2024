package ru.nsu.morozov.cinemaapp.data

import ru.nsu.morozov.cinemaapp.domain.entity.Film
class FilmConverter {

    fun convert(from: FilmModel): Film =
        with(from) {
            Film(
                id = id,
                name = name,
                originalName = originalName,
                description = description,
                releaseDate = releaseDate,
                genres = genres,
                rating = rating,
                image = image,
                country = country.name,
                runtime = runtime,
                ageRating = ageRating,
            )
        }
}