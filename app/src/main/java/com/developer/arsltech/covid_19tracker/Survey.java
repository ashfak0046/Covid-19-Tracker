package com.developer.arsltech.covid_19tracker;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private EditText nameEditText,ageEditText;
//    Its not needed now :cityEditText,covidEditText,familyEditText
    DatabaseReference databaseReference;
    //For Validation
    AwesomeValidation awesomeValidation;
    ///Adding Radio Button
    RadioButton radioCovidYes,radioCovidNo,radioFamilyCovidYes,radioFamilyCovidNo;
//    RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        getSupportActionBar().setTitle("Survey");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

      ///Finding Id From XML
        saveDataButton =findViewById(R.id.SaveDataButtonId);
        loadDataButton = findViewById(R.id.LoadDataButtonId);
        nameEditText = findViewById(R.id.nameEditTextId);
        ageEditText = findViewById(R.id.ageEditTextId);
//        cityEditText = findViewById(R.id.cityEditTextId);
        ///Radio finding Id
        radioCovidYes = findViewById(R.id.CovidYesEditRadioID);
        radioCovidNo = findViewById(R.id.CovidNoEditRadioID);
        radioFamilyCovidYes = findViewById(R.id.CovidFamilyYesEditRadioID);
        radioFamilyCovidNo = findViewById(R.id.CovidFamilyNoEditRadioID);
        //covidEditText =findViewById(R.id.covidEditTextId);
        //familyEditText = findViewById(R.id.familyEditTextId);

        ///Intialize Validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        ///Add validation for name
        awesomeValidation.addValidation(Survey.this,R.id.nameEditTextId, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        ///Add validation for age
        awesomeValidation.addValidation(Survey.this,R.id.ageEditTextId,RegexTemplate.NOT_EMPTY,R.string.invalid_age);
        //Add validation for city
//        awesomeValidation.addValidation(Survey.this,R.id.cityEditTextId,RegexTemplate.NOT_EMPTY,R.string.invalid_city);

        //Add validation for covid
//        awesomeValidation.addValidation(Survey.this,R.id.CovidYesEditRadioID,RegexTemplate.NOT_EMPTY,R.string.invalid_covid_yes);
//        //Add validation for covid-family
//        awesomeValidation.addValidation(Survey.this,R.id.familyEditTextId,RegexTemplate.NOT_EMPTY,R.string.invalid_family_covid);
//        awesomeValidation.addValidation(Survey.this,R.id.CovidNoEditRadioID,RegexTemplate.NOT_EMPTY,R.string.invalid_covid_no);
//        awesomeValidation.addValidation(Survey.this,R.id.CovidFamilyYesEditRadioID,RegexTemplate.NOT_EMPTY,R.string.invalid_covid_family_yes);
//        awesomeValidation.addValidation(Survey.this,R.id.CovidFamilyNoEditRadioID,RegexTemplate.NOT_EMPTY,R.string.invalid_covid_family_no);


        databaseReference = FirebaseDatabase.getInstance().getReference("Covid_Details");
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
            ///Validation Success
            Toast.makeText(getApplicationContext(),"Data is added",Toast.LENGTH_SHORT).show();
            String name = nameEditText.getText().toString().trim();
            String age = ageEditText.getText().toString().trim();
//          String covid = covidEditText.getText().toString().trim();
//          String city = cityEditText.getText().toString().trim();
//          String family = familyEditText.getText().toString().trim();

            ///Radio Button String
            String covid = "";
            String family = "";

//            int isSelect = radioGroup.getCheckedRadioButtonId();

              //Radio Button Condition Check
            if(radioCovidYes.isChecked()){
                covid ="Yes";
            }

            if (radioCovidNo.isChecked()){
                covid="No";
            }
            if(radioFamilyCovidYes.isChecked()){
                family = "Yes";
            }
            if(radioFamilyCovidNo.isChecked()){
                family = "No";
            }
//            if(isSelect == -1){
//                Toast.makeText(Survey.this, "Please Select Yes or No", Toast.LENGTH_SHORT).show();
//            }
//            if(radioCovidYes.isChecked() && radioFamilyCovidYes.isChecked()){
////                Toast.makeText(Survey.this, "Check Yes", Toast.LENGTH_SHORT).show();
//                covid = "Yes";
//                family = "Yes";
//            }
//            if(radioCovidNo.isChecked() && radioFamilyCovidNo.isChecked()){
//                covid = "No";
//                family = "No";
//            }

            //
            String key = databaseReference.push().getKey();
            Student student = new Student(name,age,covid,family);
              databaseReference.child(key).setValue(student);
              nameEditText.setText("");
              ageEditText.setText("");


//            covidEditText .setText("");
//            cityEditText.setText("");
//            familyEditText.setText("");
        }

        else{
            Toast.makeText(getApplicationContext(), "Please Enter Your Information", Toast.LENGTH_SHORT).show();
        }
    }

//    For backspace
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}