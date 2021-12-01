package com.example.tmdbapp.utils


import com.example.tmdbapp.model.local.Movie
import com.example.tmdbapp.model.web.WebMovieEntity
import javax.inject.Inject

class WebEntityMapper @Inject constructor(): IEntityMapper<WebMovieEntity, Movie> {
    override fun mapToLocal(entity: WebMovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            release_date = entity.release_date,
            vote_average = entity.vote_average,
            poster_path = entity.poster_path
        )
    }

    override fun mapFromLocal(entity: Movie): WebMovieEntity
    {
        return WebMovieEntity(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            release_date = entity.release_date,
            vote_average = entity.vote_average,
            poster_path = entity.poster_path
        )
    }

    fun mapWebListToLocal(list: List<WebMovieEntity>): List<Movie>
    {
        return list.map { movie ->  mapToLocal(movie) }
    }
}

