package com.example.androidremotedatacachingwithroom.RoomDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ActorRepository {

    private ActorDatabase database;
    private LiveData<List<Actor>> getAllActors;
    public ActorRepository(Application application) {
        database = ActorDatabase.getINSTANCE(application);
        getAllActors = database.actorDao().getAllActors();
    }

    public void insert(List<Actor> actorList){
         new InsertAsyncTask(database).execute(actorList);
    }

    public LiveData<List<Actor>> getGetAllActors(){
        if (getAllActors ==null){

        }
        return getAllActors;
    }

    /*
                ##### INSERTION #########
     */
    static class InsertAsyncTask extends AsyncTask<List<Actor>,Void,Void>{
        private ActorDao actorDao;

        public InsertAsyncTask(ActorDatabase actorDatabase) {
            actorDao = actorDatabase.actorDao();
        }


        @Override
        protected Void doInBackground(List<Actor>... lists) {
            actorDao.insert(lists[0]);
            return null;
        }
    }
}
