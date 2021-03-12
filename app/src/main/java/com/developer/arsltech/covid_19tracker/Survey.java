package com.developer.arsltech.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.arsltech.covid_19tracker.Model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.widget.AdapterView.*;

public class Survey extends AppCompatActivity {

    private Button saveDataButton,loadDataButton;
    private EditText nameEditText,ageEditText,covidEditText;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        databaseReference = FirebaseDatabase.getInstance().getReference("Covid_Summary");
        saveDataButton =findViewById(R.id.SaveDataButtonId);
        loadDataButton = findViewById(R.id.LoadDataButtonId);
        nameEditText = findViewById(R.id.nameEditTextId);
        ageEditText = findViewById(R.id.ageEditTextId);
        covidEditText =findViewById(R.id.covidEditTextId);
        loadDataButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Survey.this,Survey_Details.class);
                startActivity(intent);
            }
        });
        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }
    public void saveData(){
        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String covid = covidEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();
        Student student = new Student(name,age,covid);
        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(), "Data is added", Toast.LENGTH_SHORT).show();

        nameEditText.setText("");
        ageEditText.setText("");
       covidEditText .setText("");




    }
}