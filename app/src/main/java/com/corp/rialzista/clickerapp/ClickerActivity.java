package com.corp.rialzista.clickerapp;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ClickerActivity extends ActionBarActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

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
        if (id == R.id.set_counter_name) {
            showSetCounterTitleDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showSetCounterTitleDialog() {
        DialogFragment dialog = new DialogFragment();
        dialog.show(getSupportFragmentManager(), "My Dialog");
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener{

        private TextView mCounterTV;
        private RelativeLayout mClickCount;
        private ImageView mRefresh, mRemove;

        public PlaceholderFragment() {
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dec_counter:
                    this.decCounter();
                    break;
                case R.id.clickCount:
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
            View rootView = inflater.inflate(R.layout.one_clicker, container, false);

            this.mRemove = (ImageView) rootView.findViewById(R.id.dec_counter);
            this.mRemove.setOnClickListener(this);

            this.mClickCount = (RelativeLayout) rootView.findViewById(R.id.clickCount);
            this.mClickCount.setOnClickListener(this);

            this.mRefresh = (ImageView) rootView.findViewById(R.id.refreshCounter);
            this.mRefresh.setOnClickListener(this);

            this.mCounterTV = (TextView) rootView.findViewById(R.id.mCounterTV);

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
