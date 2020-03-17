package br.com.uneb.tebd.artigos;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private static RetrofitConfig instance;
    private final Retrofit retrofit;

    static RetrofitConfig getInstance() {
        if (instance == null) instance = new RetrofitConfig();
        return instance;
    }

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://127.0.0.1:3000/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public RetrofitServices getRetrofitService() {
        return this.retrofit.create(RetrofitServices.class);
    }
}
