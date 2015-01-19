package com.corp.rialzista.clickerapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ClickerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clicker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener{

        private TextView mRemoveTV, mAddTV, mCounterTV;
        private ImageView mRefresh;

        public PlaceholderFragment() {
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.removeTV:
                    this.decCounter();
                    break;
                case R.id.addTV:
                    this.incCounter();
                    break;
                case R.id.refreshCounter:
                    this.resetCounter();
                    break;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_clicker, container, false);

            this.mRemoveTV = (TextView) rootView.findViewById(R.id.removeTV);
            this.mAddTV = (TextView) rootView.findViewById(R.id.addTV);
            this.mCounterTV = (TextView) rootView.findViewById(R.id.clickCount);

            this.mRefresh = (ImageView) rootView.findViewById(R.id.refreshCounter);

            this.mRemoveTV.setOnClickListener(this);
            this.mAddTV.setOnClickListener(this);
            this.mRefresh.setOnClickListener(this);

            return rootView;
        }

        private void incCounter() {
            String sCounter = this.mCounterTV.getText().toString();
            try {
                int value = Integer.parseInt(sCounter);
                this.mCounterTV.setText(++value + "");
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


}
