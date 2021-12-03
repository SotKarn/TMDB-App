package com.example.tmdbapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmdbapp.model.web.WebMovieEntity
import com.example.tmdbapp.retrofit.IWebService
import retrofit2.HttpException
import java.io.IOException


class MoviePagingSource (
    private val webService: IWebService
): PagingSource<Int, WebMovieEntity>()
{
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WebMovieEntity> {
        val position = params.key ?: 1

        return try
                {
                    val movies = webService.getPopularMovies(page = position).results

                    LoadResult.Page(
                        data = movies,
                        prevKey = if (position == 1) null else position - 1,
                        nextKey = if (movies.isEmpty()) null else position + 1
                    )
                } catch (exception: IOException) {
                    LoadResult.Error(exception)
                } catch (exception: HttpException) {
                    LoadResult.Error(exception)
                }
    }

    override fun getRefreshKey(state: PagingState<Int, WebMovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}