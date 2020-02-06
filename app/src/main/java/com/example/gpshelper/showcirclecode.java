package com.example.gpshelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showcirclecode extends AppCompatActivity {

    TextView showid;
    Button done;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcirclecode);

        showid = findViewById(R.id.txtcircle_id);
        done = findViewById(R.id.button_done);

        //String currentid = firebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String i = dataSnapshot.child("circle_id").getValue(String.class);
                    //String str = String.valueOf(i);
                    showid.setText(i);
                }catch (NullPointerException e){
                    Toast.makeText(showcirclecode.this, "error:"+e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donetohomelistener();
            }
        });
    }

    private void donetohomelistener() {

        startActivity(new Intent(showcirclecode.this, home.class));

    }
}
