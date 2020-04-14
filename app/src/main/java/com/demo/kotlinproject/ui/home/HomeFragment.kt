package com.demo.kotlinproject.ui.ecommerce

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.demo.kotlinproject.R
import com.demo.kotlinproject.databinding.FragmentHomeBinding
import com.demo.kotlinproject.ui.base.BaseFragment

class HomeFragment : BaseFragment() {
      private lateinit var mBinding: FragmentHomeBinding

    companion object {
        val TAG = HomeFragment::class.java.simpleName
        fun getInstance(bundle: Bundle?): HomeFragment {
            val fragment = HomeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initUI(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FragmentHomeBinding

    }


}
