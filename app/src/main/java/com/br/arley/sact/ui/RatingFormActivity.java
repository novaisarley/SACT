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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.br.arley.sact.R;
import com.br.arley.sact.adapter.SectionRecyclerViewAdapter;
import com.br.arley.sact.model.Avaliation;
import com.br.arley.sact.model.Grade;
import com.br.arley.sact.model.GradeData;
import com.br.arley.sact.model.Question;
import com.br.arley.sact.model.RetrofitConfig;
import com.br.arley.sact.model.SactServer;
import com.br.arley.sact.model.Section;
import com.br.arley.sact.model.Criterion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RatingFormActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton btBack;
    Button btConclude;
    ProgressBar progressBar;
    SectionRecyclerViewAdapter sectionRecyclerViewAdapter;
    ArrayList<Section> sectionList;
    ArrayList<Criterion> criterionList;
    Retrofit retrofit;
    SactServer sactServer;
    Avaliation avaliation;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_form);

        avaliation = getIntent().getExtras().getParcelable("avaliation");

        setComponents();

        progressBar.setVisibility(View.VISIBLE);

        setComponentsListeners();

        btConclude.setClickable(false);

        if (isConnectedToInternet()){
            RetrofitConfig retrofitConfig = RetrofitConfig.getInstance();
            sactServer = retrofitConfig.getSactServer();

            String token = getEspecifcUserPref(getString(R.string.current_evaluator_token));

            getAllQuestions("Bearer " + token);
        }else {
            showNoInternetDialog();
        }

        sectionList = new ArrayList<>();
        criterionList = new ArrayList<>();

    }

    private void setComponentsListeners() {
        btConclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnectedToInternet()){
                    List<Section> sections = sectionRecyclerViewAdapter.getSectionList();
                    List<Grade> grades = new ArrayList<>();
                    EditText edtComments;
                    edtComments = findViewById(R.id.activity_rating_form_edt_comments);

                    GradeData gradeData = new GradeData(avaliation.getId());
                    for (Section s : sections) {
                        for (Criterion c : s.getCriterionList()) {
                            grades.add(new Grade(c.getId(), Float.parseFloat(c.getGrade())));
                        }
                    }
                    gradeData.setComments(edtComments.getText().toString().trim() + " ");
                    gradeData.setGrades(grades);

                    String token = getEspecifcUserPref(getString(R.string.current_evaluator_token));
                    createSetOfGrades("Bearer " + token, gradeData);

                    //Log.d("Grade", gradeData.toString());
                }else {
                    showNoInternetDialog();
                }


            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setComponents() {
        recyclerView = findViewById(R.id.activity_rating_form_rv);
        btBack = findViewById(R.id.activity_rating_form_bt_back);
        btConclude = findViewById(R.id.activity_rating_form_bt_conclude);
        progressBar = findViewById(R.id.progressBar_form);
    }


    void buildRecyclerView(List<Section> sections) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sectionRecyclerViewAdapter = new SectionRecyclerViewAdapter(sections, this);
        recyclerView.setAdapter(sectionRecyclerViewAdapter);
    }

    void createSetOfGrades(String token, GradeData gradeData) {
        Call<com.br.arley.sact.model.Response> call = sactServer.createSetOfGrades(token, gradeData);

        call.enqueue(new Callback<com.br.arley.sact.model.Response>() {
            @Override
            public void onResponse(Call<com.br.arley.sact.model.Response> call, Response<com.br.arley.sact.model.Response> response) {
                if (!response.isSuccessful()) {
                    //Log.d("Response", response.body().getMessage() + " " + response.body().getStatus());
                    Toast.makeText(RatingFormActivity.this, "Code: " + response.code() + response.message()
                            , Toast.LENGTH_SHORT).show();
                    return;

                }
                showAvaliationStatusDialog(R.drawable.sucesso, getResources().getString(R.string.SUCESSO_AVALIACAO));
            }

            @Override
            public void onFailure(Call<com.br.arley.sact.model.Response> call, Throwable t) {
                showAvaliationStatusDialog(R.drawable.erro, getResources().getString(R.string.ERRO_AVALIACAO));
                Toast.makeText(RatingFormActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR", t.toString());
            }
        });

    }

    void getAllQuestions(String token) {
        Call<List<Question>> call = sactServer.getQuestions(token);

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RatingFormActivity.this, "Code: " + response.code() + response.message()
                            , Toast.LENGTH_LONG).show();

                    return;
                }
                //Log.d("Questions", response.body().toString());
                organizeQuestions(response.body());
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(RatingFormActivity.this, t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    String getEspecifcUserPref(String pref) {
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(pref, "");

        return value;
    }

    void showAvaliationStatusDialog(int drawable, String statusMessage) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);

        View mView = getLayoutInflater().inflate(R.layout.dialog_status_avaliation, null);

        Button btnOk = (Button) mView.findViewById(R.id.dialog_status_avaliation_bt_ok);
        ImageView imgStatus = (ImageView) mView.findViewById(R.id.dialog_status_avaliation_iv_status);
        TextView tvMessage = (TextView) mView.findViewById(R.id.dialog_status_avaliation_tv_message);

        imgStatus.setImageDrawable(getResources().getDrawable(drawable));
        tvMessage.setText(statusMessage);

        mBuilder.setCancelable(false);
        mBuilder.setView(mView);

        final AlertDialog dialog = mBuilder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(RatingFormActivity.this, ProjectsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        });
        dialog.show();
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    void showNoInternetDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);

        View mView = getLayoutInflater().inflate(R.layout.dialog_no_wifi, null);

        Button btnOk = (Button) mView.findViewById(R.id.dialog_no_wifi_bt_ok);

        mBuilder.setView(mView);

        final AlertDialog dialog = mBuilder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    void organizeQuestions(List<Question> questions) {
        List<Question> questionList = questions;
        List<Section> sections = new ArrayList<>();

        for (Question q : questionList) {

            Section s = new Section(q.getSection());
            ArrayList<Criterion> criterionList = new ArrayList<>();

            for (int i = 0; i < questionList.size(); i++) {
                if (questionList.get(i).getSection().equals(q.getSection())) {
                    Criterion c = new Criterion(questionList.get(i).getId(), questionList.get(i).getCriterion()
                            , questionList.get(i).getMinGrade(), questionList.get(i).getMaxGrade());
                    criterionList.add(c);
                }
            }

            s.setCriterionList(criterionList);
            sections.add(s);
        }

        List<Section> listSemRepetidos = new ArrayList<>();

        for (Section section : sections) {
            if (!listSemRepetidos.contains(section)) {
                listSemRepetidos.add(section);
            }
        }

        buildRecyclerView(listSemRepetidos);
        progressBar.setVisibility(View.INVISIBLE);
        btConclude.setClickable(true);

        //Log.d("Sections", listSemRepetidos.toString());
    }
}