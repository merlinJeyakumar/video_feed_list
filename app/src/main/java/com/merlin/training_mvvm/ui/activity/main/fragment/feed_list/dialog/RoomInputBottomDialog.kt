package com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TimeUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.merlin.training_mvvm.R
import com.merlin.training_mvvm.domain.entity.FeedEntity
import com.merlin.training_mvvm.support.utills.DateTimePattern
import com.merlin.training_mvvm.support.utills.getUTCTime
import org.jetbrains.anko.support.v4.toast


class RoomInputBottomDialog : DialogFragment() {
    private var feedEntity: FeedEntity? = null
    private lateinit var edRoomName: EditText
    private lateinit var cbIsLive: CheckBox
    private lateinit var mbCancel: MaterialButton
    private lateinit var mbCreate: MaterialButton
    private lateinit var v: View

    companion object {
        var IS_SUCCESS = "IS_SUCCESS"
        var FEED_ENTITY = "FEED_ENTITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        feedEntity = arguments?.getParcelable(FEED_ENTITY)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.d_room_input, container, false)

        initViews()
        initListener()
        initUi()
        return v
    }

    private fun initViews() {
        edRoomName = v.findViewById(R.id.edRoomName)
        cbIsLive = v.findViewById(R.id.cbIsLive)
        mbCancel = v.findViewById(R.id.mbCancel)
        mbCreate = v.findViewById(R.id.mbCreate)
    }

    private fun initUi() {
        if (!feedEntity?.room_name.isNullOrEmpty()) {
            edRoomName.keyListener = null
        }

        feedEntity?.let {
            edRoomName.setText(it.room_name)
            cbIsLive.isChecked = it.live
        }
    }

    private fun initListener() {
        mbCancel.setOnClickListener {
            targetFragment?.onActivityResult(
                targetRequestCode,
                101,
                activity?.intent?.putExtra(IS_SUCCESS, false)
            )
            dismiss()
        }
        mbCreate.setOnClickListener {
            feedEntity!!.live = cbIsLive.isChecked
            feedEntity!!.room_name = edRoomName.text.toString()
            feedEntity!!.updated_on = getUTCTime()

            if (feedEntity!!.room_name.isEmpty()) {
                toast("room name required*")
                return@setOnClickListener
            }

            targetFragment?.onActivityResult(
                targetRequestCode,
                101,
                activity?.intent?.putExtra(IS_SUCCESS, true)?.putExtra(FEED_ENTITY, feedEntity)
            )
            dismiss()
        }
    }
}