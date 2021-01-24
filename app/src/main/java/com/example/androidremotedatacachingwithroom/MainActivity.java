package com.example.androidremotedatacachingwithroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.androidremotedatacachingwithroom.Adapter.ActorAdapter;
import com.example.androidremotedatacachingwithroom.Network.API;
import com.example.androidremotedatacachingwithroom.RoomDatabase.Actor;
import com.example.androidremotedatacachingwithroom.RoomDatabase.ActorRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActorViewModel actorViewModel;
    RecyclerView actorsListRecyclerView;
    ActorAdapter adapter;
    private List<Actor> actorList;
    private ActorRepository repository;
    public static final String URL_DATA = "http://www.codingwithjks.tech/data.php/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actorsListRecyclerView = findViewById(R.id.actorsList);
        actorsListRecyclerView.setHasFixedSize(true);
        actorsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        actorsListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        actorList = new ArrayList<>();
        repository = new ActorRepository(getApplication());
        adapter = new  ActorAdapter(this,actorList);


        actorViewModel = new ViewModelProvider(this).get(ActorViewModel.class);
        actorViewModel.getGetAllActors().observe(this, actorList -> {
            adapter.getAllActors(actorList);
            actorsListRecyclerView.setAdapter(adapter);
        });


        networkRequest();
    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_DATA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Actor>> call = api.getAllActors();
        call.enqueue(new Callback<List<Actor>>() {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                if (response.isSuccessful()){
                    repository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {

                Log.d("Main", "onFailure: "+t.getMessage());
                Toast.makeText(MainActivity.this, "Something Went Wrong"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}