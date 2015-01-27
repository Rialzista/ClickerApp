package com.corp.rialzista.clickerapp;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class ClickerActivity extends ActionBarActivity implements
        ClickerFragment.OnFragmentInteractionListener,
        NameDialogFragment.NameDialogInteractionListener {

    public static final String CLICKER_TITLE = "clicker_titile";

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String toolbarTitle = "";

        setContentView(R.layout.activity_clicker);

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, ClickerFragment.newInstance("0"))
                    .commit();
        } else {
            toolbarTitle = savedInstanceState.getString(CLICKER_TITLE, "");
        }

        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clicker, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(CLICKER_TITLE, getSupportActionBar().getTitle().toString());
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
        NameDialogFragment dialog = new NameDialogFragment();
        dialog.show(getSupportFragmentManager(), "My Dialog");
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onNameSet(String clickerTitle) {
        getSupportActionBar().setTitle(clickerTitle);
    }
}
