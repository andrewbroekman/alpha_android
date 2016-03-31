package com.example.stuart.alphaandroidinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity
{

    String type,type1,type2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        type = "";
        type1 = "super";
        type2 = "author";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        type = type1;

        Button button = (Button) findViewById(R.id.edit_type);
        if(button != null)
            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    type = type2;
                }
            });

        button = (Button) findViewById(R.id.add);
        if(button != null)
            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if(type.equals("super"))
                        startActivity(new Intent(MainMenu.this, AddPublicationAsSuper.class));
                    else
                        startActivity(new Intent(MainMenu.this, AddPublication.class));
                }
            });

        button = (Button) findViewById(R.id.view);
        if(button != null)
            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                        startActivity(new Intent(MainMenu.this, ViewPublications.class));
                }
            });

        button = (Button) findViewById(R.id.edit);
        if(button != null)
            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                     startActivity(new Intent(MainMenu.this, AddPublication.class));
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.settings:
                startActivity(new Intent(MainMenu.this, Settings.class));

                break;

            case R.id.signout:
                startActivity(new Intent(MainMenu.this, Login.class));

                break;

        }
        return false;
    }

}
