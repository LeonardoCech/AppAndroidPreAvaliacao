package com.cech.loginapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MockableService {

    @GET("JSON")
    Call<List<DataIO>> buscar();
}
