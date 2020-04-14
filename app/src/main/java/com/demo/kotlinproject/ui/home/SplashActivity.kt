package com.demo.kotlinproject.ui.home

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.demo.kotlinproject.ApplicationController.Companion.context
import com.demo.kotlinproject.BuildConfig
import com.demo.kotlinproject.R
import com.demo.kotlinproject.data.api.pojo.LocationCoordinates
import com.demo.kotlinproject.data.api.response.CategoryResponse
import com.demo.kotlinproject.data.api.util.ApiResponse
import com.demo.kotlinproject.databinding.ActivitySplash21Binding
import com.demo.kotlinproject.ui.base.BaseActivity
import com.demo.kotlinproject.util.constant.AppConstants
import com.demo.kotlinproject.viewModel.home.HomeViewModel
import com.google.android.gms.location.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.parceler.Parcels

class SplashActivity : BaseActivity() {
    private lateinit var mBinding: ActivitySplash21Binding
    private var categoryResponse: CategoryResponse? = null
    private var location: Location? = null
    private var locationProviderClient: FusedLocationProviderClient? = null
    private val LOCATION_REQUEST_CODE = 1001
    private val homeViewModel: HomeViewModel by viewModel()


    companion object {
        val TAG: String = SplashActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, SplashActivity::class.java))
        }
    }

    private fun subscribeToViewModel() {
        homeViewModel.getCategoriesListResponse().observe(this, Observer {
            handleCatergoryListResponse(it);
        })
    }

    private fun handleCatergoryListResponse(response: ApiResponse<CategoryResponse>?) {
        if (response?.status == ApiResponse.Status.SUCCESS) {
            categoryResponse = response.data
            launchNextScreen()
        }

    }

    private fun fetchLocationInformation() {

        //Guard check
        if (ActivityCompat.checkSelfPermission(
                this@SplashActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        locationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext)

        //Listen for the last location from the provider
        locationProviderClient?.getLastLocation()?.addOnSuccessListener { loc ->

            //We did not get the last location, lets get it
            if (null == loc) {

                val locationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(10000)
                    .setFastestInterval(1000)

                locationProviderClient?.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.myLooper()
                )

            } else {
                location = loc
                fetchDataFromZomatoToProceed()
            }
        }
            ?.addOnFailureListener { e ->
                location = null
                fetchDataFromZomatoToProceed()
            }
    }

    private val locationCallback = object : LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            location = locationResult!!.lastLocation
            locationProviderClient?.removeLocationUpdates(this)
            fetchDataFromZomatoToProceed()
        }
    }

    private fun fetchDataFromZomatoToProceed() {


    }


    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {

        when (requestCode) {

            LOCATION_REQUEST_CODE -> if (PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                fetchLocationInformation()
            } else {
                location = null
                fetchDataFromZomatoToProceed()
            }

            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onPause() {
        super.onPause()
        if (null != locationProviderClient) {
            locationProviderClient?.removeLocationUpdates(locationCallback)
        }
    }

    override fun onResume() {
        super.onResume()
        confirmUserAction()

    }

    override fun getLayout(): Int {
        return R.layout.activity_splash21
    }

    private fun requestLocationAccess() {

        //If the permission has been provided
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {

            fetchLocationInformation()
            return
        }

        //Request the permission from the user
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_REQUEST_CODE
        )
    }

    private fun launchNextScreen() {

        val locationCoordinates = LocationCoordinates()
        locationCoordinates.setLongitude(
            location?.getLongitude() ?: java.lang.Double.parseDouble(BuildConfig.DEFAULT_LOGITUDE)
        )

        locationCoordinates.setLatitude(
            location?.getLatitude() ?: java.lang.Double.parseDouble(BuildConfig.DEFAULT_LATITUDE)
        )

        val bundle = Bundle()
        bundle.putParcelable("categoryResponse", Parcels.wrap<Any>(categoryResponse))
        bundle.putParcelable("location", Parcels.wrap<Any>(locationCoordinates))

        val dashboardIntent = Intent(this, HomeActivity::class.java)
        dashboardIntent.putExtra("data", bundle)
        startActivity(dashboardIntent)
        finish()
    }

    private fun confirmUserAction() {
        val builder = AlertDialog.Builder(this)
            .setTitle("ATTENTION")
            .setMessage(R.string.notify_user)
            .setPositiveButton("I Agree", { dialogInterface, i -> requestLocationAccess() })
            .setNegativeButton("Exit", { dialogInterface, i -> finish() })
            .setCancelable(false)
        builder.create().show()
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivitySplash21Binding
        init()
        setNavigationColor(ContextCompat.getColor(context!!, R.color.app_color))
        mBinding.imgLogo.setOnClickListener { init() }
    }

    private fun init() {
        navigateToNext()

    }




    /**
     * navigate to welcome activity after Splash timer Delay
     */
    private fun navigateToNext() {
        Handler().postDelayed({
            HomeActivity.newIntent(this)
            finish()
        }, AppConstants.SPLASH_DELAY)
    }
}