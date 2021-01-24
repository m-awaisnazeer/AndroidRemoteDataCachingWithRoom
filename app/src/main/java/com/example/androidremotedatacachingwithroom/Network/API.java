package com.example.androidremotedatacachingwithroom.Network;


import com.example.androidremotedatacachingwithroom.RoomDatabase.Actor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("data.php")
    Call<List<Actor>> getAllActors();
}
