package com.example.tmdbapp.utils

import com.example.tmdbapp.model.cache.CachedMovie
import com.example.tmdbapp.model.local.Movie

class RoomEntityMapper: IEntityMapper<CachedMovie, Movie>
{
    override fun mapToLocal(entity: CachedMovie): Movie{
        return Movie(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            release_date = entity.release_date,
            vote_average = entity.vote_average,
            poster_path = entity.poster_path
        )
    }

    override fun mapFromLocal(entity: Movie): CachedMovie{
        return CachedMovie(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            release_date = entity.release_date,
            vote_average = entity.vote_average,
            poster_path = entity.poster_path
        )
    }

    fun mapLocalListToCached(list: List<Movie>): List<CachedMovie>
    {
        return list.map { movie ->  mapFromLocal(movie) }
    }
}