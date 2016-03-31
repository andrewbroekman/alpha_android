package com.example.jocelyn.testing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.view.View.*;


public class Edit_Publication extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__publication);
        Button b;
        Button b1;
        final EditText el1;
        final EditText el2;
        final EditText el3;
        final EditText el4;
        final EditText el5;
        final EditText el6;
        final EditText el7;
        b = (Button)findViewById(R.id.button_SAVE);
        b1 = (Button)findViewById(R.id.button_CANCEL);
        el1 = (EditText)findViewById(R.id.Author_edit);
        el2 = (EditText)findViewById(R.id.Title_edit);
        el3 = (EditText)findViewById(R.id.Year_edit);
        el4 = (EditText)findViewById(R.id.Edition_edit);
        el5 = (EditText)findViewById(R.id.Type_edit);
        el6 = (EditText)findViewById(R.id.ISBN_edit);
        el7 = (EditText)findViewById(R.id.PubHouse_edit);
        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i1);
            }
        });
        b.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Article.class);
                String str1 = el1.getText().toString();
                String str2 = el2.getText().toString();
                String str3 = el3.getText().toString();
                String str4 = el4.getText().toString();
                String str5 = el5.getText().toString();
                String str6 = el6.getText().toString();
                String str7 = el7.getText().toString();

                i.putExtra("name1",str1);
                i.putExtra("name2",str2);
                i.putExtra("name3",str3);
                i.putExtra("name4",str4);
                i.putExtra("name5",str5);
                i.putExtra("name6",str6);
                i.putExtra("name7", str7);
                startActivity(i);

            }
        });
    }

    /*public void nextActivity(View v)
    {
        Intent nextActivity = new Intent();
        nextActivity.setClass(this, Article.class);
        startActivity(nextActivity);
    }*/
}
