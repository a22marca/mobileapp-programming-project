package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener, AdapterView.OnItemSelectedListener {

    private ArrayList<Island> islands;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Intent islandActivityIntent;
    private Intent aboutActivityIntent;
    private final String ISLANDS_JSON_URL = "https://mobprog.webug.se/json-api?login=a22marca";
    private Spinner filterSpinner;
    private SharedPreferences filterPreferenceRef;
    private SharedPreferences.Editor filterPreferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // find Views
        Button aboutButton = findViewById(R.id.about_button);
        filterSpinner = findViewById(R.id.filter_spinner);
        filterSpinner.setOnItemSelectedListener(this);

        // button to start AboutActivity
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutActivityIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutActivityIntent);
            }
        });

        //SharedPreferences
        filterPreferenceRef = getPreferences(MODE_PRIVATE);
        filterPreferenceRef  = getSharedPreferences("filterPreference", MODE_PRIVATE);
        filterPreferenceRef = getSharedPreferences("selectedFilter", MODE_PRIVATE);
        filterPreferenceEditor = filterPreferenceRef.edit();

        //list for RecycleView
        islands = new ArrayList<>();

        //Start IslandActivity with Intents
        recyclerViewAdapter = new RecyclerViewAdapter(this, islands, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Island island) { //item clicked in RecycleView
                islandActivityIntent = new Intent(MainActivity.this,IslandActivity.class);
                islandActivityIntent.putExtra("Island_name",island.getName());
                islandActivityIntent.putExtra("Island_population",String.valueOf(island.getPopulation()));
                islandActivityIntent.putExtra("Island_government",island.getGovernment());
                islandActivityIntent.putExtra("Island_ocean",island.getOcean());
                islandActivityIntent.putExtra("Island_capital", island.getCapital());
                islandActivityIntent.putExtra("Island_wikiUrl",island.getWikiUrl());
                islandActivityIntent.putExtra("Island_area",String.valueOf(island.getArea()));
                islandActivityIntent.putExtra("Island_imageUrl",island.getImageUrl());
                startActivity(islandActivityIntent);
            }
        });

        RecyclerView view = findViewById(R.id.recyclerview_islands);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(recyclerViewAdapter);
    }

    // downloaded JSON
    @Override
    public void onPostExecute(String json){
        try {
            // json data to a json array
            JSONArray jsonIslandsArray = new JSONArray(json);

            for (int i = 0; i < jsonIslandsArray.length(); i++){
                // get object from Json data
                JSONObject islandObject = jsonIslandsArray.getJSONObject(i);
                // get values from json object
                JSONObject islandAuxDataObject = islandObject.getJSONObject("auxdata");
                String name = islandObject.getString("name");
                int population = islandObject.getInt("cost");
                String government = islandObject.getString("company");
                String location = islandObject.getString("location");
                String wikiUrl = islandAuxDataObject.getString("wikiUrl");
                String capital = islandObject.getString("category");
                int area = islandObject.getInt("size");
                String imageUrl = islandAuxDataObject.getString("imageUrl");

                //get selected filter
                String filter = filterPreferenceRef.getString("filterPreference","");
                // filter islands that should not be added
                if (!location.equals(filter)){
                    islands.add(new Island(
                            name,
                            population,
                            government,
                            location,
                            wikiUrl,
                            capital,
                            area,
                            imageUrl
                    ));
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //update RecyclerView contents
        recyclerViewAdapter.notifyDataSetChanged();
    }


    // Item selected in spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        islands.removeAll(islands); //remove islands
        //set selected filter in SharedPreferences
        if (pos==1){
            filterPreferenceEditor.putString("filterPreference","Pacific Ocean");
        }else if (pos==2){
            filterPreferenceEditor.putString("filterPreference","Atlantic Ocean");
        }else {
            filterPreferenceEditor.putString("filterPreference","");
        }
        filterPreferenceEditor.putInt("selectedFilter",pos); // save spinner position for onResume
        filterPreferenceEditor.apply();
        // get JSON
        new JsonTask(this).execute(ISLANDS_JSON_URL);
    }


    //spinner implemented method
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onResume(){
        super.onResume();
        //set selected filter if it exists, otherwise index 0
        filterSpinner.setSelection(filterPreferenceRef.getInt("selectedFilter",0));
    }

}