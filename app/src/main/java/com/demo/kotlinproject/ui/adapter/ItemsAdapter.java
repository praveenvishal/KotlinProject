package com.demo.kotlinproject.ui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.kotlinproject.R;
import com.demo.kotlinproject.databinding.ItemRestDetailsBinding;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    public interface IIteamAction {
        void onClick(int position);
    }

    public static class ItemInfo {
        private String itemName;
        private @DrawableRes
        int itemIcon;

        public ItemInfo(String itemName, @DrawableRes int itemIcon) {
            this.itemName = itemName;
            this.itemIcon = itemIcon;
        }

        public String getItemName() {
            return itemName;
        }

        public int getItemIcon() {
            return itemIcon;
        }
    }

    private ArrayList<ItemInfo> itemList;
    private IIteamAction itemAction;

    public ItemsAdapter(IIteamAction itemAction) {
        itemList = new ArrayList<>();
        this.itemAction = itemAction;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemRestDetailsBinding binder = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_rest_details, parent, false);

        return new ItemViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return null == itemList ? 0 : itemList.size();
    }

    public void bindData(ArrayList<ItemInfo> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemRestDetailsBinding binder;

        ItemViewHolder(ItemRestDetailsBinding binder) {

            super(binder.getRoot());
            this.binder = binder;
            this.binder.getRoot().setOnClickListener(clickListener);
        }

        void onBind(int position) {

            binder.tvItemDetails.setText(itemList.get(position).getItemName());
            binder.ivItemIcon.setImageResource(itemList.get(position).getItemIcon());
        }

        private View.OnClickListener clickListener = view -> {

            if(null != itemAction) {
                itemAction.onClick(getAdapterPosition());
            }
        };
    }

    public static class ItemDecorate extends RecyclerView.ItemDecoration {

        private static final int OFFSET = 8;
        private int margins;

        public ItemDecorate(Context context, @DimenRes int margin) {
            margins = context.getResources().getDimensionPixelOffset(margin);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(margins, margins, margins, margins);
        }
    }
}
