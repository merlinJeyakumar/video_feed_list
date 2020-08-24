package com.merlin.training_mvvm.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.merlin.training_mvvm.R
import com.merlin.training_mvvm.databinding.LayoutMainBinding
import com.merlin.training_mvvm.extension.obtainViewModel
import com.merlin.training_mvvm.support.baseApp.mvvm.MActionBarActivity
import com.merlin.training_mvvm.support.baseApp.mvvm.permission.MEasyPermissions
import com.merlin.training_mvvm.support.dialog.getConfirmationDialog
import com.merlin.training_mvvm.ui.activity.main.fragment.feed_list.FeedListFragment
import com.merlin.training_mvvm.ui.activity.main.fragment.video_list.VideoListFragment
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.layout_main.*
import org.jetbrains.anko.toast


class MainActivity : MActionBarActivity<LayoutMainBinding, MainViewModel>() {
    private val TAG: String = "MainActivity"

    override fun getLayoutId(): Int {
        return R.layout.layout_main
    }

    override fun getHeaderTitle(): String {
        return resources.getString(R.string.app_name)
    }

    override fun isSupportBackOption(): Boolean {
        return false
    }

    override fun initializeViewModel(): MainViewModel {
        obtainViewModel(MainViewModel::class.java).let { binding.viewmodel = it; return it }
    }

    override fun setUpUI(savedInstanceState: Bundle?) {
        viewModel.subscribe()
        initPermission()
        initInstance()
        initObserver()
        initUi()
        initListener()
    }

    private fun initInstance() {
    }

    private fun initListener() {

    }

    private fun initObserver() {
    }

    private fun initUi() {
        onVideosSelected()
    }

    private fun initPermission() {
        if (MEasyPermissions.hasPermissions(
                this@MainActivity,
                android.Manifest.permission.ACCESS_WIFI_STATE,
                android.Manifest.permission.CHANGE_WIFI_STATE,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {

        } else {
            MEasyPermissions.requestPermissions(
                this@MainActivity,
                REQ_STORAGE_PERMISSION,
                object : MEasyPermissions.PermissionCallbacks {
                    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
                        //TODO
                    }

                    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
                        addRxCall(
                            getConfirmationDialog(
                                message = "Require Permission",
                                isCancellable = false,
                                negativeText = "Close",
                                positiveText = "Enable"
                            ).subscribe(Consumer {
                                if (it) {
                                    initPermission()
                                } else {
                                    finishAffinity()
                                }
                            })
                        )
                    }

                    override fun onPermissionsPermanentlyDeclined(
                        requestCode: Int,
                        perms: List<String>
                    ) {
                        addRxCall(
                            getConfirmationDialog(
                                message = "Require Permission",
                                isCancellable = false,
                                negativeText = "Close",
                                positiveText = "Enable"
                            ).subscribe(Consumer {
                                if (it) {
                                    MEasyPermissions.startSetting()
                                } else {
                                    finishAffinity()
                                }
                            })
                        )
                    }
                },
                android.Manifest.permission.ACCESS_WIFI_STATE,
                android.Manifest.permission.CHANGE_WIFI_STATE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        MEasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResume() {
        super.onResume()
        if (MEasyPermissions.hasPermissions(
                this@MainActivity,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            180 -> {
                initPermission()
            }
            else -> {
            }
        }

    }

    fun onVideosSelected(view: View? = null) {
        mbtnVideosItem.isChecked = true
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentParentViewGroup, VideoListFragment())
            .commit()
    }

    fun onFeedsSelected(view: View? = null) {
        mbtnFeedListItem.isChecked = true
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentParentViewGroup, FeedListFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unsubscribe()
    }
}
