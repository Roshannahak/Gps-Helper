package com.example.gpshelper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

class membersadepter extends RecyclerView.Adapter<membersadepterViewHolder> {

    ArrayList<String> namelist;
    Context context;

    public membersadepter(ArrayList<String> namelist, Context context) {
        this.namelist = namelist;
        this.context = context;
    }

    @NonNull
    @Override
    public membersadepterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrecyclerview, parent,false);
        membersadepterViewHolder membersadepterView = new membersadepterViewHolder(v,context,namelist);
        return membersadepterView;
    }

    @Override
    public void onBindViewHolder(@NonNull membersadepterViewHolder holder, int position) {
        //users userobj = namelist.get(position);
        //holder.nametxt.setText(userobj.getFirstname());
        String strobj = namelist.get(position);
        holder.nametxt.setText(strobj);
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }
}


class membersadepterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    TextView nametxt;
    Context c;
    ArrayList<String> namearraylist;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    public membersadepterViewHolder(@NonNull View itemView, Context c, ArrayList<String> namearraylist) {
        super(itemView);
        this.c = c;
        this.namearraylist = namearraylist;

        itemView.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        nametxt = itemView.findViewById(R.id.textview_name);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(c, "user have clicked "+v, Toast.LENGTH_SHORT).show();
    }
}
