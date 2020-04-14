package com.demo.kotlinproject.ui.ecommerce

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.kotlinproject.R
import com.demo.kotlinproject.data.api.response.search.Restaurant
import com.demo.kotlinproject.databinding.FragmentSearchBinding
import com.demo.kotlinproject.ui.base.BaseFragment
import com.demo.kotlinproject.ui.home.SearchAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchFragment() : BaseFragment(),SearchAdapter.ISearchAction  {
    private var searchAcion: ISearchAcion? = null
    var disposableObserver: DisposableObserver<String>? = null
    fun setSearchAction(searchAction: ISearchAcion) {
        this.searchAcion = searchAction
    }

    interface ISearchAcion {
        fun onRestaurantSelected(restaurant: com.demo.kotlinproject.data.api.pojo.Restaurant?)


    }
    override fun onSearchComplete(count: Int) {
        if (0 == count) {
            binder.pbSearch.visibility = View.GONE
            binder.tvSearchError.visibility = View.VISIBLE
            binder.rvSearchResult.visibility = View.GONE
        } else {
            binder.pbSearch.visibility = View.GONE
            binder.tvSearchError.visibility = View.GONE
            binder.rvSearchResult.visibility = View.VISIBLE
        }    }

    override fun onSearchError() {
        binder.pbSearch.visibility = View.GONE
        binder.tvSearchError.visibility = View.VISIBLE
        binder.rvSearchResult.visibility = View.GONE
    }

    override fun onClick(restaurant: Restaurant?) {

        if (null != searchAcion) {
            searchAcion?.onRestaurantSelected(restaurant)
        }    }

    lateinit var binder: FragmentSearchBinding
    private var searchAdapter: SearchAdapter? = null


    companion object {
        val TAG = SearchFragment::class.java.simpleName
        fun getInstance(bundle: Bundle?): SearchFragment {
            val fragment = SearchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_search
    }

    override fun initUI(binding: ViewDataBinding?, view: View) {
        binder = binding as FragmentSearchBinding
        binder.rvSearchResult.setLayoutManager(LinearLayoutManager(activity))
        binder.rvSearchResult.setAdapter(searchAdapter)
        subscribeForReactSearch()
    }

    fun  subscribeForReactSearch(){
        disposableObserver = ReactSearch().getReactiveSearcher(binder.svSearchQuery)
            .debounce(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith<DisposableObserver<String>>(object : DisposableObserver<String>() {
                override fun onNext(searchString: String) {

                    binder.tvSearchError.visibility = View.GONE
                    binder.rvSearchResult.visibility = View.GONE

                    if (!TextUtils.isEmpty(searchString)) {
                        binder.pbSearch.visibility = View.VISIBLE
                        searchAdapter?.search(searchString)
                    } else {
                        binder.pbSearch.visibility = View.GONE
                        searchAdapter?.clearSearch()
                    }
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })

    }


    private inner class ReactSearch internal constructor() {

        private val subject: PublishSubject<String>

        init {
            subject = PublishSubject.create()
        }

        internal fun getReactiveSearcher(searchView: SearchView): PublishSubject<String> {

            searchView.setOnQueryTextListener(object : OnQueryTextListener {

                override fun onQueryTextSubmit(query: String): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    subject.onNext(newText)
                    return true
                }
            })

            return subject
        }
    }


}
