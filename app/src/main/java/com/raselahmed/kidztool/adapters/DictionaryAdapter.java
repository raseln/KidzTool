package com.raselahmed.kidztool.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.raselahmed.kidztool.R;
import com.raselahmed.kidztool.data_access.DbHelper;
import com.raselahmed.kidztool.models.BioDict;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.MyViewHolder> {
    private ArrayList<BioDict> data = new ArrayList<>();
    private boolean check = false;
    private boolean flag;
    private Context context;

    public DictionaryAdapter(ArrayList<BioDict> data, boolean flag, Context context) {
        this.data = data;
        this.flag =flag;
        this.context = context;
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        TextView generalText, scientificText;
        generalText = holder.generalText;
        scientificText = holder.scientificText;
        //holder.imageButton.setBackgroundColor(Color.TRANSPARENT);

        if (flag) {
            generalText.setText(data.get(position).getGeneralName());
            scientificText.setText(data.get(position).getScientificName());
        }else {
            generalText.setText(data.get(position).getScientificName());
            scientificText.setText(data.get(position).getGeneralName());
        }

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String generalName = data.get(position).getGeneralName();
                final String scientificName = data.get(position).getScientificName();
                final DbHelper dbHelper = DbHelper.getInstance(context);
                //alreadyInFavourite = dbHelper.getFavouriteOrderByGN();
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());
                alertBuilder.setMessage("Are you sure to add this word to favourite?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbHelper.addToFavorite(new BioDict(generalName, scientificName))) {
                                    Toast.makeText(view.getContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
                                }
                                else Toast.makeText(view.getContext(), "Error!!!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.setTitle("Adding Favorite!!!");
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView generalText, scientificText;
        RelativeLayout expandableLayout;
        ImageButton imageButton;

        MyViewHolder(View itemView) {
            super(itemView);
            this.generalText = itemView.findViewById(R.id.generalText);
            this.scientificText = itemView.findViewById(R.id.scientificText);
            this.expandableLayout = itemView.findViewById(R.id.expandableLayout);
            this.imageButton = itemView.findViewById(R.id.favoriteImg);
        }
    }
}
