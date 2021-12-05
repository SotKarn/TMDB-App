package com.example.tmdbapp.paging

import androidx.paging.*
import com.example.tmdbapp.model.MovieEntity
import com.example.tmdbapp.retrofit.IWebService
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class SearchPagingSource(
    private val webService: IWebService,
    private val query:String?
): PagingSource<Int, MovieEntity>()
{

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val position:Int = params.key?: 1
        return try {
            val apiResponse = webService.searchMovie(query = query, page = position)
            val movies = apiResponse.results

            LoadResult.Page(
                data = movies,
                prevKey = if(position == 1) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
