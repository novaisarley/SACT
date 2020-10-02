package com.br.arley.sact.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.br.arley.sact.R;
import com.br.arley.sact.adapter.ProjectRecyclerViewAdapter;
import com.br.arley.sact.model.Project;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProjectsActivity extends AppCompatActivity {

    RecyclerView recyclerViewProjects;
    ProjectRecyclerViewAdapter projectAdapter;
    ArrayList<Project> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        projectList = new ArrayList<>();

        String[] members = {"Arley Novais", "Klinsman Maia", "Debora Colhyer"};

        for (int i = 0; i < 10; i++){
            Project p = new Project("PalmPlay","3CI", "Informática", members);
            projectList.add(p);
        }

        recyclerViewProjects = findViewById(R.id.activity_projects_recycler_view);
        recyclerViewProjects.setLayoutManager(new LinearLayoutManager(this));
        projectAdapter = new ProjectRecyclerViewAdapter(projectList);

        recyclerViewProjects.setAdapter(projectAdapter);

    }


}