package com.developer.arsltech.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.developer.arsltech.covid_19tracker.Model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Survey_Details extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private List<Student>studentList;
    private CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey__details);
        getSupportActionBar().setTitle("Survey Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView = findViewById(R.id.listViewId);

        databaseReference = FirebaseDatabase.getInstance().getReference("Covid_Details");
        studentList = new ArrayList<>();

        customAdapter = new CustomAdapter(Survey_Details.this, studentList);


    }

    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Student student = dataSnapshot1.getValue(Student.class);
                    studentList.add(student);
                }
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        super.onStart();
    }
    ////
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}

