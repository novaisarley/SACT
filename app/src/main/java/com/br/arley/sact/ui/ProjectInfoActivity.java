package com.br.arley.sact.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.br.arley.sact.R;

public class ProjectInfoActivity extends AppCompatActivity {

    ImageButton btBack, btStartEvaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);

        btBack = findViewById(R.id.activity_project_info_bt_back);
        btStartEvaluation = findViewById(R.id.activity_project_info_bt_next);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btStartEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProjectInfoActivity.this, RatingFormActivity.class));
            }
        });
    }
}
