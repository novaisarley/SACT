package com.br.arley.sact.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.br.arley.sact.R;
import com.br.arley.sact.adapter.SectionRecyclerViewAdapter;
import com.br.arley.sact.model.Avaliation;
import com.br.arley.sact.model.Constants;
import com.br.arley.sact.model.Question;
import com.br.arley.sact.model.SactServer;
import com.br.arley.sact.model.Section;
import com.br.arley.sact.model.Criterion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RatingFormActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton btBack;
    Button btFinish;
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

        recyclerView = findViewById(R.id.activity_rating_form_rv);
        btBack = findViewById(R.id.activity_rating_form_bt_back);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sactServer = retrofit.create(SactServer.class);

        String token = getEspecifcUserPref(getString(R.string.current_evaluator_token));

        getAllQuestions("Bearer " + token);

        sectionList = new ArrayList<>();
        criterionList = new ArrayList<>();

        for (int j = 0; j < 2; j++){
            Criterion c = new Criterion("6.0", "1.1. Oralidade");
            criterionList.add(c);
        }

        for (int i = 0; i < 5; i++){
            Section s = new Section("Apresentacao", criterionList);
            sectionList.add(s);
        }

        //buildRecyclerView(sectionList);

    }

    void buildRecyclerView(List<Section> sections){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sectionRecyclerViewAdapter = new SectionRecyclerViewAdapter(sections, this);
        recyclerView.setAdapter(sectionRecyclerViewAdapter);
    }

    void getAllQuestions(String token){
        Call<List<Question>> call = sactServer.getQuestions(token);

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(RatingFormActivity.this, "Code: "+ response.code() + response.message()
                            , Toast.LENGTH_LONG).show();

                    return;
                }
                Log.d("Questions", response.body().toString());
                organizeQuestions(response.body());
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(RatingFormActivity.this, t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    String getEspecifcUserPref(String pref){
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(pref, "");

        return value;
    }

    void organizeQuestions(List<Question> questions){
        List<Question> questionList = questions;
        List<Section> sections = new ArrayList<>();
//      List<Criterion> criterions = new ArrayList<>();

            for (Question q : questionList){

                Section s = new Section(q.getSection());
                ArrayList<Criterion> criterionList = new ArrayList<>();

                for (int i = 0; i < questionList.size(); i++){
                    if (questionList.get(i).getSection().equals(q.getSection())){
                        Criterion c = new Criterion(questionList.get(i).getId(), questionList.get(i).getCriterion());
                        criterionList.add(c);
                        //questionList.remove(i);
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

        Log.d("Sections", listSemRepetidos.toString());

    }
}