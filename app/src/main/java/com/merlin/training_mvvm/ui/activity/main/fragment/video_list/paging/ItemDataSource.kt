package com.merlin.training_mvvm.ui.activity.main.fragment.video_list.paging

import android.util.Pair
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.merlin.training_mvvm.Injection
import com.merlin.training_mvvm.data.schedulers.Scheduler.io
import com.merlin.training_mvvm.data.schedulers.Scheduler.ui
import com.merlin.training_mvvm.domain.models.Incident
import io.reactivex.disposables.CompositeDisposable

class ItemDataSource(var networkStateLiveData: MutableLiveData<Pair<Int, String>>) :
    PageKeyedDataSource<Int, Incident>() {

    companion object {
        const val PAGE_SIZE = 10
        private const val FIRST_PAGE = 1
    }

    var restDataSourceFactory = Injection.provideRestDataSource()
    var compositeDisposable = CompositeDisposable()

    override fun loadInitial(
        params: LoadInitialParams<Int?>,
        callback: LoadInitialCallback<Int?, Incident?>
    ) {
        compositeDisposable.add(
            restDataSourceFactory.getMovieList(FIRST_PAGE, PAGE_SIZE)
                .subscribeOn(io())
                .observeOn(ui())
                .subscribe({
                    callback.onResult(
                        it.incidents,
                        null,
                        FIRST_PAGE + 1
                    )
                    networkStateLiveData.value =  Pair(1,"loaded!")
                }, {
                    Pair(2,it.localizedMessage)
                    it.printStackTrace()
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int?>, callback: LoadCallback<Int?, Incident?>) {
        compositeDisposable.add(
            restDataSourceFactory.getMovieList(FIRST_PAGE, PAGE_SIZE)
                .subscribeOn(io())
                .observeOn(ui())
                .subscribe({
                    networkStateLiveData.value =  Pair(1,"loaded!")
                    val adjacentKey = if (params.key > 1) params.key - 1 else null
                    callback.onResult(it.incidents, adjacentKey)
                }, {
                    Pair(2,it.localizedMessage)
                    it.printStackTrace()
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int?>, callback: LoadCallback<Int?, Incident?>) {
        compositeDisposable.add(
            restDataSourceFactory.getMovieList(FIRST_PAGE, PAGE_SIZE)
                .subscribeOn(io())
                .observeOn(ui())
                .subscribe({
                    networkStateLiveData.value =  Pair(1,"loaded!")
                    callback.onResult(it.incidents, params.key + 1)
                }, {
                    Pair(2,it.localizedMessage)
                    it.printStackTrace()
                })
        )
    }
}