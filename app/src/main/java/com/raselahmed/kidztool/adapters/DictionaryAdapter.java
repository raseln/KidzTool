package com.raselahmed.kidztool.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raselahmed.kidztool.R;
import com.raselahmed.kidztool.models.BioDict;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.MyViewHolder> {
    private ArrayList<BioDict> data = new ArrayList<>();
    private boolean check = false;
    private boolean flag;

    public DictionaryAdapter(ArrayList<BioDict> data, boolean flag) {
        this.data = data;
        this.flag =flag;
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
                    myViewHolder.expandableLayout.animate()
                            .alpha(0.0f)
                            .setDuration(1000);
                    myViewHolder.expandableLayout.setVisibility(View.GONE);
                    check=true;
                    //  myViewHolder.schedule.setVisibility(View.VISIBLE);
                }
                else {
                    myViewHolder.expandableLayout.setVisibility(View.VISIBLE);
                    myViewHolder.expandableLayout.animate().alpha(1.0f)
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

        if (flag) {
            generalText.setText(data.get(position).getGeneralName());
            scientificText.setText(data.get(position).getScientificName());
        }else {
            generalText.setText(data.get(position).getScientificName());
            scientificText.setText(data.get(position).getGeneralName());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView generalText, scientificText;
        RelativeLayout expandableLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            this.generalText = itemView.findViewById(R.id.generalText);
            this.scientificText = itemView.findViewById(R.id.scientificText);
            this.expandableLayout = itemView.findViewById(R.id.expandableLayout);
        }
    }
}
