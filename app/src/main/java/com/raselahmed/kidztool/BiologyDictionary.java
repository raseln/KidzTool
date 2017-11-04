package com.raselahmed.kidztool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class BiologyDictionary extends AppCompatActivity {
    ArrayList<BioDict> Data;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    DbHelper dbHelper;
    SearchView searchView;
    ToggleButton toggleButton;
    public static boolean toggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biology_dictionary);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        searchView = (SearchView) findViewById(R.id.SearchView);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        dbHelper = new DbHelper(this);
        Data = dbHelper.getDataOrderbyGN();
        CustomAdapter adapter = new CustomAdapter(Data);
        recyclerView.setAdapter(adapter);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(toggleButton.isChecked()){
                    toggle = true;
                    Data = dbHelper.getDataOrderbySN();
                    CustomAdapter1 adapter = new CustomAdapter1(Data);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    toggle = false;
                    Data = dbHelper.getDataOrderbyGN();
                    CustomAdapter adapter = new CustomAdapter(Data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });


        searchView.setQueryHint("Search Here");
        searchView.setQueryRefinementEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                RecyclerView.Adapter adapter1;
                ArrayList<BioDict> filterdList = new ArrayList<>();
                String query="";

                for (int i=0; i<Data.size(); i++){
                    if (!toggle){
                        query = Data.get(i).getGeneralName().toLowerCase();
                    }
                    else query = Data.get(i).getScientificName().toLowerCase();
                    if (query.contains(newText)){
                        BioDict Name = new BioDict(Data.get(i).getGeneralName(), Data.get(i).getScientificName());
                        filterdList.add(Name);
                    }
                }

                if(!toggle){
                    adapter1 = new CustomAdapter(filterdList);
                    recyclerView.setAdapter(adapter1);
                }
                else{
                    adapter1 = new CustomAdapter1(filterdList);
                    recyclerView.setAdapter(adapter1);
                }
                return true;
            }
        });
    }
}
