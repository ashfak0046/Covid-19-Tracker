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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.developer.arsltech.covid_19tracker.Model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.widget.AdapterView.*;

public class Survey extends AppCompatActivity {

    private Button saveDataButton,loadDataButton;
    private EditText nameEditText,ageEditText,covidEditText,cityEditText,familyEditText;
    DatabaseReference databaseReference;
    //////
    AwesomeValidation awesomeValidation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        getSupportActionBar().setTitle("Survey");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("Covid_Summary");
        saveDataButton =findViewById(R.id.SaveDataButtonId);
        loadDataButton = findViewById(R.id.LoadDataButtonId);
        nameEditText = findViewById(R.id.nameEditTextId);
        ageEditText = findViewById(R.id.ageEditTextId);
        covidEditText =findViewById(R.id.covidEditTextId);
        cityEditText = findViewById(R.id.cityEditTextId);
        familyEditText = findViewById(R.id.familyEditTextId);

        ///Intialize Validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        ///Add validation for name
        awesomeValidation.addValidation(Survey.this,R.id.nameEditTextId, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        ///Add validation for age
        awesomeValidation.addValidation(Survey.this,R.id.ageEditTextId,RegexTemplate.NOT_EMPTY,R.string.invalid_age);
        //Add validation for city
        awesomeValidation.addValidation(Survey.this,R.id.cityEditTextId,RegexTemplate.NOT_EMPTY,R.string.invalid_city);
        //Add validation for covid
        awesomeValidation.addValidation(Survey.this,R.id.covidEditTextId,RegexTemplate.NOT_EMPTY,R.string.invalid_covid);
        //Add validation for covid-family
        awesomeValidation.addValidation(Survey.this,R.id.familyEditTextId,RegexTemplate.NOT_EMPTY,R.string.invalid_family_covid);



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

        ///check Validation
        if(awesomeValidation.validate()){
            ///On Sucess
            Toast.makeText(getApplicationContext(),"Data is added",Toast.LENGTH_SHORT).show();
            String name = nameEditText.getText().toString().trim();
            String age = ageEditText.getText().toString().trim();
            String covid = covidEditText.getText().toString().trim();
            String city = cityEditText.getText().toString().trim();
            String family = familyEditText.getText().toString().trim();
            String key = databaseReference.push().getKey();
            Student student = new Student(name,age,covid,city,family);
            databaseReference.child(key).setValue(student);
            nameEditText.setText("");
            ageEditText.setText("");
            covidEditText .setText("");
            cityEditText.setText("");
            familyEditText.setText("");

        }
        else{
            Toast.makeText(getApplicationContext(), "Please Enter Your Information", Toast.LENGTH_SHORT).show();
        }
    }

}