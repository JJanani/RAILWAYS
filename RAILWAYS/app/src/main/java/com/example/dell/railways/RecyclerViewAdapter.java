package com.example.dell.railways;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<GetDataAdapter> getDataAdapter;

    public RecyclerViewAdapter(List<GetDataAdapter> getDataAdapter, Context context){

        super();

        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);

        holder.DateTextView.setText(getDataAdapter1.getCDate());

        holder.IdTextView.setText(String.valueOf(getDataAdapter1.getId()));

        holder.TypeTextView.setText(getDataAdapter1.getCType());

        holder.DescriptionTextView.setText(getDataAdapter1.getCDescription());

        holder.StatusTextView.setText(getDataAdapter1.getCStatus());


    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView IdTextView;
        public TextView DateTextView;
        public TextView TypeTextView;
        public TextView DescriptionTextView;
        public TextView StatusTextView;


        public ViewHolder(View itemView) {

            super(itemView);

            IdTextView = (TextView) itemView.findViewById(R.id.textView2) ;
            DateTextView = (TextView) itemView.findViewById(R.id.textView4) ;
            TypeTextView = (TextView) itemView.findViewById(R.id.textView6) ;
            DescriptionTextView = (TextView) itemView.findViewById(R.id.textView8) ;
            StatusTextView = (TextView) itemView.findViewById(R.id.textView10) ;
        }
    }
}