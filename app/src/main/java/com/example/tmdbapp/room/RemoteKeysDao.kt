package com.example.tmdbapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbapp.model.cache.MovieRemoteKey

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteMovieKeys(list: List<MovieRemoteKey>)

    @Query("SELECT * FROM remote_keys WHERE movieId = :movieId")
    suspend fun getRemoteKeyMovieWithId(movieId: Int): MovieRemoteKey?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}