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
    FirebaseAuth firebaseAuth;
    FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcirclecode);

        showid = findViewById(R.id.txtcircle_id);
        done = findViewById(R.id.button_done);

        firebaseAuth = FirebaseAuth.getInstance();
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        String current = fuser.getUid();

        //if (current != null) {

            databaseReference = FirebaseDatabase.getInstance().getReference("users").child(current);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String currentcode = dataSnapshot.child("circle_id").getValue(String.class);
                        showid.setText(currentcode);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        //}
        //else{
            //Toast.makeText(showcirclecode.this, "user id null", Toast.LENGTH_SHORT).show();
        //}

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
