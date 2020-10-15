package com.br.arley.sact.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SactServer {

    @GET("/avaliations/{evaluator_id}")
    Call<List<Avaliation>> getAvaliationsByEvaluatorId(
            @Path("evaluator_id") String evaluatorId,
            @Header("Authorization") String token);

    @GET("/questions/all")
    Call<List<Question>> getQuestions(@Header("Authorization") String token);

    @POST("/evaluators/sessions")
    Call<AuthData> authenticateEvaluatorByEmail(@Body Email email);

}
