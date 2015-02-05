package com.corp.rialzista.clickerapp;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rialzista on 28.01.2015.
 */
public class ClickerViewAdapter extends RecyclerView.Adapter<ClickerViewHolder> implements ClickerViewHolder.ClickerAction {

    private List<Counter> mItems = new ArrayList<Counter>();
    private Context mCtx;

    public ClickerViewAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    @Override
    public ClickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clicker ,parent , false);

        ClickerViewHolder vh = new ClickerViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ClickerViewHolder holder, int position) {
        Counter item = this.mItems.get(position);

        holder.mTitle.setText(item.getTitle());
        holder.mCounterValue.setText(item.getCounterValue() + "");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void add(Counter item) {
        this.mItems.add(item);
    }

    public void clear() {
        this.mItems.clear();
    }

    public void addRange(Collection<Counter> collection) {
        this.mItems.addAll(collection);
    }

    /**********************************************************************************************
     * ClickerViewHolder.ClickerAction
     */
    @Override
    public void incButtonClick(int position) {
        Counter item = this.mItems.get(position);

        item.setCounterValue(item.getCounterValue() + 1);

        synchronized (this) {
            notifyDataSetChanged();
        }

    }

    @Override
    public void decButtonClick(int position) {
        Counter item = this.mItems.get(position);

        item.setCounterValue(item.getCounterValue() - 1);

        synchronized (this) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void menuButtonClick(int position, View v) {
        this.showPop(v);
    }

    @Override
    public void refreshButtonClick(int position) {
        Counter item = this.mItems.get(position);

        item.setCounterValue(0);

        synchronized (this) {
            notifyDataSetChanged();
        }
    }

    private void showPop(View v) {
        PopupMenu popupMenu = new PopupMenu(this.mCtx, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();
    }


}
