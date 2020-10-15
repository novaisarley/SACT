package com.br.arley.sact.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.br.arley.sact.R;
import com.br.arley.sact.model.Avaliation;
import com.br.arley.sact.model.Project;

public class ProjectInfoActivity extends AppCompatActivity {

    ImageButton btBack;
    Button btStartEvaluation;
    TextView tvName, tvMembers, tvArea, tvObservations;
    Avaliation avaliation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);

        avaliation = getIntent().getExtras().getParcelable("avaliation");


        setComponentsId();
        setComponentsListeners();

        Project project = avaliation.getProject();
        String[] list = project.getMembers().split(",");
        String members = "";
        for (int i = 0; i < list.length; i++) {
            if (i == 0) members = list[i];
            else members += "\n" + list[i].trim();
            tvMembers.setText(members);
        }

        tvName.setText(project.getName());
        tvArea.setText(project.getOccupationArea());
        tvObservations.setText(project.getObservations());


    }

    void setComponentsId(){
        btBack = findViewById(R.id.activity_project_info_bt_back);
        btStartEvaluation = findViewById(R.id.activity_project_info_bt_next);
        tvName = findViewById(R.id.activity_aproject_info_tv_name);
        tvMembers = findViewById(R.id.activity_project_info_tv_members);
        tvArea = findViewById(R.id.activity_project_info_tv_occupation_area);
        tvObservations = findViewById(R.id.activity_project_info_tv_observations);
    }

    void setComponentsListeners(){
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btStartEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectInfoActivity.this, RatingFormActivity.class);
                intent.putExtra("avaliation", avaliation);
                startActivity(intent);
            }
        });
    }

}
