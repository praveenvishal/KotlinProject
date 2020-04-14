package com.demo.kotlinproject.ui.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.demo.kotlinproject.data.api.pojo.LocationCoordinates;
import com.demo.kotlinproject.ui.ecommerce.CategoryListFragment;
import com.demo.kotlinproject.ui.listenr.IRestaurantAction;
import com.demo.kotlinproject.util.constant.SearchTypes;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DashboardPageAdapter extends FragmentStatePagerAdapter {

    private static final int TAB_INDEX_FAVOURITE = 3;
    private int pageCount;
    private LocationCoordinates locationCoordinates;
    private ArrayList<PageInfo> pageInfoList;
    private IRestaurantAction restaurantActionImpl;
    public DashboardPageAdapter(FragmentManager fm, LocationCoordinates locationCoordinates,
                                int pageCount, ArrayList<PageInfo> pageInfoList,
                                IRestaurantAction action) {

        super(fm);
        this.locationCoordinates = locationCoordinates;
        this.pageCount = pageCount;
        this.pageInfoList = pageInfoList;
        this.restaurantActionImpl = action;
    }

    @Override
    public Fragment getItem(int position) {



        Bundle bundle = new Bundle();
        bundle.putString(CategoryListFragment.KEY_HEADER_TITLE, pageInfoList.get(position).getHeaderTitle());
        bundle.putString(CategoryListFragment.KEY_HEADER_SUB_TITLE, pageInfoList.get(position).getHeaderSubTitle());
        bundle.putParcelable(CategoryListFragment.KEY_LOCATION_COORDINATE, Parcels.wrap(locationCoordinates));
        bundle.putString(CategoryListFragment.KEY_SEARCH_TYPE, pageInfoList.get(position).getSearchType());

        //If this is for favourite fragment
        if(SearchTypes.SEARCH_FAVOURITE.contentEquals(pageInfoList.get(position).getSearchType())) {

            FavouriteList fragment = new FavouriteList();
            fragment.setRestaurantAction(restaurantActionImpl);
            fragment.setArguments(bundle);

            return fragment;
        }

        CategoryListFragment fragment = new CategoryListFragment();
        fragment.setRestaurantAction(restaurantActionImpl);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    public static class PageInfo {

        private String headerTitle;
        private String headerSubTitle;
        private @SearchTypes.SearchType
        String searchType;

        public PageInfo(String headerTitle, String headerSubTitle, @SearchTypes.SearchType String searchType) {
            this.headerTitle = headerTitle;
            this.headerSubTitle = headerSubTitle;
            this.searchType = searchType;
        }

        String getHeaderTitle() {
            return headerTitle;
        }

        String getHeaderSubTitle() {
            return headerSubTitle;
        }

        String getSearchType() {
            return searchType;
        }
    }
}
