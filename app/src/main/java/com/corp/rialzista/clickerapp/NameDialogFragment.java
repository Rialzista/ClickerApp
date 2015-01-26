package com.corp.rialzista.clickerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.internal.widget.TintEditText;

/**
 * Created by Rialzista on 26.01.2015.
 */
public class NameDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    private TintEditText mClickerTitle;
    private NameDialogInteractionListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        this.mClickerTitle = new TintEditText(getActivity());

        builder.setTitle(getString(R.string.set_clicker_name))
                .setCancelable(true)
                .setPositiveButton("Ok", this)
                .setNegativeButton("Cancel", this)
                .setView(this.mClickerTitle);

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (NameDialogInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                this.mListener.onNameSet(this.mClickerTitle.getText().toString());
                break;
            case Dialog.BUTTON_NEGATIVE:
                this.dismiss();
                break;
        }
    }

    public interface NameDialogInteractionListener {
        public void onNameSet(String clickerTitle);
    }
}
