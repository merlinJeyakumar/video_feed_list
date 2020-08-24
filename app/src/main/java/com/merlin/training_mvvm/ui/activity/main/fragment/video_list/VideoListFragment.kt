package com.merlin.training_mvvm.ui.activity.main.fragment.video_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.merlin.training_mvvm.R
import com.merlin.training_mvvm.databinding.FVideoListBinding
import com.merlin.training_mvvm.extension.obtainViewModel
import com.merlin.training_mvvm.support.baseApp.mvvm.MBaseFragment
import com.merlin.training_mvvm.support.widgets.PaginationScrollListener
import com.merlin.training_mvvm.ui.activity.main.fragment.video_list.adapter.IncidentAdapter
import kotlinx.android.synthetic.main.f_video_list.*


class VideoListFragment : MBaseFragment<FVideoListBinding, VideoListFragmentViewModel>() {
    private val TAG: String = VideoListFragment::class.java.simpleName
    private var incidentAdapter: IncidentAdapter? = null
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    companion object {
        fun getInstance(mediaType: Int): VideoListFragment {
            val mediaFragment = VideoListFragment()
            val bundle = Bundle()
            mediaFragment.arguments = bundle
            return mediaFragment
        }
    }

    override fun getLayoutResID(): Int {
        return R.layout.f_video_list
    }

    override fun initializeViewModel(): VideoListFragmentViewModel {
        obtainViewModel(VideoListFragmentViewModel::class.java).let {
            return it
        }
    }

    override fun setUpUI() {
        viewModel.subscribe()

        initObserver()
        initAdapter()
        initUi()
    }

    private fun initUi() {
    }

    private fun initObserver() {
        viewModel.itemPagedList.let {
            it?.observe(this, Observer {
                isLoading = false
                pbLoading.visibility = View.GONE
                showLoadMore()
                incidentAdapter?.submitList(it)
            })
        }
        viewModel.networkStateLiveData.observe(this, Observer {
            when (it.first) {
                0 -> swipeRefreshLayout.isRefreshing = true
                1 -> swipeRefreshLayout.isRefreshing = false
                2 -> {
                    swipeRefreshLayout.isRefreshing = false
                    showToast(it.second)
                }
            }
        })
    }

    private fun initAdapter() {
        incidentAdapter = IncidentAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.adapter = incidentAdapter
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                showToast("load more")
                showLoadMore()
                //you have to call loadmore items to get more data
            }
        })
    }

    fun showLoadMore() {
        //pbLoadMore.visibility = (if (isLoading) View.VISIBLE else View.GONE)
    }

    fun onFab(view: View) {
    }
}