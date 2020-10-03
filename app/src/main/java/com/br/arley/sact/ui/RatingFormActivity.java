package com.br.arley.sact.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.br.arley.sact.R;
import com.br.arley.sact.adapter.ProjectRecyclerViewAdapter;
import com.br.arley.sact.adapter.SectionRecyclerViewAdapter;
import com.br.arley.sact.model.Section;
import com.br.arley.sact.model.Criterion;

import java.util.ArrayList;

public class RatingFormActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton btBack;
    SectionRecyclerViewAdapter sectionRecyclerViewAdapter;
    ArrayList<Section> sectionList;
    ArrayList<Criterion> criterionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_form);

        recyclerView = findViewById(R.id.activity_rating_form_rv);
        btBack = findViewById(R.id.activity_rating_form_bt_back);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sectionList = new ArrayList<>();
        criterionList = new ArrayList<>();

        for (int j = 0; j < 5; j++){
            Criterion c = new Criterion("1.1. Oralidade", "6.0");
            criterionList.add(c);
        }

        for (int i = 0; i < 5; i++){
            Section s = new Section("Apresentacao", criterionList);
            sectionList.add(s);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sectionRecyclerViewAdapter = new SectionRecyclerViewAdapter(sectionList, this);

        recyclerView.setAdapter(sectionRecyclerViewAdapter);

    }
}