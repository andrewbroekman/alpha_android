package com.codinginfinity.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.CharSequence;import java.lang.Override;import java.lang.String;import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.codinginfinity.android.R.array.state_array;

public class AddPublication extends AppCompatActivity {

    protected CreatePublications newPublication;
    protected String file = "Publications";
    EditText name, owner, type, target, url;
    Spinner state;
    String name_s, owner_s, type_s, state_s, url_s, adder;
    String envisioned_date, start_date;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        int result = this.getResources().getConfiguration().orientation;
        if (result == 1)
        {
            //set content view to portrait
            setContentView(R.layout.activity_add_publication);
        }
        else
        {
            //set content view to landscape}
            setContentView(R.layout.activity_add_publication_landscape);
        }

        adder = getIntent().getExtras().getString("User");

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

                    owner = (EditText) findViewById(R.id.super_edit);
                    owner_s = owner.getText().toString();

                    type = (EditText) findViewById(R.id.type_edit);
                    type_s = type.getText().toString();

                    state = (Spinner) findViewById(R.id.state_edit);
                    state_s = state.getSelectedItem().toString();

                    target = (EditText) findViewById(R.id.target_edit);
                    envisioned_date = target.getText().toString();
                    Date newdate = new Date();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    //String envisioned_date = sdf.format(target_s);

                    start_date = sdf.format(newdate);

                    url = (EditText) findViewById(R.id.url_edit);
                    url_s = url.getText().toString();

                    try {
                        newPublication = new CreatePublications(
                                adder,
                                name_s,
                                owner_s,
                                type_s,
                                state_s,
                                url_s,
                                envisioned_date,
                                start_date
                        );
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
                startActivity(new Intent(AddPublication.this, MainActivity.class));

                break;

            case R.id.viewpublications:
                startActivity(new Intent(AddPublication.this, ViewPublicationsActivity.class));

                break;

            case R.id.settings:
                startActivity(new Intent(AddPublication.this, Settings.class));

                break;

            case R.id.signout:
                startActivity(new Intent(AddPublication.this, LoginActivity.class));

                break;

        }
        return false;
    }

    public boolean testForPriviledge(String name, String user) throws JSONException {
        File file = new File(path + "/puplications.json");
        String data = Load(file);
        JSONArray jsonArray = new JSONArray(data);

        for (int i =0; i<jsonArray.length();i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each person from array
            JSONArray jsonArray2 = new JSONArray();
            if(user.compareTo(jsonArray2.getString(i)) == 0)
            {
                jsonArray2 = jsonObject.getJSONArray("author");
                for (int j = 0; j < jsonArray2.length(); i++) {
                    jsonObject = jsonArray2.getJSONObject(j);
                    if (name.compareTo(jsonObject.getString("name")) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void Save(File file, String dataString)
    {
        String [] data = String.valueOf(dataString).split(System.getProperty("line.separator"));
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    fos.write(data[i].getBytes());
                    if (i < data.length-1)
                    {
                        fos.write("\n".getBytes());
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }

    public static String Load(File file)
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl=0;
        try
        {
            while ((test=br.readLine()) != null)
            {
                anzahl++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        try
        {
            fis.getChannel().position(0);
        }
        catch (IOException e) {e.printStackTrace();}

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try
        {
            while((line=br.readLine())!=null)
            {
                array[i] = line;
                i++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        String returnString = "";
        for (int k = 0; k < array.length; k++)
        {
            returnString += array[k];
        }

        return returnString;
    }

}
