package com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.merlin.training_mvvm.domain.entity.FeedEntity
import com.merlin.training_mvvm.support.widgets.BaseViewHolder
import com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.adapter.view_holder.FeedViewHolder

class FeedAdapter(
    private var fileList: MutableList<FeedEntity>,
    private var itemListener: ItemListener?
) : RecyclerView.Adapter<BaseViewHolder<FeedEntity>>() {
    private val selectionList = mutableMapOf<String, Pair<Int, FeedEntity>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FeedEntity> {
        return FeedViewHolder(
            LayoutInflater.from(parent.context),
            parent,
            itemListener
        )
    }

    override fun getItemCount(): Int = fileList.size

    override fun onBindViewHolder(holder: BaseViewHolder<FeedEntity>, position: Int) {
        holder.itemView.setOnClickListener {
            itemListener?.onItemSelected(position, fileList[position])
        }
        holder.bind(
            position,
            fileList[position]
        )
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<FeedEntity>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
    }

    fun setData(newRating: List<FeedEntity>) {
        val diffCallback = MediaDiffCallback(fileList, newRating)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        fileList.clear()
        fileList.addAll(newRating)
        selectionList.clear()
        diffResult.dispatchUpdatesTo(this)
    }

    class MediaDiffCallback(
        private val oldList: List<FeedEntity>,
        private val newList: List<FeedEntity>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldList[oldPosition].updated_on == newList[newPosition].updated_on
        }

        @Nullable
        override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
            return super.getChangePayload(oldPosition, newPosition)
        }
    }

    interface ItemListener {
        fun onItemSelection(position: Int, fileModel: FeedEntity) {}
        fun onItemSelected(position: Int, fileModel: FeedEntity) {}
    }
}
