package com.example.gpshelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_screen extends AppCompatActivity {

    EditText logemail, logpass;
    Button loginbtn;
    TextView txtsign;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        logemail = findViewById(R.id.edittext_email);
        logpass = findViewById(R.id.edittext_password);
        loginbtn = findViewById(R.id.login_button);
        txtsign = findViewById(R.id.logtosign);

        firebaseAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginlistner();
            }
        });

        txtsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtsignuplistener();
            }
        });

    }

    private void txtsignuplistener() {
        startActivity(new Intent(login_screen.this, signup_screen.class));
    }

    private void loginlistner() {

        String email = logemail.getText().toString().trim();
        String password = logpass.getText().toString().trim();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(login_screen.this, "fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login_screen.this, "success..", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login_screen.this, home.class));
                            } else {
                                Toast.makeText(login_screen.this, "failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
