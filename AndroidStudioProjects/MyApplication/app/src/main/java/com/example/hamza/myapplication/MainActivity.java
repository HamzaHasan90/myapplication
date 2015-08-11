package com.example.hamza.myapplication;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.hamza.myapplication.com.example.hamza.myapplication.db.ToursDataSource;
import com.example.hamza.myapplication.model.Tour;
import com.example.hamza.myapplication.xml.ToursPullParser;
import com.exploreca.tourfinder.R;


public class MainActivity extends ListActivity {

    public static final String LOGTAG = "EXPLORECA";
    public static final String USERNAME = "pref_username";
    public static final String VIEWIMAGE = "pref_viewimages";

    private SharedPreferences settings;
    private OnSharedPreferenceChangeListener listener;

    ToursDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = PreferenceManager.getDefaultSharedPreferences(this);

        listener = new OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                  String key) {
                MainActivity.this.refreshDisplay();
            }
        };
        settings.registerOnSharedPreferenceChangeListener(listener);

        ToursPullParser parser = new ToursPullParser();
        List<Tour> tours = parser.parseXML(this);

        datasource = new ToursDataSource(this);
        datasource.open();
        createData();

        ArrayAdapter<Tour> adapter = new ArrayAdapter<Tour>(this,
                android.R.layout.simple_list_item_1, tours);
        setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setPreference(View v) {
        Log.i(LOGTAG, "Clicked set");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void refreshDisplay() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        datasource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.close();
    }

    private void createData(){
        Tour tour = new Tour();
        tour.setTitle("Salton sea");
        tour.setDescription("A tour to Salton sea");
        tour.setPrice(400);
        tour.setImage("salton_sea");
        tour=datasource.create(tour);
        Log.i(LOGTAG,"Tours id is "+tour.getId());


        tour = new Tour();
        tour.setTitle("Amman ");
        tour.setDescription("A tour to Amman");
        tour.setPrice(400);
        tour.setImage("amman");
        tour=datasource.create(tour);
        Log.i(LOGTAG,"Tours id is "+tour.getId());

        tour = new Tour();
        tour.setTitle("Irbid");
        tour.setDescription("A tour to Irbid");
        tour.setPrice(400);
        tour.setImage("irbid");
        tour=datasource.create(tour);
        Log.i(LOGTAG,"Tours id is "+tour.getId());

    }

}
