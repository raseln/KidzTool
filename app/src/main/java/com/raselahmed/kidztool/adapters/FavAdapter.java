package com.raselahmed.kidztool.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raselahmed.kidztool.R;
import com.raselahmed.kidztool.data_access.DbHelper;
import com.raselahmed.kidztool.models.BioDict;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyViewHolder> {
    private ArrayList<BioDict> data = new ArrayList<>();
    private Boolean check = false;

    public FavAdapter(ArrayList<BioDict> data) {
        this.data = data;
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
                    myViewHolder.expandableLayout.animate()
                            .alpha(1.0f)
                            .setDuration(1000);

                    check=false;

                }
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //TextView generalText, scientificText;
        //ImageButton imageButton;
        //generalText = holder.generalText;
        //scientificText = holder.scientificText;
        //imageButton = holder.imageButton;

        holder.generalText.setText(data.get(position).getGeneralName());
        holder.scientificText.setText(data.get(position).getScientificName());
        //imageButton.setImageResource(R.mipmap.notfavorite);
        //imageButton.setBackgroundColor(Color.TRANSPARENT);

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String generalName = data.get(position).getGeneralName();
                final DbHelper dbHelper = DbHelper.getInstance(view.getContext());
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());
                alertBuilder.setMessage("Are you sure to delete this word from favourite?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                int deleteRows = dbHelper.deleteFavourite(generalName);
//                                if(deleteRows>0){
//                                    Toast.makeText(view.getContext(), "Deleted from Favorite", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(view.getContext(), Profile.class);
//                                    view.getContext().startActivity(intent);
//                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.setTitle("Deleting Favorite!!!");
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
