package com.demo.kotlinproject.ui.home

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.kotlinproject.R
import com.demo.kotlinproject.data.api.util.ApiConstant
import com.demo.kotlinproject.data.api.util.ApiResponse
import com.demo.kotlinproject.databinding.FragmentOffersBinding
import com.demo.kotlinproject.data.api.response.BaseApiResponse
import com.demo.kotlinproject.data.api.response.CategoryResponse
import com.demo.kotlinproject.data.api.response.CouponListResponse
import com.demo.kotlinproject.ui.adapter.CouponListAdapter
import com.demo.kotlinproject.ui.base.BaseFragment
import com.demo.kotlinproject.viewModel.home.HomeViewModel
import kotlinx.android.synthetic.main.banner_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OfferFragment : BaseFragment(), View.OnClickListener {
    private var mAdapter: CouponListAdapter? = null
    private lateinit var mBinding: FragmentOffersBinding
    private val homeViewModel: HomeViewModel by viewModel()


    companion object {
        val TAG = OfferFragment::class.java.simpleName
        fun getInstance(bundle: Bundle?): OfferFragment {
            val fragment = OfferFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_offers
    }

    override fun initUI(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FragmentOffersBinding
        setAdapter(mBinding.recyclerView, CouponListAdapter(ArrayList()))
        initViewModel()
        clickListener()
    }


    private fun initViewModel() {
        homeViewModel.getCouponListResponse().observe(viewLifecycleOwner, Observer { response ->
            handleCouponListResponse(response)
        })

        homeViewModel.getCategoriesListResponse().observe(viewLifecycleOwner, Observer { response ->
            handleCategoriesList(response)
        })
        homeViewModel.getCouponsList(ApiConstant.GET_COUPON_LIST_URL)
        homeViewModel.triggerCategoriesListApi()
    }

    private fun handleCategoriesList(response: ApiResponse<CategoryResponse>?) {

    }

    private fun handleCouponListResponse(response: ApiResponse<BaseApiResponse<CouponListResponse>>?) {
        mBinding.isLoading = response != null && response.status == ApiResponse.Status.LOADING
        if (response != null && response.status == ApiResponse.Status.SUCCESS) {
            if (response.data != null && response.data.status == 200) {
                mAdapter?.setCupons(response.data.result!!.cupons)
                setBanners(response.data.result?.banners);
            }
        }
    }

    private fun setBanners(banners: ArrayList<String>?) {
        with(mBinding.viewPagerBanner) {
            adapter = BannerPagerAdapter(banners)
            dots_indicator.setViewPager(this)

        }
    }

    private fun setAdapter(rvFashion: RecyclerView?, couponListAdapter: CouponListAdapter) {
        mAdapter = couponListAdapter
        rvFashion?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        changeAnimation(rvFashion)
        rvFashion?.adapter = couponListAdapter
    }

    private fun changeAnimation(rvFashion: RecyclerView?) {
        val animation = AnimationUtils.loadLayoutAnimation(context, R.anim.rv_anim_left_to_right)
        rvFashion?.setLayoutAnimation(animation)
    }


    private fun clickListener() {
        mBinding.callButton.setOnClickListener(this)
        mBinding.menuButton.setOnClickListener(this)
        mBinding.mapButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mapButton -> navigateToMap()
            R.id.callButton -> navigateToCall()
            R.id.menuButton -> navigateToMenu()

        }
    }

    private fun navigateToMenu() {
    }

    private fun navigateToCall() {
    }

    private fun navigateToMap() {
    }


}
