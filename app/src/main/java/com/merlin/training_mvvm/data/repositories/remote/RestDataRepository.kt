package com.merlin.training_mvvm.data.repositories.remote

import androidx.annotation.VisibleForTesting
import com.merlin.training_mvvm.data.repositories.BaseRepository
import com.merlin.training_mvvm.data.webservices.IService
import com.merlin.training_mvvm.domain.datasources.remote.IRestDataSource
import com.merlin.training_mvvm.domain.models.MovieModel
import io.reactivex.Single

class RestDataRepository(
    private val service: IService
) : BaseRepository(), IRestDataSource {

    companion object {
        private var INSTANCE: RestDataRepository? = null

        @JvmStatic
        fun getInstance(
            service: IService
        ): RestDataRepository {
            if (INSTANCE == null) {
                synchronized(RestDataRepository::javaClass) {
                    INSTANCE =
                        RestDataRepository(service)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

    override fun getMovieList(
        page: Int, per_page: Int
    ): Single<MovieModel> {
        return service.getMovieModelList(page, per_page)
    }
}
