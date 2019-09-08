package com.example.kotlinproject.view.map

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityCommonBinding
import com.example.kotlinproject.view.base.BaseFragment
import com.example.kotlinproject.view.base.BaseLocation
import com.example.kotlinproject.viewModel.map.MapViewModel
import com.google.android.gms.maps.model.Marker
import org.koin.android.ext.android.inject

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class MapActivity : BaseLocation() {
    private lateinit var mBinding: ActivityCommonBinding
    public val mapViewModel: MapViewModel by inject()


    companion object {
        val TAG = MapActivity::class.java.simpleName
    }

    override fun getLayout(): Int {
        return R.layout.activity_common
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityCommonBinding
        navigateScreen(GoogleMapFrm.TAG)
    }

    /**
     * navigate on fragment
     * @param tag represent navigation activity
     */
    private fun navigateScreen(tag: String) {
        val frm: BaseFragment
        if (tag == GoogleMapFrm.TAG) {
            frm = GoogleMapFrm.getInstance(Bundle())
            navigateFragment(R.id.container, frm, false)
        }
    }



    override fun getCurrentLocation(location: Location, address: String?) {
        Log.d(TAG, "lat -> " + location.latitude.toString() + "\n long -> " + location.longitude.toString())
        mapViewModel.locationUpdated.postValue(location)
    }


}