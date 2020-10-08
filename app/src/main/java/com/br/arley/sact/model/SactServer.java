package com.br.arley.sact.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SactServer {

    @GET("posts/")
    Call<List<Project>> getProjects();

    @POST("evaluators/sessions/")
    Call<AuthData> authenticateEvaluator(@Body String cpf);

}
