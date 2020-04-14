package com.demo.kotlinproject.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.demo.kotlinproject.R
import com.demo.kotlinproject.data.api.pojo.LocationCoordinates
import com.demo.kotlinproject.data.api.pojo.Restaurant
import com.demo.kotlinproject.data.api.response.CategoryResponse
import com.demo.kotlinproject.data.api.util.ApiConstant
import com.demo.kotlinproject.data.api.util.ApiResponse
import com.demo.kotlinproject.databinding.ActivityDashboardBinding
import com.demo.kotlinproject.databinding.ActivityRestaurantDetailsBinding
import com.demo.kotlinproject.ui.adapter.DashboardPageAdapter
import com.demo.kotlinproject.ui.base.BaseActivity
import com.demo.kotlinproject.ui.ecommerce.RestaturantDetailsFragment
import com.demo.kotlinproject.ui.listenr.IRestaurantAction
import com.demo.kotlinproject.util.constant.SearchTypes
import com.demo.kotlinproject.viewModel.home.HomeViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.parceler.Parcels


class   RestauarntDetailsActivity : BaseActivity(), View.OnClickListener, IRestaurantAction {
    private var mBinding: ActivityRestaurantDetailsBinding?=null
    private val FTAG_DETAILS = "detailsFragment"


    override fun onClick(restaurant: Restaurant?) {
    }


    companion object {
        val TAG: String = RestauarntDetailsActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, RestauarntDetailsActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_restaurant_details
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityRestaurantDetailsBinding
        val bundle = Bundle()
        bundle.putString("resid", intent.getStringExtra("resid"))
        bundle.putString("rName", intent.getStringExtra("rName"))
        bundle.putString("rCuisine", intent.getStringExtra("rCuisine"))
        val fragment = RestaturantDetailsFragment ()
        fragment.setArguments(bundle)
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fl_rest_details_activity_container, fragment, FTAG_DETAILS)
            .commit()

    }







}