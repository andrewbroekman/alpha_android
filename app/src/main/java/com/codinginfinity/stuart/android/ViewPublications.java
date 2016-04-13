package com.codinginfinity.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.codinginfinity.android.R;import java.lang.Override;

public class ViewPublications extends AppCompatActivity {

    //CreatePublications pubs = new CreatePublications();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_publications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
