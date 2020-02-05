package com.example.gpshelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread td = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(MainActivity.this, login_screen.class));
                    finish();
                }
            }
        };td.start();
    }
}
