package com.corp.rialzista.clickerapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Rialzista on 28.01.2015.
 */
public class ClickerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView vDec, vInc, vMenu;
    private ClickerAction mListener;

    public ClickerViewHolder(View itemView, ClickerAction listener) {
        super(itemView);
        this.mListener = listener;

        this.initializeVariables(itemView);
    }

    private void initializeVariables(View itemView) {
        this.vDec = (ImageView) itemView.findViewById(R.id.dec_counter);
        this.vInc = (ImageView) itemView.findViewById(R.id.inc_counter);
        this.vMenu = (ImageView) itemView.findViewById(R.id.menu_item);

        this.vDec.setOnClickListener(this);
        this.vInc.setOnClickListener(this);
        this.vMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.inc_counter:
                this.mListener.incButtonClick();
                break;
            case R.id.dec_counter:
                this.mListener.decButtonClick();
                break;
            case R.id.menu_item:
                this.mListener.menuButtonClick();
                break;
        }
    }

    public interface ClickerAction {
        public void incButtonClick();
        public void decButtonClick();
        public void menuButtonClick();
    }
}
