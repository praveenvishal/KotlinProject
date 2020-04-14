package com.demo.kotlinproject.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.MenuItem
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
import com.demo.kotlinproject.ui.listenr.IRestaurantAction
import com.demo.kotlinproject.util.constant.SearchTypes
import com.demo.kotlinproject.viewModel.home.HomeViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.parceler.Parcels


class HomeActivity : BaseActivity(), View.OnClickListener, IRestaurantAction {
    override fun onClick(restaurant: Restaurant?) {
        launchNextActivity(restaurant)

    }

    private lateinit var mBinding: ActivityDashboardBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private val MAX_TABS = 4
    private var locationCoordinates: LocationCoordinates? = null
    companion object {
        val TAG: String = HomeActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, HomeActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_dashboard
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityDashboardBinding
        locationCoordinates =
            Parcels.unwrap(intent.getBundleExtra("data").getParcelable("location"))
        val categoryResponse =
            Parcels.unwrap(intent.getBundleExtra("data").getParcelable("categoryResponse")) as CategoryResponse
        setSupportActionBar(mBinding.toolbar)
        //Create the state pager adapter
        val fragmentStatePagerAdapter = DashboardPageAdapter(
            supportFragmentManager,
            locationCoordinates, MAX_TABS, getTabHeaderInfos(), this
        )

        // Set up the ViewPager with the state pager adapter.
        mBinding.container.setAdapter(fragmentStatePagerAdapter)

        mBinding.container.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                mBinding.tabs
            )
        )
        mBinding.tabs.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                mBinding.container
            )
        )

        mBinding.tabs.setSelectedTabIndicatorColor(Color.YELLOW)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.menu_source_search -> launchSearchActivity()

            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun launchNextActivity(restaurant: Restaurant?) {
        val launchIntent = Intent(this, RestauarntDetailsActivity::class.java)
        launchIntent.putExtra("resid", restaurant?.res_id)
        launchIntent.putExtra("rName", restaurant?.name)
        launchIntent.putExtra("rCuisine", restaurant?.cuisines)
        startActivity(launchIntent)
    }


    private fun launchSearchActivity() {
        val searchIntent = Intent(this, SearchActivity::class.java)
        searchIntent.putExtra("location", Parcels.wrap<Any>(locationCoordinates))
        startActivity(searchIntent)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageViewBackButton -> finish();
        }
    }


    private fun getTabHeaderInfos(): java.util.ArrayList<DashboardPageAdapter.PageInfo> {

        val pageInfos = ArrayList<DashboardPageAdapter.PageInfo>(MAX_TABS)

        pageInfos.add(
            DashboardPageAdapter.PageInfo(
                "Go out for lunch or dinner",
                "Dine-out restaurants",
                SearchTypes.SEARCH_DINE_OUT
            )
        )

        pageInfos.add(
            DashboardPageAdapter.PageInfo(
                "Get food delivered",
                "Delivery restaurants",
                SearchTypes.SEARCH_DELIVERY
            )
        )

        pageInfos.add(
            DashboardPageAdapter.PageInfo(
                "Grab food to-go",
                "Delivery restaurants",
                SearchTypes.SEARCH_TAKE_AWAY
            )
        )

        pageInfos.add(
            DashboardPageAdapter.PageInfo(
                "Favourites",
                "Favourite restaurants",
                SearchTypes.SEARCH_FAVOURITE
            )
        )

        return pageInfos
    }


}