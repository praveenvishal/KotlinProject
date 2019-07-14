package com.example.kotlinproject.view.base

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.kotlinproject.R
import com.example.kotlinproject.global.common.*
import com.example.kotlinproject.global.constant.DbConstant
import com.example.kotlinproject.global.db.database.AppDatabase
import com.example.kotlinproject.model.eventBus.EventBusListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.ext.android.inject
import java.io.File

/**
 * Created by Deepak Sharma on 01/07/19.
 */
abstract class BaseActivity : AppCompatActivity(), View.OnClickListener, PermissionHelper.Companion.PermissionListener,
    ImagePicker.ImagePickerListener {
    protected  var appDb: AppDatabase?=null
    protected val imagePicker: ImagePicker  by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            if (window != null) {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            }
        }
        GlobalUtility.hideKeyboard(this)
        AppApplication.mCurrencyActivity = this
        var layoutResId = getLayout()
        var binding: ViewDataBinding? = null
        if (layoutResId != 0) {
            try {
                binding = DataBindingUtil.setContentView(this, layoutResId)
                initUI(binding)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    open abstract fun getLayout(): Int

    open abstract fun initUI(binding: ViewDataBinding)
    /**
     * placeholder type for image
     *
     * @param placeholderType position of string array placeholder
     * @return
     */
    protected fun getPlaceHolder(placeholderType: Int): String {
        val placeholderArray = getResources().getStringArray(R.array.image_loader)
        return placeholderArray[placeholderType]
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    fun getDbInstance(): AppDatabase {
        if (appDb == null) {
            appDb = Room.databaseBuilder(this, AppDatabase::class.java, DbConstant.DB_NAME)
                .allowMainThreadQueries().build();
        }
        return appDb as AppDatabase
    }

    @Subscribe
    fun EventBusListener(eventBusListener: EventBusListener) {

    }

    override fun onClick(v: View?) {}

    fun checkStoragePermission() {
        val multiplePermission = ArrayList<String>()
        multiplePermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        multiplePermission.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        multiplePermission.add(Manifest.permission.CAMERA)
        if (PermissionHelper.checkMultiplePermission(this, multiplePermission)) {
            FileUtils.createApplicationFolder()
            onPermissionGranted(multiplePermission)
        } else
            PermissionHelper.requestMultiplePermission(this, multiplePermission, this)
    }


    fun checkLocationPermission() {
        val multiplePermission = ArrayList<String>()
        multiplePermission.add(Manifest.permission.ACCESS_FINE_LOCATION)
        multiplePermission.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        if (PermissionHelper.checkMultiplePermission(this, multiplePermission)) {

        } else
            PermissionHelper.requestMultiplePermission(this, multiplePermission, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionHelper.Companion.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }


    override fun onPermissionGranted(mCustomPermission: List<String>) {
        FileUtils.createApplicationFolder()
    }

    override fun onPermissionDenied(mCustomPermission: List<String>) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePicker.REQUEST_CAMERA || requestCode == ImagePicker.SELECT_FILE || requestCode == ImagePicker.PICK_IMAGE_MULTIPLE || requestCode == ImagePicker.REQUEST_CAPTURE_IMAGE) {
                imagePicker.onActivityResult(this, requestCode, resultCode, data)
            }
        }
    }

    override fun imagePath(filePath: List<File>) {
    }

    fun navigateFragment(layoutContainer: Int, fragment: Fragment, isEnableBackStack: Boolean) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out, R.anim.trans_right_in, R.anim.trans_right_out)
        fragmentTransaction.replace(layoutContainer, fragment)
        if (isEnableBackStack)
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun navigateAddFragment(layoutContainer: Int, fragment: Fragment, isEnableBackStack: Boolean) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out, R.anim.trans_right_in, R.anim.trans_right_out)
        fragmentTransaction.add(layoutContainer, fragment)
        if (isEnableBackStack)
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        fragmentTransaction.commitAllowingStateLoss()
    }

}