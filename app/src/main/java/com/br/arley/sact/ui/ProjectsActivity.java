package com.br.arley.sact.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
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
import java.util.Collections;
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
    ProgressBar progressBar;
    AppDatabase database;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        setComponentsId();

        progressBar = findViewById(R.id.progressBar_projects);

        //database = AppDatabase.getInstance(ProjectsActivity.this);



        projectList = new ArrayList<>();


    }

    void getAvaluations(String evaluatorId, String token){

        Call<List<Avaliation>> call = sactServer.getAvaliationsByEvaluatorId(evaluatorId, "Bearer "+token);

        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<Avaliation>>() {
            @Override
            public void onResponse(Call<List<Avaliation>> call, Response<List<Avaliation>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ProjectsActivity.this, "Code: "+ response.code() + response.message()
                    , Toast.LENGTH_LONG).show();

                    return;
                }

                List<Avaliation> avaliationsList = response.body();

                if (!avaliationsList.isEmpty()){
                    int cont = 0;
                    for (Avaliation a : avaliationsList){
                        if (a.getStatus().equals(Constants.RATED)) cont++;
                    }
                    if (cont == avaliationsList.size()) showAllEvaluationDoneDialog();
                    buildRecyclerView(avaliationsList);
                }
                else {
                    Toast.makeText(ProjectsActivity.this, "Nenhum projeto atribuído ao avaliador"
                            , Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
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

    void buildRecyclerView(List<Avaliation> avaliationList){

        Collections.sort(avaliationList);

        recyclerViewProjects.setLayoutManager(new LinearLayoutManager(this));
        projectAdapter = new ProjectRecyclerViewAdapter(avaliationList, ProjectsActivity.this);

        projectAdapter.setOnItemClickListener(new ProjectRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onProjectClick(int position) {

                if (avaliationList.get(position).getStatus().equals("to_evaluate")){
                    Intent intent = new Intent(ProjectsActivity.this, ProjectInfoActivity.class);
                    intent.putExtra("avaliation", avaliationList.get(position));
                    startActivity(intent);
                }else if (avaliationList.get(position).getStatus().equals("rated")){
                    Toast.makeText(ProjectsActivity.this, "Projeto já avaliado!"
                            , Toast.LENGTH_LONG).show();
                }

            }
        });


        recyclerViewProjects.setAdapter(projectAdapter);

        progressBar.setVisibility(View.INVISIBLE);
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

    void showAllEvaluationDoneDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_all_evaluation_done, null);
        Button btnOk = (Button) mView.findViewById(R.id.dialog_all_evaluation_done_bt_ok);
        mBuilder.setCancelable(false);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }

        });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sactServer = retrofit.create(SactServer.class);

        String id = getEspecifcUserPref(getString(R.string.current_evaluator_id));
        String token = getEspecifcUserPref(getString(R.string.current_evaluator_token));
        getAvaluations(id, token);
    }
}