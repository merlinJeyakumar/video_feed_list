package com.merlin.training_mvvm.ui.activity.main.fragment.video_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.merlin.training_mvvm.R
import com.merlin.training_mvvm.domain.models.Incident

/**
 * this adapter displays coupon items in recycler view
 * it extends PagedListAdapter which gets data from PagedList
 * and displays in recycler view as data is available in PagedList
 */
class IncidentAdapter : PagedListAdapter<Incident, IncidentViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val li = LayoutInflater.from(parent.context)
        val view = li.inflate(R.layout.item_incident_video, parent, false)
        return IncidentViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        val incidentEntity = getItem(position)
        if (incidentEntity != null) {
            holder.bind(position, incidentEntity)
        }
    }

    companion object {
        //DiffUtil is used to find out whether two object in the list are same or not
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Incident> =
            object : DiffUtil.ItemCallback<Incident>() {
                override fun areItemsTheSame(
                    oldIncidentEntity: Incident,
                    newIncidentEntity: Incident
                ): Boolean {
                    return oldIncidentEntity.id == newIncidentEntity.id
                }

                override fun areContentsTheSame(
                    oldCoupon: Incident,
                    newCoupon: Incident
                ): Boolean {
                    return oldCoupon.updated_at == newCoupon.updated_at
                }
            }
    }
}