package com.developer.arsltech.covid_19tracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.developer.arsltech.covid_19tracker.Model.Student;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {


    private Activity context;
    private List<Student> studentList;

    public CustomAdapter(Activity context, List<Student> studentList) {
        super(context, R.layout.sample, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample, null, true);
        Student student = studentList.get(position);
        TextView t1 = view.findViewById(R.id.nameTextViewID);
        TextView t2 = view.findViewById(R.id.ageTextViewID);
//        TextView t3 = view.findViewById(R.id.cityTextViewId);
        TextView t4 = view.findViewById(R.id.covidTextViewID);
        TextView t5 = view.findViewById(R.id.familyTextViewId);
        t1.setText("Name: " + student.getName());
        t2.setText("Age: " + student.getAge());
//        t3.setText("City: "+student.getCity());
        t4.setText("Covid Positive: " + student.getCovid());
        t5.setText("Covid Patient In Family: "+student.getFamily());

        return view;
    }
}

