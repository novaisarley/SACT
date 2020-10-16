package com.br.arley.sact.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private RetrofitConfig(){

    }

    private static RetrofitConfig config = null;

    public static RetrofitConfig getInstance(){
        if (config==null){
            config = new RetrofitConfig();
        }
        return config;
    }

    public Retrofit getRetrofitConfig(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .serializeNulls().create()))
                        .build();

        return retrofit;
    }

    public SactServer getSactServer(){
        Retrofit retrofit = getRetrofitConfig();
        return retrofit.create(SactServer.class);
    }

}
