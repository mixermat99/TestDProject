package com.example.testdproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testdproject.model.Data;
import com.example.testdproject.model.User;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class RogerRealmCustomAdapter extends RealmRecyclerViewAdapter<User, RogerRealmCustomAdapter.MyViewHolder> {

    Realm realm;
    private boolean inDeletionMode = false;
    private Set<Integer> countersToDelete = new HashSet<>();


    public RogerRealmCustomAdapter(@Nullable OrderedRealmCollection<User> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, viewGroup, false);
        return new MyViewHolder(viewGroup.getContext(), view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        User user = getItem(i);
        Picasso.get().load(user.getPicture().getThumbnail()).into(myViewHolder.iv);
        myViewHolder.name.setText(user.getName().getFirst().toUpperCase());
        myViewHolder.country.setText(user.getLocation().getState());
        myViewHolder.city.setText(user.getLocation().getCity());
        myViewHolder.email.setText(user.getEmail());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView country, name, city, email;
        ImageView iv;

        public MyViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            country = (TextView) itemView.findViewById(R.id.country);
            name = (TextView) itemView.findViewById(R.id.name);
            city = (TextView) itemView.findViewById(R.id.city);
            email = (TextView) itemView.findViewById(R.id.email);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
