package com.example.android.filemanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class searchFile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_file);
    }

    public void searchfiledetails(){
        Intent i = new Intent(this,searchFiledetails.class);
        startActivity(i);
    }
}
