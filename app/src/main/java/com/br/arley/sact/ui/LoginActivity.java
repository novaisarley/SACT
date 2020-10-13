package com.br.arley.sact.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.br.arley.sact.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.br.arley.sact.model.AuthData;
import com.br.arley.sact.model.SactServer;
import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button btLogin;
    Retrofit retrofitSact;
    EditText edtEmail;
    ProgressBar pb;
    SactServer sactServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = findViewById(R.id.activty_login_bt_login);
        edtEmail = findViewById(R.id.activty_login_edt_email);
        pb = findViewById(R.id.progressBar_login);

        pb.setVisibility(View.INVISIBLE);

        retrofitSact = new Retrofit.Builder()
                .baseUrl("http://ec2-18-219-59-120.us-east-2.compute.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        sactServer = retrofitSact.create(SactServer.class);



        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String email = edtEmail.getText().toString();

                if (email.contains("@")) {
                    btLogin.setClickable(false);
                    pb.setVisibility(View.VISIBLE);
                    Log.d("Autentica", email);
                    authenticateUser(email);

                } else {
                    Toast.makeText(LoginActivity.this, "Email Inválido", Toast.LENGTH_SHORT).show();
                }*/
                authenticateUser("arley@gmail.com");

            }
        });

    }

    private void authenticateUser(String email) {

        Call<AuthData> call = sactServer.authenticateEvaluatorByEmail(email);


        call.enqueue(new Callback<AuthData>() {
            @Override
            public void onResponse(Call<AuthData> call, Response<AuthData> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "ERROR_CODE: " + response.code(), Toast.LENGTH_SHORT).show();
                    btLogin.setClickable(true);
                    pb.setVisibility(View.INVISIBLE);
                    return;
                }
                if (response.body().getEvaluator().getEmail().equals(email)) {
                    startActivity(new Intent(LoginActivity.this, ProjectsActivity.class));
                    pb.setVisibility(View.INVISIBLE);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Avaliador não encontrado", Toast.LENGTH_SHORT).show();
                    btLogin.setClickable(true);
                    pb.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<AuthData> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                btLogin.setClickable(true);
                pb.setVisibility(View.INVISIBLE);
            }
        });


    }


}