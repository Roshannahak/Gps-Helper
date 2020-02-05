package com.example.gpshelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup_screen extends AppCompatActivity {

    EditText fname, lname, eemail, ppassword;
    Button signupbtn;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        fname = findViewById(R.id.edittext_firstname);
        lname = findViewById(R.id.edittext_lastname);
        eemail = findViewById(R.id.edittext_signemail);
        ppassword = findViewById(R.id.edittext_signpassword);
        signupbtn = findViewById(R.id.signup_button);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        firebaseAuth = FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signuplistner();
            }
        });
    }

    private void signuplistner() {

        final String first_name = fname.getText().toString().trim();
        final String last_name = lname.getText().toString().trim();
        final String email = eemail.getText().toString().trim();
        final String password = ppassword.getText().toString().trim();
        final String userid = firebaseAuth.getCurrentUser().getUid();

        if (TextUtils.isEmpty(first_name) || TextUtils.isEmpty(last_name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(signup_screen.this, "fill the all field", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                users info = new users(userid, first_name, last_name, email, password);
                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(userid)
                                        .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(signup_screen.this, "submit..", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(signup_screen.this, login_screen.class));
                                    }
                                });

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(signup_screen.this, "database response error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
