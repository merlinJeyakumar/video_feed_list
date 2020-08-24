package com.merlin.training_mvvm.data.webservices

import com.merlin.training_mvvm.domain.models.MovieModel
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Response
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query

interface IService {
    @GET("/api/v2/incidents")
    fun getMovieModelList(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Single<MovieModel>
}
