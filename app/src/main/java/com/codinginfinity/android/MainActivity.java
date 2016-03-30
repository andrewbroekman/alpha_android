package com.codinginfinity.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewPapersFunc(View v){
        Intent intent = new Intent(this, ViewPublicationsActivity.class);
        startActivity(intent);
    }

    public void addPaperFunc(View v){
        Intent intent = new Intent(this, addPapersActivity.class);
        startActivity(intent);
    }

    public void editUserFunc(View v){
        Intent intent = new Intent(this, editUserActivity.class);
        startActivity(intent);
    }

}
