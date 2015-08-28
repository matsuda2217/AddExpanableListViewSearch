package com.example.tt.addexpanablelistviewsearch;

import android.app.SearchManager;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TimingLogger;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends ActionBarActivity  implements SearchView.OnQueryTextListener,SearchView.OnClickListener {
TextView tx1,tx2, tx3;

    private  SearchView searchView;
    private  ArrayListAdapter arrayListAdapter;
    private ExpandableListView expandableListView;
    private ArrayList<Continent> continents = new ArrayList<Continent>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });
        displayList();
        expandAll();

        //-------------------------------------------------------------
       /* tx1 = (TextView) findViewById(R.id.textView);
        tx2 = (TextView) findViewById(R.id.textView2);
        tx3 = (TextView) findViewById(R.id.textView3);
       // tx1.setText(String.valueOf(TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(400000))));
        tx2.setText(String.valueOf(TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(400000))));
       // tx3.setText(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(400000) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(400000))));

        String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(40000),
                TimeUnit.MILLISECONDS.toMinutes(40000) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(40000)),
                TimeUnit.MILLISECONDS.toSeconds(40000) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(40000)));*/
    }

    private void expandAll() {
        int count = arrayListAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            expandableListView.expandGroup(i);
        }

    }

    private void displayList() {
        loadSomeData();
        expandableListView = (ExpandableListView) findViewById(R.id.expendableList);
        arrayListAdapter = new ArrayListAdapter(MainActivity.this, continents);
        expandableListView.setAdapter(arrayListAdapter);
    }

    private void loadSomeData() {
        ArrayList<Contry> contries = new ArrayList<Contry>();
        Contry contry = new Contry("SIN", "Singapore", 10000000);
        contries.add(contry);
        contry = new Contry("VN", "Vietnam", 90000000);
        contries.add(contry);
         contry = new Contry("CAM", "Caambodia", 70000000);
        contries.add(contry);

        Continent continent = new Continent("Asia", contries);
        continents.add(continent);

        contries = new ArrayList<Contry>();
        contry = new Contry("BER", "Bermuda", 10000000);
        contries.add(contry);
        contry = new Contry("USA", "United state of America", 200000000);
        contries.add(contry);
        contry = new Contry("CAN", "Caanada", 70000000);
        contries.add(contry);

        continent = new Continent("North America", contries);
        continents.add(continent);

        contries = new ArrayList<Contry>();
        contry = new Contry("BER", "Bermuda", 10000000);
        contries.add(contry);
        contry = new Contry("USA", "United state of America", 200000000);
        contries.add(contry);
        contry = new Contry("CAN", "Caanada", 70000000);
        contries.add(contry);

        continent = new Continent("North America", contries);
        continents.add(continent);


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        arrayListAdapter.fillterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        arrayListAdapter.fillterData(newText);
        expandAll();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
