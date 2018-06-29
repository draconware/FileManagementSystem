package com.example.android.filemanagementsystem;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class addresult extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String fileid,filename,department,holder;
    private TextView textView1,textView2,textView3,textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresult);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    //    SharedPreferences.Editor sharedPreferenceseditor = sharedPreferences.edit();

        fileid = sharedPreferences.getString("fileid","Not Available");
        filename = sharedPreferences.getString("filename","Not Available");
        department = sharedPreferences.getString("filedepartment","Not Available");
        holder = sharedPreferences.getString("user","Not Available");

        textView1 = (TextView)findViewById(R.id.textViewfileid);
        textView2 = (TextView)findViewById(R.id.textViewfilename);
        textView3 = (TextView)findViewById(R.id.textViewdepartment);
        textView4 = (TextView)findViewById(R.id.textViewholder);

        textView1.setText(fileid);
        textView2.setText(filename);
        textView3.setText(department);
        textView4.setText(holder);
    }
}
