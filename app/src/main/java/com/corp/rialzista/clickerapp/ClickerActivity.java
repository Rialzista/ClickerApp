package com.corp.rialzista.clickerapp;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;


public class ClickerActivity extends ActionBarActivity implements
        ClickerFragment.OnFragmentInteractionListener,
        ListCounterFragment.OnFragmentInteractionListener,
        NameDialogFragment.NameDialogInteractionListener {

    public static final String CLICKER_TITLE = "CLICKER_TITLE";
    public static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    public static final String SET_CLICKER_NAME_DIALOG_TAG = "SET_CLICKER_NAME_DIALOG_TAG";

    public String current_visible_fragment_tag;

    private FragmentManager mFM;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String toolbarTitle = "";

        setContentView(R.layout.activity_clicker);

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        this.mFM = getSupportFragmentManager();

        if (savedInstanceState == null) {
            this.mFM.beginTransaction()
                    .add(R.id.container, ClickerFragment.newInstance("0"), ClickerFragment.CLICKER_FRAGMENT_TAG)
                    .commit();
            this.current_visible_fragment_tag = ClickerFragment.CLICKER_FRAGMENT_TAG;
        } else {
            this.current_visible_fragment_tag = savedInstanceState.getString(FRAGMENT_TAG, "");
            toolbarTitle = savedInstanceState.getString(CLICKER_TITLE, "");

            this.restoreState(this.current_visible_fragment_tag);
        }

        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_clicker, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(CLICKER_TITLE, getSupportActionBar().getTitle().toString());
        outState.putString(FRAGMENT_TAG, this.current_visible_fragment_tag);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.set_counter_name:
                this.showSetCounterTitleDialog();
                return true;
            case R.id.change_ui_mode:
                this.showAnotherFragment();
                return true;
            case R.id.show_history:
                this.showHistoryFragment();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showHistoryFragment() {
        Toast.makeText(this, "Handle showHistoryFragment", Toast.LENGTH_SHORT).show();
    }

    public void showAnotherFragment() {
        Fragment clickerFragment = this.mFM.findFragmentByTag(ClickerFragment.CLICKER_FRAGMENT_TAG);
        Fragment listClickerFragment = this.mFM.findFragmentByTag(ListCounterFragment.LIST_COUNTER_FRAGMENT_TAG);

        if (clickerFragment != null && clickerFragment.isVisible()) {
            this.mFM.beginTransaction().hide(clickerFragment).commit();
            if (listClickerFragment != null)
                this.mFM.beginTransaction().show(listClickerFragment).commit();
            else
                this.mFM.beginTransaction()
                        .add(R.id.container, ListCounterFragment.newInstance(), ListCounterFragment.LIST_COUNTER_FRAGMENT_TAG).commit();

            this.current_visible_fragment_tag = ListCounterFragment.LIST_COUNTER_FRAGMENT_TAG;
        } else {
            if (listClickerFragment != null && listClickerFragment.isVisible()) {
                this.mFM.beginTransaction().hide(listClickerFragment).commit();
                this.mFM.beginTransaction().show(clickerFragment).commit();

                this.current_visible_fragment_tag = ClickerFragment.CLICKER_FRAGMENT_TAG;
            }
        }

        this.mFM.executePendingTransactions();
    }

    public void restoreState(String what_fragment_shown) {
        Fragment clickerFragment = this.mFM.findFragmentByTag(ClickerFragment.CLICKER_FRAGMENT_TAG);
        Fragment listClickerFragment = this.mFM.findFragmentByTag(ListCounterFragment.LIST_COUNTER_FRAGMENT_TAG);

        if (clickerFragment != null && what_fragment_shown.equals(ClickerFragment.CLICKER_FRAGMENT_TAG)) {
            this.mFM.beginTransaction().show(clickerFragment);
            if (listClickerFragment != null)
                this.mFM.beginTransaction().hide(listClickerFragment);
        } else if (listClickerFragment != null && what_fragment_shown.equals(ListCounterFragment.LIST_COUNTER_FRAGMENT_TAG)) {
            this.mFM.beginTransaction().show(listClickerFragment);
            if (clickerFragment != null)
                this.mFM.beginTransaction().hide(clickerFragment);
        }

    }

    public void showSetCounterTitleDialog() {
        NameDialogFragment dialog = new NameDialogFragment();
        dialog.show(getSupportFragmentManager(), SET_CLICKER_NAME_DIALOG_TAG);
    }

    public void changeVisibleStateFragment(String fragment_Tag) {
        Fragment fr = this.mFM.findFragmentByTag(fragment_Tag);

        if (fr != null ) {
            if (fr.isVisible())
                this.mFM.beginTransaction().hide(fr).commit();
            else
                this.mFM.beginTransaction().show(fr).commit();
            this.mFM.executePendingTransactions();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onNameSet(String clickerTitle) {
        getSupportActionBar().setTitle(clickerTitle);
    }
}
