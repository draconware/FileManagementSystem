package com.example.android.filemanagementsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class searchAdapter extends RecyclerView.Adapter<searchAdapter.ViewHolder>{

    private List<listItem> listItems;
    private Context context;

    public searchAdapter(List<listItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        listItem listItem = listItems.get(position);

        holder.textViewfileid.setText(listItem.getFileid());
        holder.textViewfilename.setText(listItem.getFilename());
        holder.textViewfiledepartment.setText(listItem.getFiledepartment());
        holder.textViewfileholder.setText(listItem.getFileholder());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewfileid,textViewfilename,textViewfiledepartment,textViewfileholder;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewfileid = (TextView)itemView.findViewById(R.id.textViewsearchdetailsfileid);
            textViewfilename = (TextView)itemView.findViewById(R.id.textViewsearchdetailsfilename);
            textViewfiledepartment = (TextView)itemView.findViewById(R.id.textViewsearchdetailsfiledepartment);
            textViewfileholder = (TextView)itemView.findViewById(R.id.textViewsearchdetailsfileholder);
        }
    }
}
