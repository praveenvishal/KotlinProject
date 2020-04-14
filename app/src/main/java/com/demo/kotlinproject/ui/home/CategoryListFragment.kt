package com.demo.kotlinproject.ui.ecommerce

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.demo.kotlinproject.R
import com.demo.kotlinproject.databinding.FragmentCategorylistBinding
import com.demo.kotlinproject.databinding.FragmentHomeBinding
import com.demo.kotlinproject.ui.adapter.CategoryRecyclerAdapter
import com.demo.kotlinproject.ui.base.BaseFragment
import com.demo.kotlinproject.ui.listenr.IRestaurantAction
import com.demo.kotlinproject.util.constant.SearchTypes
import org.parceler.Parcels

class CategoryListFragment : BaseFragment() {
    val KEY_HEADER_TITLE = "headerTitle"
    val KEY_HEADER_SUB_TITLE = "headerSubTitle"
    val KEY_LOCATION_COORDINATE = "locationCoordinate"
    @SearchTypes.SearchType
    val KEY_SEARCH_TYPE = "searchType"

    private var categorylistBinder: FragmentCategorylistBinding? = null
    private var categoryRecyclerAdapter: CategoryRecyclerAdapter? = null
    private var restaurantActionImpl: IRestaurantAction? = null

    companion object {
        val TAG = CategoryListFragment::class.java.simpleName
        fun getInstance(bundle: Bundle?): CategoryListFragment {
            val fragment = CategoryListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initUI(binding: ViewDataBinding?, view: View) {
       categorylistBinder= binding as FragmentCategorylistBinding



    }


}
