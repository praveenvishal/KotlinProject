package com.webaddicted.kotlinproject.view.base

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.global.common.FileUtils
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.global.common.ImagePicker
import com.webaddicted.kotlinproject.global.common.PermissionHelper
import com.webaddicted.kotlinproject.global.db.dao.UserInfoDao
import com.webaddicted.kotlinproject.global.sharedPref.PreferenceMgr
import com.webaddicted.kotlinproject.model.bean.eventBus.EventBusListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.ext.android.inject
import java.io.File


/**
 * Created by Deepak Sharma on 15/1/19.
 */
abstract class BaseFragment : Fragment(), View.OnClickListener , PermissionHelper.Companion.PermissionListener,
    ImagePicker.ImagePickerListener {
    private lateinit var mBinding: ViewDataBinding
    protected val imagePicker: ImagePicker  by inject()
    protected val preferenceMgr: PreferenceMgr  by inject()
    abstract fun getLayout(): Int
    protected abstract fun onViewsInitialized(binding: ViewDataBinding?, view: View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
        return mBinding!!.getRoot()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewsInitialized(mBinding, view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        activity?.let { GlobalUtility.hideKeyboard(it) }
    }
    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun EventBusListener(eventBusListener: EventBusListener) {
    }

    protected fun navigateFragment(layoutContainer: Int, fragment: Fragment, isEnableBackStack: Boolean) {
        if (getActivity() != null) {
            (getActivity() as BaseActivity).navigateFragment(layoutContainer, fragment, isEnableBackStack)
        }
    }

    protected fun navigateAddFragment(layoutContainer: Int, fragment: Fragment, isEnableBackStack: Boolean) {
        if (getActivity() != null) {
            (getActivity() as BaseActivity).navigateAddFragment(layoutContainer, fragment, isEnableBackStack)
        }
    }

    protected fun navigateChildFragment(layoutContainer: Int, fragment: Fragment, isEnableBackStack: Boolean) {
        val fragmentManager = getChildFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(layoutContainer, fragment)
        if (isEnableBackStack)
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun onClick(v: View) {
//        GlobalUtility.Companion.btnClickAnimation(v)
    }

//    fun getLocation() {
//        (getActivity() as BaseLocation).getLocation()
//    }

//    fun getLocation(@NonNull timeInterval: Long, @NonNull fastInterval: Long, @NonNull displacement: Long) {
//        (getActivity() as BaseLocation).getLocation(timeInterval, fastInterval, displacement)
//    }
//
//    fun getLocationWithAddress(@NonNull timeInterval: Long, @NonNull fastInterval: Long, @NonNull displacement: Long) {
//        (getActivity() as BaseLocation).getLocation(timeInterval, fastInterval, displacement)
//        (getActivity() as BaseLocation).isAddressEnabled(true)
//    }
//
//    fun stopUpdateLocation() {
//        (getActivity() as BaseLocation).stopLocationUpdates()
//    }

    fun checkStoragePermission() {
        val multiplePermission = ArrayList<String>()
        multiplePermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        multiplePermission.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        multiplePermission.add(Manifest.permission.CAMERA)
        if (PermissionHelper.checkMultiplePermission(activity!!, multiplePermission)) {
            FileUtils.createApplicationFolder()
            onPermissionGranted(multiplePermission)
        } else
            PermissionHelper.requestMultiplePermission(activity!!, multiplePermission, this)
    }
    override fun onPermissionGranted(mCustomPermission: List<String>) {
        FileUtils.createApplicationFolder()
    }


    override fun onPermissionDenied(mCustomPermission: List<String>) {
    }

    override fun imagePath(filePath: List<File>) {
    }

    fun checkLocationPermission() {
        (getActivity() as BaseActivity).checkLocationPermission()
    }

    protected fun getUserDao():UserInfoDao {
        return (getActivity() as BaseActivity).getDbInstance().userInfoDao()
    }

    fun getPlaceHolder(imageLoaderPos: Int): String {
        val imageLoader = getResources().getStringArray(R.array.image_loader)
        return imageLoader[imageLoaderPos]
    }

}
