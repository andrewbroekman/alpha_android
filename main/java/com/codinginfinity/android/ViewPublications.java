package com.example.stuart.alphaandroidinterface;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ViewPublications extends AppCompatActivity {

    protected String file = "publications.txt";
    protected String filestream;
    BufferedReader br;
    TextView textView = (TextView) findViewById(R.id.display);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_publications);


        Button button = (Button) findViewById(R.id.button);
        if(button != null)
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    startActivity(new Intent(ViewPublications.this, MainMenu.class));
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.viewpublications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.menu:
                startActivity(new Intent(ViewPublications.this, MainMenu.class));

                break;

            case R.id.settings:
                startActivity(new Intent(ViewPublications.this, Settings.class));

                break;

            case R.id.signout:
                startActivity(new Intent(ViewPublications.this, Login.class));

                break;

        }
        return false;
    }


}
