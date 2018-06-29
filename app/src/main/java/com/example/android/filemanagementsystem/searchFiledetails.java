package com.example.android.filemanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class searchFiledetails extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<listItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filedetails);
        Log.i("message","running");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for(int i=0;i<=4;i++){
            listItem listItem = new listItem(
                    "123" + (i+1),
                    "aiims bhopal",
                    "devloper",
                    "devloper"
            );
            listItems.add(listItem);
        }

        adapter = new searchAdapter(listItems,this);

        recyclerView.setAdapter(adapter);
    }

}
