package com.merlin.training_mvvm

import mobi.upod.timedurationpicker.TimeDurationPicker
import mobi.upod.timedurationpicker.TimeDurationPickerDialogFragment

class PickerDialogFragment : TimeDurationPickerDialogFragment() {
    private var onPickerDialogListener: onPickerDialogListener? = null
    fun setListener(onPickerDialogListener: onPickerDialogListener?) {
        this.onPickerDialogListener = onPickerDialogListener
    }

    override fun getInitialDuration(): Long {
        return 15 * 60 * 1000
    }

    override fun setTimeUnits(): Int {
        return TimeDurationPicker.HH_MM
    }

    override fun onDurationSet(
        view: TimeDurationPicker,
        duration: Long
    ) {
        onPickerDialogListener?.onDurationSet(view, duration)
    }
}

interface onPickerDialogListener {
    fun onDurationSet(view: TimeDurationPicker?, duration: Long)
}