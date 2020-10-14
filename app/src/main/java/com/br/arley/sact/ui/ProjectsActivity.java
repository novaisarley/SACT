package com.br.arley.sact.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.br.arley.sact.R;
import com.br.arley.sact.adapter.ProjectRecyclerViewAdapter;
import com.br.arley.sact.db.AppDatabase;
import com.br.arley.sact.model.AuthData;
import com.br.arley.sact.model.Avaliation;
import com.br.arley.sact.model.Constants;
import com.br.arley.sact.model.Project;
import com.br.arley.sact.model.SactServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectsActivity extends AppCompatActivity {

    RecyclerView recyclerViewProjects;
    ProjectRecyclerViewAdapter projectAdapter;
    List<Project> projectList;
    Retrofit retrofit;
    SactServer sactServer;
    AppDatabase database;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        setComponentsId();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sactServer = retrofit.create(SactServer.class);

        //database = AppDatabase.getInstance(ProjectsActivity.this);

        String id = getEspecifcUserPref(getString(R.string.current_evaluator_id));
        String token = getEspecifcUserPref(getString(R.string.current_evaluator_token));
        getAvaluations(id, token);

        projectList = new ArrayList<>();

        /*String members = "Arley Novais, Klinsman Maia, Debora Colhyer";

        for (int i = 0; i < 10; i++){
            Project p = new Project("PalmPlay","3CI", "Informática", members);
            projectList.add(p);
        }

        buildRecyclerView(projectList);*/


    }

    void getAvaluations(String evaluatorId, String token){

        Call<List<Avaliation>> call = sactServer.getAvaliationsByEvaluatorId(evaluatorId, "Bearer "+token);

        call.enqueue(new Callback<List<Avaliation>>() {
            @Override
            public void onResponse(Call<List<Avaliation>> call, Response<List<Avaliation>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ProjectsActivity.this, "Code: "+ response.code() + response.message()
                    , Toast.LENGTH_LONG).show();

                    return;
                }

                List<Avaliation> avaliationsList = response.body();
                List<Project> projects = new ArrayList<>();

                if (!avaliationsList.isEmpty()){
                    for (Avaliation a : avaliationsList){
                        projects.add(a.getProject());
                    }
                    buildRecyclerView(projects);
                }

                Toast.makeText(ProjectsActivity.this, "Size: "+ avaliationsList.size()
                        , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Avaliation>> call, Throwable t) {
                Toast.makeText(ProjectsActivity.this, "ERROR: " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    void setComponentsId(){
        recyclerViewProjects = findViewById(R.id.activity_projects_recycler_view);
    }

    void buildRecyclerView(List<Project> projectList){
        recyclerViewProjects.setLayoutManager(new LinearLayoutManager(this));
        projectAdapter = new ProjectRecyclerViewAdapter(projectList);

        projectAdapter.setOnItemClickListener(new ProjectRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onProjectClick(int position) {
                startActivity(new Intent(ProjectsActivity.this, ProjectInfoActivity.class));
            }
        });

        recyclerViewProjects.setAdapter(projectAdapter);
    }

    void setLoginStatus(boolean b) {
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.pref_login), b);
        editor.apply();
    }
    void setCurrentEvaluatorPref(String token, String userId){
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(getString(R.string.current_evaluator_token), token);
        editor.putString(getString(R.string.current_evaluator_id), userId);
        editor.apply();
    }

    String getEspecifcUserPref(String pref){
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(pref, "");

        return value;
    }


}