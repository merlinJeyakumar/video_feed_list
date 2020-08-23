package com.merlin.training_mvvm.support.baseApp.mvvm

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.merlin.training_mvvm.R
import kotlinx.android.synthetic.main.ma_header.*


abstract class MActionBarActivity<B : ViewDataBinding, VM : MBaseViewModel> :
    MBaseActivity<B, VM>() {

    protected abstract fun getLayoutId(): Int
    protected abstract fun setUpUI(savedInstanceState: Bundle?)
    protected abstract fun getHeaderTitle(): String
    protected abstract fun isSupportBackOption(): Boolean

    open fun readIntent() {}


    override fun getBaseLayoutId(): Int {
        return R.layout.ma_header
    }

    override fun getProgressView(): View {
        return v_content_load_error_v_frame_loading_content
    }

    override fun setUpChildUI(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getHeaderTitle()
        if (isSupportBackOption()) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener { onBackPressed() }
        } else {
            supportActionBar?.setHomeAsUpIndicator(null)
        }
        ablHeader?.elevation = 0f
        supportActionBar?.elevation = 0f
        a_header_layout_content!!.layoutResource = getLayoutId()
        binding = DataBindingUtil.bind(a_header_layout_content!!.inflate())!!
        binding.lifecycleOwner = this
        viewModel = initializeViewModel()
        setUpObserver()

        readIntent()
        setUpUI(savedInstanceState)


    }

    private fun setUpObserver() {
        viewModel.toastMessage.observe(this, Observer { message ->
            showToast(message)
        })

        viewModel.toastResMessage.observe(this, Observer { intRes ->
            showToast(intRes)
        })
    }

    fun setHeaderTitle(headerTitle: String) {
        supportActionBar!!.title = headerTitle
    }


    override fun onNetworkStatusChanged(isConnected: Boolean) {

    }
}
