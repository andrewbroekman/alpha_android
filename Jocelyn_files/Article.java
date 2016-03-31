package com.example.jocelyn.testing;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Article extends ActionBarActivity {

    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Button b;
        t1 = (TextView)findViewById(R.id.Author_value);
        t2 = (TextView)findViewById(R.id.Title_value);
        t3 = (TextView)findViewById(R.id.Year_value);
        t4 = (TextView)findViewById(R.id.Edition_value);
        t5 = (TextView)findViewById(R.id.Type_value);
        t6 = (TextView)findViewById(R.id.ISBN_value);
        t7 = (TextView)findViewById(R.id.PubHouse_value);

        Intent ii = getIntent();
        Bundle ib1 = ii.getExtras();
        String str1 = (String) ib1.get("name1");
        String str2 = (String) ib1.get("name2");
        String str3 = (String) ib1.get("name3");
        String str4 = (String) ib1.get("name4");
        String str5 = (String) ib1.get("name5");
        String str6 = (String) ib1.get("name6");
        String str7 = (String) ib1.get("name7");
        t1.setText(str1);
        t2.setText(str2);
        t3.setText(str3);
        t4.setText(str4);
        t5.setText(str5);
        t6.setText(str6);
        t7.setText(str7);

        b = (Button)findViewById(R.id.button_OK);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    /*public void nextActivity(View v)
    {
        Intent nextActivity = new Intent();
        nextActivity.setClass(this, MainActivity.class);
        startActivity(nextActivity);
    }*/
}
