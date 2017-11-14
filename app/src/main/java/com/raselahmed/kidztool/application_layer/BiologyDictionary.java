package com.raselahmed.kidztool.application_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.raselahmed.kidztool.R;
import com.raselahmed.kidztool.adapters.DictionaryAdapter;
import com.raselahmed.kidztool.data_access.DbHelper;
import com.raselahmed.kidztool.models.BioDict;

import java.util.ArrayList;

public class BiologyDictionary extends AppCompatActivity {
    ArrayList<BioDict> sciNameList, genNameList;
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
        sciNameList = dbHelper.getDataOrderBySN();
        genNameList = dbHelper.getDataOrderByGN();
        DictionaryAdapter adapter = new DictionaryAdapter(sciNameList, false, this);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnQuizBio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Quiz.class));
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (toggleButton.isChecked()) {
                    toggle = true;
                    DictionaryAdapter adapter = new DictionaryAdapter(sciNameList, true, BiologyDictionary.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    toggle = false;
                    DictionaryAdapter adapter = new DictionaryAdapter(sciNameList, false, BiologyDictionary.this);
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

                for (int i = 0; i < sciNameList.size(); i++) {
                    if (!toggle) {
                        query = sciNameList.get(i).getGeneralName().toLowerCase();
                    } else query = genNameList.get(i).getScientificName().toLowerCase();
                    if (query.contains(newText)) {
                        BioDict Name = new BioDict(sciNameList.get(i).getGeneralName(), sciNameList.get(i).getScientificName());
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
