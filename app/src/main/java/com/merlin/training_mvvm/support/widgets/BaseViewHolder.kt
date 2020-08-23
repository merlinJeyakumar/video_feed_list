package com.merlin.training_mvvm.support.widgets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.toast

abstract class BaseViewHolder<M>(
    private val inflater: LayoutInflater,
    parent: ViewGroup,
    layoutRes: Int
) :
    RecyclerView.ViewHolder(inflater.inflate(layoutRes, parent, false)) {

    fun toast(resId: Int) {
        inflater.context.toast(resId)
    }

    fun toast(text: String) {
        inflater.context.toast(text)
    }

    abstract fun bind(position: Int, fileModel: M)
}