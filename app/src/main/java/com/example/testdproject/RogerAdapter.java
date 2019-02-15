package com.example.testdproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.testdproject.model.User;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RogerAdapter extends RecyclerView.Adapter<RogerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<User> userList;
    private int resource;

    public RogerAdapter(Context ctx,  int resource, ArrayList<User> list){
        inflater = LayoutInflater.from(ctx);
        this.userList = list;
        this.resource = resource;
    }

    @Override
    public RogerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.get().load(userList.get(position).getPicture().getThumbnail()).into(holder.iv);
        holder.name.setText(userList.get(position).getName().getFirst().toUpperCase());
        holder.country.setText(userList.get(position).getLocation().getState());
        holder.city.setText(userList.get(position).getLocation().getCity());
        holder.email.setText(userList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView country, name, city, email;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            country = (TextView) itemView.findViewById(R.id.country);
            name = (TextView) itemView.findViewById(R.id.name);
            city = (TextView) itemView.findViewById(R.id.city);
            email = (TextView) itemView.findViewById(R.id.email);
            iv = (ImageView) itemView.findViewById(R.id.iv);

        }
    }
}