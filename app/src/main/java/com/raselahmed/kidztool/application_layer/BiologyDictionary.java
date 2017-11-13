package com.raselahmed.kidztool.application_layer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.raselahmed.kidztool.R;
import com.raselahmed.kidztool.adapters.DictionaryAdapter;
import com.raselahmed.kidztool.data_access.DbHelper;
import com.raselahmed.kidztool.models.BioDict;

import java.util.ArrayList;

public class BiologyDictionary extends AppCompatActivity {
    ArrayList<BioDict> data;
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

        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        searchView = findViewById(R.id.SearchView);
        toggleButton = findViewById(R.id.toggleButton);
        dbHelper = DbHelper.getInstance(this);
        data = dbHelper.getDataOrderbyGN();
        DictionaryAdapter adapter = new DictionaryAdapter(data, false, this);
        recyclerView.setAdapter(adapter);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (toggleButton.isChecked()) {
                    toggle = true;
                    data = dbHelper.getDataOrderbySN();
                    DictionaryAdapter adapter = new DictionaryAdapter(data, true, BiologyDictionary.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    toggle = false;
                    data = dbHelper.getDataOrderbyGN();
                    DictionaryAdapter adapter = new DictionaryAdapter(data, false, BiologyDictionary.this);
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
                ArrayList<BioDict> filteredList = new ArrayList<>();
                String query;

                for (int i = 0; i < data.size(); i++) {
                    if (!toggle) {
                        query = data.get(i).getGeneralName().toLowerCase();
                    } else query = data.get(i).getScientificName().toLowerCase();
                    if (query.contains(newText)) {
                        BioDict Name = new BioDict(data.get(i).getGeneralName(), data.get(i).getScientificName());
                        filteredList.add(Name);
                    }
                }

                if (!toggle) {
                    adapter1 = new DictionaryAdapter(filteredList, false, BiologyDictionary.this);
                    recyclerView.setAdapter(adapter1);
                } else {
                    adapter1 = new DictionaryAdapter(filteredList, true, BiologyDictionary.this);
                    recyclerView.setAdapter(adapter1);
                }
                return true;
            }
        });
    }
}
