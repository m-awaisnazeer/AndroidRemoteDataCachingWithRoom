package com.example.androidremotedatacachingwithroom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidremotedatacachingwithroom.R;
import com.example.androidremotedatacachingwithroom.RoomDatabase.Actor;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {

    private Context context;
    private List<Actor> actors;

    public ActorAdapter(Context context, List<Actor> actors) {
        this.context = context;
        this.actors = actors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Actor actor = actors.get(position);
        holder.id.setText(String.valueOf(actor.getId()));
        //holder.age.setText(actor.getAge());
        holder.name.setText(actor.getName());
        Glide.with(context).load(actor.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public void getAllActors(List<Actor> actorList){
        this.actors=actorList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, age;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            age = itemView.findViewById(R.id.age);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);


        }
    }
}
