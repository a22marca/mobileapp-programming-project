package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class IslandActivity extends AppCompatActivity {

    private Intent wikiActivityIntent;
    private String wikiUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_island);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back arrow

        TextView nameTextView = findViewById(R.id.name_textview);
        TextView populationTextView = findViewById(R.id.population_textview);
        TextView governmentTextView = findViewById(R.id.government_textview);
        TextView areaTextView = findViewById(R.id.area_textview);
        TextView wikiUrlTextView = findViewById(R.id.wikiurl_textview);
        TextView locationTextView = findViewById(R.id.ocean_textview);

        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            nameTextView.setText(intentExtras.getString("Island_name"));
            populationTextView.setText(intentExtras.getString("Island_population") + " inhabitants");
            governmentTextView.setText(intentExtras.getString("Island_government"));
            areaTextView.setText(intentExtras.getString("Island_area") + " km\u00B2");
            locationTextView.setText(intentExtras.getString("Island_ocean"));
            wikiUrl = intentExtras.getString("Island_wikiUrl");
        }

        Button wikiButton = findViewById(R.id.wikipedia_button);
        wikiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wikiActivityIntent = new Intent(IslandActivity.this, WikiActivity.class);
                wikiActivityIntent.putExtra("wikiUrl",wikiUrl);
                startActivity(wikiActivityIntent);
            }
        });

    }
}