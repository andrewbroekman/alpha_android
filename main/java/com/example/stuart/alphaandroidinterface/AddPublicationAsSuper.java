package com.example.stuart.alphaandroidinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddPublicationAsSuper extends AppCompatActivity {

    protected String file = "Publications";
    protected String filestream;
    EditText name, supervisor, type, state, target, authors;
    String name_s, supervisor_s, type_s, state_s, target_s, start_s, authors_s;
    String authorsList [];
    String priv = "";
    int dynam_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Bundle bundle = getIntent().getExtras();
        String type_user = "super";//bundle.getString("type");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publication_as_super);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
	    
	    Spinner spinner = (Spinner) findViewById(R.id.state_edit);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                state_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.submit);
        if (button != null)
            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v) {
                    name = (EditText) findViewById(R.id.name_edit);
                    name_s = name.getText().toString();

                    supervisor = (EditText) findViewById(R.id.super_edit);
                    supervisor_s = supervisor.getText().toString();

                    type = (EditText) findViewById(R.id.type_edit);
                    type_s = type.getText().toString();

                    state = (EditText) findViewById(R.id.state_edit);
                    state_s = state.getText().toString();

                    target = (EditText) findViewById(R.id.target_edit);
                    target_s = target.getText().toString();

                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date dateobj = new Date();

                    start_s = df.format(dateobj);

                    authors = (EditText) findViewById(R.id.authors);
                    authors_s = authors.getText().toString();

                    authorsList = authors_s.split("\n");

                    startActivity(new Intent(AddPublicationAsSuper.this, MainMenu.class));
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addpublications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.menu:
                startActivity(new Intent(AddPublicationAsSuper.this, MainMenu.class));

                break;

            case R.id.viewpublications:
                startActivity(new Intent(AddPublicationAsSuper.this, ViewPublications.class));

                break;

            case R.id.settings:
                startActivity(new Intent(AddPublicationAsSuper.this, Settings.class));

                break;

            case R.id.signout:
                startActivity(new Intent(AddPublicationAsSuper.this, Login.class));

                break;

        }
        return false;
    }

}