package com.br.arley.sact.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.br.arley.sact.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.br.arley.sact.model.AuthData;
import com.br.arley.sact.model.CpfVerifier;
import com.br.arley.sact.model.SactServer;

public class LoginActivity extends AppCompatActivity {

    Button btLogin;
    Retrofit retrofit;
    TextView tvCpf;
    SactServer sactServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = findViewById(R.id.activty_login_bt_login);
        tvCpf = findViewById(R.id.activty_login_edt_cpf);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://virtserver.swaggerhub.com/lorenzowind/SACT/1.0.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sactServer = retrofit.create(SactServer.class);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formatada = tvCpf.getText().toString().replace(".", "");
                formatada = formatada.replace("-", "");

                if (/*CpfVerifier.isCPF(formatada)*/true){
                    authenticateUser(formatada);

                }else Toast.makeText(LoginActivity.this, "CPF Inválido", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void authenticateUser(String cpf){
        Call<AuthData> call = sactServer.authenticateEvaluator(cpf);


        call.enqueue(new Callback<AuthData>() {
            @Override
            public void onResponse(Call<AuthData> call, Response<AuthData> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "ERROR_CODE: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body().getEvaluator().getCpf().replace(".", "").replace("-", "").equals(cpf)){
                    startActivity(new Intent(LoginActivity.this, ProjectsActivity.class));
                    finish();
                }else Toast.makeText(LoginActivity.this, "Avaliador não encontrado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<AuthData> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Falha de comunicação com o servidor", Toast.LENGTH_SHORT).show();
            }
        });


    }
}