package com.rk.hideonscroll.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rk.hideonscroll.R;
import com.rk.hideonscroll.adapter.viewholder.RecyclerHeaderViewHolder;
import com.rk.hideonscroll.adapter.viewholder.RecyclerItemViewHolder;

import java.util.List;

/**
 * Created by Rutul on 02-03-2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //added view types
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;

    private List<String> mItemList;

    public RecyclerAdapter(List<String> itemList) {
        mItemList = itemList;
    }

    /**
     * Based on viewType, it will return Header or Item for RecycleView
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == TYPE_ITEM) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
            return RecyclerItemViewHolder.newInstance(view);
        } else if (viewType == TYPE_HEADER) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_header, parent, false);
            return new RecyclerHeaderViewHolder(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types    correctly");
    }

    /**
     * Binds a correct View for the Adapter
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (!isPositionHeader(position)) {
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            // we are taking header in to account so all of our items are correctly positioned
            String itemText = mItemList.get(position - 1);
            holder.setItemText(itemText);
        }
    }

    /**
     * Get total item count
     * @return
     */
    public int getBasicItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    //our new getItemCount() that includes header View

    /**
     * Get total of item count and header
     * @return
     */
    @Override
    public int getItemCount() {
        return getBasicItemCount() + 1; // header
    }

    /**
     * For very first posotion, return TypeHeader, and for rest return TypeItem.
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    /**
     * Checks if given position is a header
     * @param position
     * @return
     */
    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}