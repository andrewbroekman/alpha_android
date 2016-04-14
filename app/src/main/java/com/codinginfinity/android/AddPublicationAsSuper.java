package com.codinginfinity.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codinginfinity.android.R;

import org.json.JSONException;

import java.lang.CharSequence;import java.lang.Override;import java.lang.String;import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.codinginfinity.android.R.array.state_array;

public class AddPublicationAsSuper extends AppCompatActivity {

    protected CreatePublications newPublication;
    protected String file = "Publications";
    Spinner state;
    EditText name, owner, type, target, authors, url;
    String name_s, owner_s, type_s, state_s, target_s, authors_s, url_s, adder;
    String authorsList [];
    String envisioned_date, start_date;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int result = this.getResources().getConfiguration().orientation;
        if (result == 1)
        {
            //set content view to portrait
            setContentView(R.layout.activity_add_publication_as_super);
        }
        else
        {
            //set content view to landscape}
            setContentView(R.layout.activity_add_publication_as_super_landscape);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adder = getIntent().getExtras().getString("User");

        Spinner spinner = (Spinner) findViewById(R.id.state_edit);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                state_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.add_publication_super);

        name = (EditText) findViewById(R.id.name_edit);
        name.setWidth(name.getWidth() - 150);

        owner = (EditText) findViewById(R.id.super_edit);
        owner.setWidth(owner.getWidth()-50);

        type = (EditText) findViewById(R.id.type_edit);
        type.setWidth(type.getWidth()-50);

        state = (Spinner) findViewById(R.id.state_edit);

        target = (EditText) findViewById(R.id.target_edit);
        target.setWidth(target.getWidth()-50);

        url = (EditText) findViewById(R.id.url_edit);
        url.setWidth(url.getWidth()-50);

        authors = (EditText) findViewById(R.id.authors);
        authors.setWidth(authors.getWidth()-50);

        Button button = (Button) findViewById(R.id.submit);
        if (button != null)
            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v) {
                    name_s = name.getText().toString();

                    owner_s = owner.getText().toString();

                    type_s = type.getText().toString();

                    state_s = state.getSelectedItem().toString();

                    envisioned_date = target.getText().toString();
                    Date newdate = new Date();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    //String envisioned_date = sdf.format(target_s);

                    start_date = sdf.format(newdate);

                    authors_s = authors.getText().toString();

                    authorsList = authors_s.split("\n");

                    url_s = url.getText().toString();

                    arrayList = new ArrayList<>(Arrays.asList(authorsList));

                    for(int i = 0 ; i < arrayList.size() ; i++)
                        if(arrayList.get(i).equals(""))
                            arrayList.remove(i);

                    try
                    {
                        newPublication = new CreatePublications(
                                adder,
                                name_s,
                                owner_s,
                                type_s,
                                state_s,
                                url_s,
                                envisioned_date,
                                start_date,
                                arrayList);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(), "Publication Successfully Created!", Toast.LENGTH_SHORT).show();
                    finish();
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
                startActivity(new Intent(AddPublicationAsSuper.this, MainActivity.class));

                break;

            case R.id.viewpublications:
                startActivity(new Intent(AddPublicationAsSuper.this, ViewPublicationsActivity.class));

                break;

            case R.id.settings:
                startActivity(new Intent(AddPublicationAsSuper.this, Settings.class));

                break;

            case R.id.signout:
                startActivity(new Intent(AddPublicationAsSuper.this, LoginActivity.class));

                break;
        }
        return false;
    }

}