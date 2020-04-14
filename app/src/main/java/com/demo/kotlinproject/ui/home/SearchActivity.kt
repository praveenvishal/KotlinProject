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
import com.demo.kotlinproject.ui.adapter.DashboardPageAdapter
import com.demo.kotlinproject.ui.base.BaseActivity
import com.demo.kotlinproject.ui.ecommerce.SearchFragment
import com.demo.kotlinproject.ui.listenr.IRestaurantAction
import com.demo.kotlinproject.util.constant.SearchTypes
import com.demo.kotlinproject.viewModel.home.HomeViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.parceler.Parcels


class SearchActivity : BaseActivity(), View.OnClickListener, SearchFragment.ISearchAcion  {
    override fun onRestaurantSelected(restaurant:Restaurant?) {
        launchNextActivity(restaurant)

    }

    private lateinit var fragment: SearchFragment
    private lateinit var mBinding: ActivityDashboardBinding
    private val FTAG_SEARCH_FRAGMENT = "fragmentSearch"

    companion object {
        val TAG: String = SearchActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, SearchActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_dashboard
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityDashboardBinding
        var fragment: SearchFragment? =
            supportFragmentManager.findFragmentByTag(FTAG_SEARCH_FRAGMENT) as SearchFragment
        setSearchFragment();

    }

    private fun setSearchFragment() {
        if (null == fragment) {

            fragment = SearchFragment()

            val bundle = Bundle()
            bundle.putParcelable("location", intent.getParcelableExtra("location"))
            fragment?.setArguments(bundle)

            supportFragmentManager.beginTransaction()
                .add(R.id.fl_search_activity, fragment, FTAG_SEARCH_FRAGMENT)
                .commit()
        }

        fragment.setSearchAction(this)

    }

    private fun launchNextActivity(restaurant: Restaurant?) {
        val launchIntent = Intent(this, RestauarntDetailsActivity::class.java)
        launchIntent.putExtra("resid", restaurant?.res_id)
        launchIntent.putExtra("rName", restaurant?.name)
        launchIntent.putExtra("rCuisine", restaurant?.cuisines)
        startActivity(launchIntent)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageViewBackButton -> finish();
        }
    }




}