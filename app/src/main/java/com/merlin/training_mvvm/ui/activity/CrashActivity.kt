package com.merlin.training_mvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.merlin.training_mvvm.R

class CrashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash)

        initView()
    }

    private fun initView() {
        //textView.text = intent?.getStringExtra(CRASH_ISSUE)
    }

    companion object {
        @JvmField
        var CRASH_ISSUE: String = "CRASH_ISSUE"
    }
}
