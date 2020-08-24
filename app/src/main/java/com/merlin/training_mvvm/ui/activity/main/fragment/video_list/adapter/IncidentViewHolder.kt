package com.merlin.training_mvvm.ui.activity.main.fragment.video_list.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.merlin.training_mvvm.R
import com.merlin.training_mvvm.domain.models.Incident
import com.merlin.training_mvvm.support.utills.parseDateFromMilliseconds

class IncidentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var imageView = itemView.findViewById<ImageView>(R.id.imageView)!!
    var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)!!
    var tvDateTime = itemView.findViewById<TextView>(R.id.tvDateTime)!!
    var tvType = itemView.findViewById<TextView>(R.id.tvType)!!
    var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)!!


    fun bind(position: Int, incidentModel: Incident) {
        incidentModel.media.image_url?.let {
            Glide.with(imageView.context)
                .load(incidentModel.media.image_url_thumb)
                .apply(
                    RequestOptions().signature(ObjectKey(it)).centerCrop()
                        .diskCacheStrategy(
                            DiskCacheStrategy.DATA
                        )
                )
                .into(imageView)

            Glide.with(imageView.context).clear(imageView)

            Glide.with(imageView.context)
                .load(it)
                .apply(
                    RequestOptions().signature(ObjectKey(it)).centerCrop()
                        .diskCacheStrategy(
                            DiskCacheStrategy.DATA
                        )
                )
                .into(imageView)
        }

        tvTitle.text = incidentModel.title
        incidentModel.updated_at.let {
            tvDateTime.text = parseDateFromMilliseconds(
                it.toLong(),
                "dd/MMM/yyyy HH:mm aa"
            )
        }
        tvType.text = incidentModel.type
        tvDescription.text = incidentModel.description
    }
}