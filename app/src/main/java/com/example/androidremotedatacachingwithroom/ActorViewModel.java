package com.example.androidremotedatacachingwithroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidremotedatacachingwithroom.RoomDatabase.Actor;
import com.example.androidremotedatacachingwithroom.RoomDatabase.ActorRepository;

import java.util.List;

public class ActorViewModel extends AndroidViewModel {

    private ActorRepository repository;
    private LiveData<List<Actor>> getAllActors;
    public ActorViewModel(@NonNull Application application) {
        super(application);
        repository = new ActorRepository(application);
        getAllActors = repository.getGetAllActors();
    }

    public void insert(List<Actor> actorList){
        repository.insert(actorList);
    }

    public LiveData<List<Actor>> getGetAllActors(){
        return getAllActors;
    }


}
