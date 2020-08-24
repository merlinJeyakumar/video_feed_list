package com.merlin.training_mvvm.ui.activity.main.fragment.feed_list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.merlin.training_mvvm.R
import com.merlin.training_mvvm.databinding.FFeedListBinding
import com.merlin.training_mvvm.domain.entity.FeedEntity
import com.merlin.training_mvvm.extension.obtainViewModel
import com.merlin.training_mvvm.support.baseApp.mvvm.MBaseFragment
import com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.adapter.FeedAdapter
import com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.dialog.RoomInputBottomDialog
import com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.dialog.RoomInputBottomDialog.Companion.FEED_ENTITY
import com.merlin.training_mvvm.ui.activity.main.fragment.video_list.VideoListFragment
import kotlinx.android.synthetic.main.f_feed_list.*


class FeedListFragment : MBaseFragment<FFeedListBinding, FeedListFragmentViewModel>() {
    private lateinit var feedAdapter: FeedAdapter
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

    override fun initializeViewModel(): FeedListFragmentViewModel {
        obtainViewModel(FeedListFragmentViewModel::class.java).let {
            return it
        }
    }

    override fun setUpUI() {
        viewModel.subscribe()

        initObserver()
        initListener()
        initAdapter()
    }

    private fun initObserver() {
        viewModel.feedEntityLiveData.observe(this, Observer { feedList ->

            initAdapter(feedList)
        })
    }

    private fun initAdapter(feedEntitySorted: List<FeedEntity> = viewModel.getFeedList()) {
        feedAdapter =
            FeedAdapter(
                feedEntitySorted.sortedByDescending { it.live }.toMutableList(),
                object : FeedAdapter.ItemListener {
                    override fun onItemSelected(position: Int, feedEntity: FeedEntity) {
                        showInputDialog(feedEntity)
                    }
                })
        rvFeedList.layoutManager = LinearLayoutManager(context)
        rvFeedList.adapter = feedAdapter
    }

    private fun initListener() {
        fabAdd.setOnClickListener {
            showInputDialog(FeedEntity())
        }
    }

    private fun showInputDialog(feedEntity: FeedEntity) {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        val prev: Fragment? = fragmentManager!!.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        val dialogFragment: DialogFragment = RoomInputBottomDialog()
        dialogFragment.setTargetFragment(this, 1)
        val bundle = Bundle()
        bundle.putParcelable(FEED_ENTITY, feedEntity)
        dialogFragment.arguments = bundle
        dialogFragment.show(ft, "dialog")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Stuff to do, dependent on requestCode and resultCode
        if (requestCode == 1) { // 1 is an arbitrary number, can be any int
            if (resultCode == 101) { // 1 is an arbitrary number, can be any int
                if (data?.getBooleanExtra(RoomInputBottomDialog.IS_SUCCESS, false)!!) {
                    val feedEntity =
                        data.getParcelableExtra<FeedEntity>(RoomInputBottomDialog.FEED_ENTITY)!!
                    viewModel.addFeed(feedEntity)
                }
            }
        }
    }
}