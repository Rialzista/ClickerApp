package com.corp.rialzista.clickerapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rialzista on 28.01.2015.
 */
public class ClickerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected ImageView vDec, vInc, vMenu;
    protected ClickerAction mListener;
    protected TextView mTitle, mCounterValue;

    public ClickerViewHolder(View itemView, ClickerAction listener) {
        super(itemView);
        this.mListener = listener;

        this.initializeVariables(itemView);
    }

    private void initializeVariables(View itemView) {
        this.vDec = (ImageView) itemView.findViewById(R.id.dec_counter);
        this.vInc = (ImageView) itemView.findViewById(R.id.inc_counter);
        this.vMenu = (ImageView) itemView.findViewById(R.id.menu_item);

        this.mTitle = (TextView) itemView.findViewById(R.id.clicker_name);
        this.mCounterValue = (TextView) itemView.findViewById(R.id.counter_count);

        this.vDec.setOnClickListener(this);
        this.vInc.setOnClickListener(this);
        this.vMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.inc_counter:
                this.mListener.incButtonClick(getPosition());
                break;
            case R.id.dec_counter:
                this.mListener.decButtonClick(getPosition());
                break;
            case R.id.menu_item:
                this.mListener.menuButtonClick(getPosition(), v);
                break;
        }
    }

    public interface ClickerAction {
        public void incButtonClick(int position);
        public void decButtonClick(int position);
        public void menuButtonClick(int position, View view);
    }
}
