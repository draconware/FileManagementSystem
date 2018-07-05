package com.example.android.filemanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class searchFile extends AppCompatActivity {

    private EditText editTextfileid,editTextfilename,editTextfiledepartment;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedpreferenceseditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_file);

        editTextfileid = (EditText)findViewById(R.id.editTextsearchfileid);
        editTextfilename = (EditText)findViewById(R.id.editTextsearchfilename);
        editTextfiledepartment = (EditText)findViewById(R.id.editTextsearchfiledepartment);
    }

    public void searchfiletab(View v){
        final String fileid = editTextfileid.getText().toString().trim();
        final String filename = editTextfilename.getText().toString().trim();
        final String filedepartment = editTextfiledepartment.getText().toString().trim();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(searchFile.this);
        sharedpreferenceseditor = sharedPreferences.edit();

        sharedpreferenceseditor.putString("searchfileid",fileid);
        sharedpreferenceseditor.putString("searchfilename",filename);
        sharedpreferenceseditor.putString("searchfiledepartment",filedepartment);

        sharedpreferenceseditor.commit();
        Intent i = new Intent(v.getContext(),SearchFileList.class);
        startActivity(i);
    }

}
