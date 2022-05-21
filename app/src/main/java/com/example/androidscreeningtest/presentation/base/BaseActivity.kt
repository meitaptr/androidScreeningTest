package com.example.androidscreeningtest.presentation.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.androidscreeningtest.R
import com.google.android.gms.common.api.ResolvableApiException

abstract class BaseActivity : AppCompatActivity(),
    ActivityLifecycle {
    abstract val resourceLayout: Int?

    private var onPermissionGranted: (() -> Unit)? = null
    private var onSettingsAccepted: (() -> Unit)? = null

    protected var resolvableApiException: ResolvableApiException? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resourceLayout?.run(this::setContentView)
        lifecycle.addObserver(
            AppActivityLifecycleObserver(
                supportFragmentManager,
                this
            )
        )
    }

    override fun onInitViews() {}

    override fun onInitObservers() {}

    fun runWithPermission(permissions: Array<String>, onPermissionGranted: () -> Unit) {
        this.onPermissionGranted = onPermissionGranted
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasPermissions(permissions)) {
            requestPermission(permissions)
        } else {
            this.onPermissionGranted?.invoke()
        }
    }

    private fun requestPermission(permissions: Array<String>) {
        ActivityCompat.requestPermissions(this, permissions,
            REQUEST_PERMISSION
        )
    }

    protected fun handleResolvableApiException(
        value: Throwable,
        onSettingsAccepted: (() -> Unit)? = null
    ) {
        this.onSettingsAccepted = onSettingsAccepted
        val exception = value as ResolvableApiException
        exception.startResolutionForResult(this,
            REQUEST_CHECK_SETTINGS
        )
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == REQUEST_PERMISSION && permissions.isNotEmpty()) {
//            when {
//                grantResults.none { it != PackageManager.PERMISSION_GRANTED } ->
//                    onPermissionGranted?.invoke()
//                permissions.none { !isNeedRequestPermissionRationale(it) } ->
//                    showNeedPermissionDialog(permissions)
//                else -> showGoToSettingDialog()
//            }
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            REQUEST_CHECK_SETTINGS -> when (resultCode) {
//                Activity.RESULT_OK -> onSettingsAccepted?.invoke()
//                Activity.RESULT_CANCELED -> showAlertDialog(
//                    message = R.string.general_warning_need_location,
//                    onPositiveClick = {
//                        resolvableApiException?.also { handleResolvableApiException(it) }
//                    },
//                    onNegativeClick = {
//                        finish()
//                    }
//                )
//            }
//        }
//    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

//    private fun showNeedPermissionDialog(permissions: Array<String>) {
//        showAlertDialog(
//            message = R.string.general_warning_need_permission,
//            onPositiveClick = {
//                requestPermission(permissions)
//            },
//            onNegativeClick = {}
//        )
//    }
//
//    private fun showGoToSettingDialog() {
//        showAlertDialog(
//            message = R.string.general_warning_need_go_to_settings,
//            onPositiveClick = {
//                startActivity(Intent().apply {
//                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                    data = Uri.fromParts("package", packageName, null)
//                })
//            },
//            onNegativeClick = {}
//        )
//    }

    private fun isNeedPermission(context: Context, permission: String) =
        ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED

    fun Activity.hasPermissions(permissions: Array<String>) =
        !permissions.map { isNeedPermission(this, it) }.contains(true)

    private var alertDialog: AlertDialog? = null

    @JvmOverloads
    fun Context.showAlertDialog(
        @StringRes message: Int,
        @StringRes labelPositive: Int = 0,
        @StringRes labelNegative: Int = 0,
        isCancelable: Boolean = false,
        onPositiveClick: (() -> Unit)? = null,
        onNegativeClick: (() -> Unit)? = null,
        isSingleLaunch: Boolean = false
    ) {
        if (isSingleLaunch && alertDialog == null ||
            isSingleLaunch && alertDialog != null && alertDialog?.isShowing == false ||
            !isSingleLaunch
        ) {
            alertDialog = AlertDialog.Builder(this@showAlertDialog)
                .apply {
                    setMessage(message)
                    setCancelable(isCancelable)
                    onPositiveClick?.run {
                        setPositiveButton(if (labelPositive != 0) labelPositive else android.R.string.ok) { dialog, _ ->
                            dialog.dismiss()
                            onPositiveClick()
                        }
                    }
                    onNegativeClick?.run {
                        setNegativeButton(if (labelNegative != 0) labelNegative else android.R.string.cancel) { dialog, _ ->
                            dialog.dismiss()
                            onNegativeClick()
                        }
                    }
                }
                .create()
            alertDialog?.show()
        }
    }

    fun Activity.isNeedRequestPermissionRationale(permission: String) =
        if (isNeedPermission(this, permission)) {
            ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
        } else {
            false
        }

    companion object {
        private const val REQUEST_PERMISSION = 0x0
        private const val REQUEST_CHECK_SETTINGS = 0x1
    }


}
