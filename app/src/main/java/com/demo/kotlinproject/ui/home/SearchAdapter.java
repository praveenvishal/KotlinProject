package com.demo.kotlinproject.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.kotlinproject.R;
import com.demo.kotlinproject.data.api.response.search.Restaurant;
import com.demo.kotlinproject.data.api.response.search.SearchResponse;
import com.demo.kotlinproject.databinding.ItemSearchBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchItemViewHolder> {

    public interface ISearchAction {
        void onClick(Restaurant restaurant);
        void onSearchComplete(int count);
        void onSearchError();
    }
    private SearchResponse searchResponse;
    private ISearchAction searchAction;
    private DisposableObserver<SearchResponse> disposableObserver;

    public SearchAdapter(Context context) {
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchItemViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_search, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return null == searchResponse ? 0 : searchResponse.getRestaurants().size();

    }


    public void search(String searchString) {


    }

    public void clearSearch() {
        searchResponse = null;
        notifyDataSetChanged();
    }

    class SearchItemViewHolder extends RecyclerView.ViewHolder {

        private ItemSearchBinding binder;

        SearchItemViewHolder(ItemSearchBinding binder) {
            super(binder.getRoot());
            this.binder = binder;

            this.binder.getRoot().setOnClickListener(clickListener);
        }

        void onBind(int position) {

            binder.tvSearchItemName.setText(searchResponse.getRestaurants().get(position)
                    .getRestaurant().getName());

            binder.tvSearchItemAddress.setText(searchResponse.getRestaurants().get(position)
                    .getRestaurant().getLocation().getAddress());
        }

        private View.OnClickListener clickListener = view -> {

            if(null != searchAction) {
                searchAction.onClick(searchResponse.getRestaurants()
                        .get(getAdapterPosition()).getRestaurant());
            }
        };
    }

}
