package com.br.arley.sact.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.br.arley.sact.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.br.arley.sact.model.AuthData;
import com.br.arley.sact.model.Constants;
import com.br.arley.sact.model.Email;
import com.br.arley.sact.model.SactServer;

public class LoginActivity extends AppCompatActivity {

    Button btLogin;
    Retrofit retrofitSact;
    EditText edtEmail;
    ProgressBar pb;
    SactServer sactServer;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (!isConnectedToInternet()){
            showNoInternetDialog();
        }

        btLogin = findViewById(R.id.activty_login_bt_login);
        edtEmail = findViewById(R.id.activty_login_edt_email);
        pb = findViewById(R.id.progressBar_login);

        pb.setVisibility(View.INVISIBLE);

        retrofitSact = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sactServer = retrofitSact.create(SactServer.class);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email email = new Email();

                String emailS = edtEmail.getText().toString();

                if (!emailS.trim().isEmpty()) {
                    if (emailS.contains("@")) {
                        if (isConnectedToInternet()){
                            btLogin.setClickable(false);
                            pb.setVisibility(View.VISIBLE);
                            email.setEmail(emailS);
                            authenticateUser(email);
                        }else {
                            showNoInternetDialog();
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "Email Inválido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Preencha o campo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void authenticateUser(Email email) {

        Call<AuthData> call = sactServer.authenticateEvaluatorByEmail(email);

        call.enqueue(new Callback<AuthData>() {
            @Override
            public void onResponse(Call<AuthData> call, Response<AuthData> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    btLogin.setClickable(true);
                    pb.setVisibility(View.INVISIBLE);
                    return;
                }

                AuthData authData = response.body();
                String token = authData.getToken();
                String id = authData.getEvaluator().getId();

                //GUARDAR AVALIADOR
                //setLoginStatus(true);
                //setCurrentUserPref(token, id);
                pb.setVisibility(View.INVISIBLE);
                startActivity(new Intent(LoginActivity.this, ProjectsActivity.class));

                finish();

            }

            @Override
            public void onFailure(Call<AuthData> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                btLogin.setClickable(true);
                pb.setVisibility(View.INVISIBLE);
            }
        });


    }

    void setLoginStatus(boolean b) {
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.pref_login), b);
        editor.apply();
    }

    void setCurrentUserPref(String token, String userId) {
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(getString(R.string.current_evaluator_token), token);
        editor.putString(getString(R.string.current_evaluator_id), userId);
        editor.apply();
    }

    void showBlockLoginDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_block_login, null);
        Button btnOk = (Button) mView.findViewById(R.id.dialog_block_login_bt_ok);
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


}