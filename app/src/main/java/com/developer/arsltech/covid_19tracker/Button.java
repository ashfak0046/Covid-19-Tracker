package com.developer.arsltech.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;

public class Button extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

    }
    public void goTrackCountries(View view) {

        startActivity(new Intent(getApplicationContext(),AffectedCountries.class));

    }
    public void GlobalStates(View view) {

        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
    public void Covid(View view) {

        startActivity(new Intent(getApplicationContext(),Covid.class));

    }
    public void CovidVaccine(View view) {

        startActivity(new Intent(getApplicationContext(),Vaccine.class));

    }
    public void Covid_Survey(View view) {

    startActivity(new Intent(getApplicationContext(), Survey.class));

   }

}