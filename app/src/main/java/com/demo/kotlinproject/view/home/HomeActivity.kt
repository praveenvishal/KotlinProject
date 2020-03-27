package com.demo.kotlinproject.view.home

import FragmentTabAdapter
import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.databinding.ViewDataBinding
import com.demo.kotlinproject.R
import com.demo.kotlinproject.databinding.ActivityTabBinding
import com.demo.kotlinproject.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_tab.*


class HomeActivity : BaseActivity() ,View.OnClickListener {

    private lateinit var mBinding: ActivityTabBinding

    companion object {
        val TAG: String = HomeActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, HomeActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_tab
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityTabBinding
        mBinding.imageViewBackButton.setOnClickListener(this)
        setFragmentAdapter()
    }

    private fun setFragmentAdapter() {
        val myViewPageStateAdapter = FragmentTabAdapter(supportFragmentManager)
        myViewPageStateAdapter.addFragment(OfferFragment(), getString(R.string.textOffers))
        myViewPageStateAdapter.addFragment(DetailsFragment(), getString(R.string.textDetails))
        viewPager.adapter = myViewPageStateAdapter
        tabLayout.setupWithViewPager(viewPager, true)

    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.imageViewBackButton->finish();
        }
    }




}