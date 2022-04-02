package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

//class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MoviesResponse>>> {

        //get data from remote api and return the flow so it can emit the data to the repository
        return flow {
            try {
                val response = apiService.getMoviesList()
                if (response.results.isNotEmpty()) {
                    emit(ApiResponse.Success(listOf(response)))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }


}

