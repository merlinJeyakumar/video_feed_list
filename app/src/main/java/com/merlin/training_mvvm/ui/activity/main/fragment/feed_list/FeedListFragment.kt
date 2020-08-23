package com.merlin.training_mvvm.ui.activity.main.fragment.feed_list

import android.os.Bundle
import com.merlin.training_mvvm.R
import com.merlin.training_mvvm.databinding.FFeedListBinding
import com.merlin.training_mvvm.extension.obtainViewModel
import com.merlin.training_mvvm.support.baseApp.mvvm.MBaseFragment
import com.merlin.training_mvvm.ui.activity.main.fragment.video_list.VideoListFragment
import com.merlin.training_mvvm.ui.activity.main.fragment.video_list.VideoListFragmentViewModel


class FeedListFragment : MBaseFragment<FFeedListBinding, VideoListFragmentViewModel>() {
    private val TAG: String = VideoListFragment::class.java.simpleName

    companion object {
        fun getInstance(mediaType: Int): VideoListFragment {
            val mediaFragment = VideoListFragment()
            val bundle = Bundle()
            mediaFragment.arguments = bundle
            return mediaFragment
        }
    }

    override fun getLayoutResID(): Int {
        return R.layout.f_feed_list
    }

    override fun initializeViewModel(): VideoListFragmentViewModel {
        obtainViewModel(VideoListFragmentViewModel::class.java).let {
            return it
        }
    }

    override fun setUpUI() {
        viewModel.subscribe()

        initObserver()
    }

    private fun initObserver() {
    }
}