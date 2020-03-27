package com.demo.kotlinproject.view.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.demo.kotlinproject.R
import com.demo.kotlinproject.apiutils.ApiConstant
import com.demo.kotlinproject.apiutils.ApiResponse
import com.demo.kotlinproject.databinding.FragmentDetailsBinding
import com.demo.kotlinproject.global.common.GlideApp
import com.demo.kotlinproject.model.response.BaseApiResponse
import com.demo.kotlinproject.model.response.CouponListResponse
import com.demo.kotlinproject.view.base.BaseFragment
import com.demo.kotlinproject.viewModel.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment(), View.OnClickListener {
    private lateinit var mBinding: FragmentDetailsBinding
    private val homeViewModel: HomeViewModel by viewModel()


    companion object {
        val TAG = DetailsFragment::class.java.simpleName
        fun getInstance(bundle: Bundle?): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_details
    }

    override fun initUI(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FragmentDetailsBinding
        initViewModel()
    }


    private fun initViewModel() {
        homeViewModel.getCouponListResponse().observe(viewLifecycleOwner, Observer { response ->
            handleCouponListResponse(response)
        })
        homeViewModel.getCouponsList(ApiConstant.GET_COUPON_LIST_URL)

    }

    private fun handleCouponListResponse(response: ApiResponse<BaseApiResponse<CouponListResponse>>?) {
        mBinding.isLoading = response != null && response.status == ApiResponse.Status.LOADING
        if (response != null && response.status == ApiResponse.Status.SUCCESS) {
            if (response.data != null && response.data.status == 200) {
                GlideApp.with(activity!!).load(response.data.result?.decription_image).into(mBinding.imageViewBanner)
                mBinding.textViewDescription.setText(response.data.result?.description_body)
                mBinding.textViewDescriptionTitle.setText(response.data.result?.description_title)
            }
        }
    }


    override fun onClick(v: View) {
        when (v.id) {
        }
    }


}
