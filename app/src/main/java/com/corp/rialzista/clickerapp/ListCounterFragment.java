package com.corp.rialzista.clickerapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListCounterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListCounterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCounterFragment extends Fragment {

    public static final String LIST_COUNTER_FRAGMENT_TAG = "LIST_COUNTER_FRAGMENT_TAG";

    private OnFragmentInteractionListener mListener;
    private ClickerViewAdapter mAdapter;
    private RecyclerView mRecycler;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ListCounterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListCounterFragment newInstance() {
        ListCounterFragment fragment = new ListCounterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ListCounterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_counter, container, false);

        this.mRecycler = (RecyclerView) v.findViewById(R.id.recycler_view);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mAdapter = new ClickerViewAdapter(getActivity());

        this.mAdapter.add(new Counter("First counter", 0));
        this.mAdapter.add(new Counter("Second counter", 10));

        this.mRecycler.setAdapter(this.mAdapter);
        this.mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mRecycler.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

}
