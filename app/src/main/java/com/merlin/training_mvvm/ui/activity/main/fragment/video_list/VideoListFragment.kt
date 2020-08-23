package com.merlin.training_mvvm.ui.activity.main.fragment.video_list

import android.os.Bundle
import com.merlin.training_mvvm.R
import com.merlin.training_mvvm.databinding.FFeedListBinding
import com.merlin.training_mvvm.databinding.FVideoListBinding
import com.merlin.training_mvvm.extension.obtainViewModel
import com.merlin.training_mvvm.support.baseApp.mvvm.MBaseFragment


class VideoListFragment : MBaseFragment<FVideoListBinding, VideoListFragmentViewModel>() {
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
    }

    private fun initObserver() {
    }
}