package com.cech.loginapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    private OkHttpClient buildHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient result = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        return result;
    }

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder().baseUrl("http://demo5093041.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(this.buildHttpClient())
                .build();
    }

    public MockableService getMockableService() {
        return this.retrofit.create(MockableService.class);
    }
}
