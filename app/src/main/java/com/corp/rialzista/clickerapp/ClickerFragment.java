package com.corp.rialzista.clickerapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import at.markushi.ui.CircleButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClickerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClickerFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String CLICKER_FRAGMENT_TAG = "CLICKER_FRAGMENT_TAG";
    private static final String SAVED_VALUE = "counter_value";

    // TODO: Rename and change types of parameters
    private String mBeginCounterValue;

    private TextView mCounterTV;
    private CircleButton mIncCounter, mRefresh, mRemove;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment and set begin value.
     *
     * @param beginValue The value at which to begin the expense.
     * @return A new instance of fragment ClickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClickerFragment newInstance(String beginValue) {
        ClickerFragment fragment = new ClickerFragment();
        Bundle args = new Bundle();
        args.putString(SAVED_VALUE, beginValue);
        fragment.setArguments(args);
        return fragment;
    }

    public ClickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null)
            this.mBeginCounterValue = getArguments().getString(SAVED_VALUE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.one_clicker, container, false);

        this.mRemove = (CircleButton) rootView.findViewById(R.id.dec_counter);
        this.mRemove.setOnClickListener(this);

        this.mRefresh = (CircleButton) rootView.findViewById(R.id.refreshCounter);
        this.mRefresh.setOnClickListener(this);

        this.mCounterTV = (TextView) rootView.findViewById(R.id.mCounterTV);
        this.mCounterTV.setText(this.mBeginCounterValue);

        this.mIncCounter = (CircleButton) rootView.findViewById(R.id.inc_counter_btn);
        this.mIncCounter.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dec_counter:
                this.decCounter();
                break;
            case R.id.inc_counter_btn:
                this.incCounter();
                break;
            case R.id.refreshCounter:
                this.resetCounter();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save UI state changes ti the savedInstanceState
        // This bundle will be passed to OnCreate if this process is
        // killed and restarted.
        //outState.putString(SAVED_VALUE, this.mCounterTV.getText().toString());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void incCounter() {
        String sCounter = this.mCounterTV.getText().toString();
        try {
            int value = Integer.parseInt(sCounter);
            this.mCounterTV.setText(++value + "");
            this.mBeginCounterValue = value + "";
        } catch (Exception ex) {
            System.out.println("Could not parse " + ex);
        }
    }

    private void decCounter() {
        String sCounter = this.mCounterTV.getText().toString();
        try {
            int value = Integer.parseInt(sCounter);
            this.mCounterTV.setText(--value + "");
        } catch (Exception ex) {
            System.out.println("Could not parse " + ex);
        }
    }

    private void resetCounter() {
        this.mCounterTV.setText("0");
    }
}
