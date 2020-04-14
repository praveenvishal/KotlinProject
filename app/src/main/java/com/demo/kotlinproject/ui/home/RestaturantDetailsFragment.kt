package com.demo.kotlinproject.ui.ecommerce

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.demo.kotlinproject.R
import com.demo.kotlinproject.databinding.FragmentHomeBinding
import com.demo.kotlinproject.databinding.FragmentRestaurantDetailsBinding
import com.demo.kotlinproject.ui.adapter.ItemsAdapter
import com.demo.kotlinproject.ui.base.BaseFragment
import com.demo.kotlinproject.util.constant.DefaultCuisineImage

class RestaturantDetailsFragment : BaseFragment(), ItemsAdapter.IIteamAction {
    override fun onClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var adapter: ItemsAdapter
    private var restaurantName: String? = null

    private lateinit var binder: FragmentRestaurantDetailsBinding

    companion object {
        val TAG = RestaturantDetailsFragment::class.java.simpleName
        fun getInstance(bundle: Bundle?): RestaturantDetailsFragment {
            val fragment = RestaturantDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_restaurant_details
    }

    override fun initUI(binding: ViewDataBinding?, view: View) {
       binder = binding as FragmentRestaurantDetailsBinding
        restaurantName = arguments?.getString("rName")
        var cuisine = arguments?.getString("rCuisine")
        cuisine = cuisine?.split(",")?.get(0)
        binder.ivRestDetailsHeader.setImageDrawable(
            ContextCompat.getDrawable(
                binder.ivRestDetailsHeader.context,
                DefaultCuisineImage.getCuisineImage(cuisine)
            )
        )

        setAdapter();



    }

    private fun setAdapter() {
        //Restaurant Details
        binder.rvRestDetailsActions.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        binder.rvRestDetailsActions.addItemDecoration(
            ItemsAdapter.ItemDecorate(
                activity,
                R.dimen.card_margins
            )
        )
        adapter = ItemsAdapter(this)
        binder.rvRestDetailsActions.adapter = adapter
        binder.rvReviewList.layoutManager = LinearLayoutManager(activity)


    }


}
