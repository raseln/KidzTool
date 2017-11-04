package com.raselahmed.kidztool;


import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<BioDict> Data = new ArrayList<>();
    Boolean check = false;

    public CustomAdapter(ArrayList<BioDict> data) {
        Data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, null);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.scientificText.setTypeface(null, Typeface.BOLD_ITALIC);
        myViewHolder.generalText.setTypeface(null, Typeface.BOLD);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check)
                {
                    myViewHolder.expandablelayout.animate()
                            .alpha(0.0f)
                            .setDuration(1000);


                    myViewHolder.expandablelayout.setVisibility(View.GONE);
                    check=true;
                    //  myViewHolder.schedule.setVisibility(View.VISIBLE);

                }
                else {
                    myViewHolder.expandablelayout.setVisibility(View.VISIBLE);
                    myViewHolder.expandablelayout.animate()
                            .alpha(1.0f)
                            .setDuration(1000);

                    check=false;

                }
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView generalText, scientificText;
        generalText = holder.generalText;
        scientificText = holder.scientificText;

        generalText.setText(Data.get(position).getGeneralName());
        scientificText.setText(Data.get(position).getScientificName());

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView generalText, scientificText;
        RelativeLayout expandablelayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.generalText = itemView.findViewById(R.id.generalText);
            this.scientificText = itemView.findViewById(R.id.scientificText);
            this.expandablelayout = itemView.findViewById(R.id.expandableLayout);
        }
    }
}
