package com.example.kotlinproject.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.model.eventBus.EventBusListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


/**
 * Created by Deepak Sharma on 15/1/19.
 */
abstract class BaseFragment : Fragment(), View.OnClickListener {
    private lateinit var mBinding: ViewDataBinding

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
        GlobalUtility.Companion.btnClickAnimation(v)
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
        (getActivity() as BaseActivity).checkStoragePermission()
    }

    fun checkLocationPermission() {
        (getActivity() as BaseActivity).checkLocationPermission()
    }

    fun getImageLoader(imageLoaderPos: Int): String {
        val imageLoader = getResources().getStringArray(R.array.image_loader)
        return imageLoader[imageLoaderPos]
    }

}
