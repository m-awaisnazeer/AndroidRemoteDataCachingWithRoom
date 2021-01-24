package com.example.androidremotedatacachingwithroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.androidremotedatacachingwithroom.RoomDatabase.Actor;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActorViewModel actorViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actorViewModel = new ViewModelProvider(this).get(ActorViewModel.class);
        actorViewModel.getGetAllActors().observe(this, actorList -> {
            Toast.makeText(this, "Working Fine", Toast.LENGTH_SHORT).show();
        });
    }
}