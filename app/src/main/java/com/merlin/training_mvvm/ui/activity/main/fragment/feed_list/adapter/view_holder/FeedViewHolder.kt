package com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.adapter.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.merlin.training_mvvm.R
import com.merlin.training_mvvm.domain.entity.FeedEntity
import com.merlin.training_mvvm.support.utills.parseDateFromMilliseconds
import com.merlin.training_mvvm.support.widgets.BaseViewHolder
import com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.adapter.FeedAdapter

class FeedViewHolder(
    inflater: LayoutInflater,
    private val parent: ViewGroup,
    private val itemListener: FeedAdapter.ItemListener?
) :
    BaseViewHolder<FeedEntity>(inflater, parent, R.layout.item_feed) {
    private val TAG: String = "ImageViewHolder"
    private var tvDateTime = itemView.findViewById<TextView>(R.id.tvDateTime)
    private var tvLive = itemView.findViewById<TextView>(R.id.tvLive)
    private var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

    override fun bind(position: Int, feedEntity: FeedEntity) {
        tvDateTime.text = parseDateFromMilliseconds(feedEntity.updated_on, "dd-MMM-yyyy HH:mm")
        tvLive.visibility = (if (feedEntity.live) View.VISIBLE else View.GONE)
        tvTitle.text = feedEntity.room_name
    }
}