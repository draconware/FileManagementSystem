package com.example.android.filemanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;

import java.security.PublicKey;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewfileid;
        private TextView textViewfilename;
        private TextView textViewfileholder;
        private TextView textViewfiledepartment;
        private Button buttonview;

        public ViewHolder(View itemView) {

            super(itemView);

            textViewfileid = itemView.findViewById(R.id.searchfilelistfileid);
            textViewfilename = itemView.findViewById(R.id.searchfilelistfilename);
            textViewfileholder = itemView.findViewById(R.id.searchfilelistfileholder);
            textViewfiledepartment = itemView.findViewById(R.id.searchfilelistfiledepartment);

            buttonview = itemView.findViewById(R.id.buttonsearchfilelist);
        }
    }

    private List<FileItems> finalfileItems;
    private Context context;


    public Adapter(SearchFileList inicontext, List<FileItems> fileItems) {
        finalfileItems = fileItems;
        context = inicontext.getApplicationContext();
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.items, parent, false);


        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final Adapter.ViewHolder viewHolder, int position) {

        final FileItems fileItems = finalfileItems.get(position);

        TextView textViewid = viewHolder.textViewfileid;
        textViewid.setText(fileItems.getFileid().toString().trim());

        TextView textViewname = viewHolder.textViewfilename;
        textViewname.setText(fileItems.getFilename().toString().trim());

        TextView textViewholder = viewHolder.textViewfileholder;
        textViewholder.setText(fileItems.getFileholder().toString().trim());

        TextView textViewdepartment = viewHolder.textViewfiledepartment;
        textViewdepartment.setText(fileItems.getFiledepartment().toString().trim());

        Button button = viewHolder.buttonview;

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(),ChangeLocation.class);
                        i.putExtra("fileId", fileItems.getFileid().toString().trim());
                        v.getContext().startActivity(i);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return finalfileItems.size();
    }
}
