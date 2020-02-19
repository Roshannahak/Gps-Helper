package com.example.gpshelper;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

public class ProgressbarLoader {
    private Activity myactivity;
    private AlertDialog dialog;

    public ProgressbarLoader(Activity myactivity) {
        this.myactivity = myactivity;
    }

    public void showloader(){
        AlertDialog.Builder builder = new AlertDialog.Builder(myactivity);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_bar);
        dialog = builder.create();

        dialog.show();
    }

    public void dismissloader(){
        dialog.dismiss();
    }
}
