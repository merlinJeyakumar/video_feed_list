package com.merlin.training_mvvm.domain.datasources.remote

import com.merlin.training_mvvm.domain.models.MovieModel
import io.reactivex.Single

interface IRestDataSource {
    fun getMovieList(proximity_square: Int, page: Int): Single<MovieModel>
}