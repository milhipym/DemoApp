package com.db.demoapp.ui.apishowcase.service;

import com.db.demoapp.ui.apishowcase.model.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();
}
