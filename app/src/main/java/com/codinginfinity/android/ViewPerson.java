package com.codinginfinity.android;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

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

/**
 * ViewPersons activity will retrive and display the current
 * users information and allow them to edit and save the new
 * infomation.
 *
 * The activity contains some un-enabled edit boxes that display the current
 * users details, and two buttons edit and view papers, once the
 * edit button is clicked it enables the edit boxes and changes the edit
 * button into a save button. When the save button is clicked it stores
 * the new values into variables and will update the database.
 * When the view papers button is clicked it will go to the view
 * papers activity
 *
 * @author Mark Klingenberg
 */
public class ViewPerson extends AppCompatActivity {

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    String username;

    @Override
    /**
     * This method gets called when the activity is created.
     * @param savedInstanceState
     * @return nothing
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);

        username = getIntent().getExtras().getString("User");

        File file = new File(path + "/people.json");
        String jsonString = Load(file);

        EditText name = (EditText) findViewById(R.id.edtName);
        EditText password = (EditText) findViewById(R.id.edtPassword);
        EditText unitEarned = (EditText) findViewById(R.id.edtUnitsEarned);
        EditText email = (EditText) findViewById(R.id.edtEmail);
        EditText research = (EditText) findViewById(R.id.edtReasearch);
        EditText contactD = (EditText) findViewById(R.id.edtCon);

        try
        {
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObj = jsonArray.getJSONObject(i) ;
                if(username.compareTo(jsonObj.getString("name")) == 0){
                    name.setText(jsonObj.getString("name"));
                    password.setText(jsonObj.getString("password"));
                    unitEarned.setText(jsonObj.getString("units"));
                    email.setText(jsonObj.getString("email"));
                    research.setText(jsonObj.getString("group"));
                    contactD.setText(jsonObj.getString("contact"));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private String Password ;
    private String Name ;
    private String Email ;
    private String ContactD;
    /**
     * when this button is clicked it makes all the edit
     * boxes enabled and changes the button to a save button.
     * when the save button is clicked it stores the text in
     * the edit boxes into variable and update the database.
     *
     * @param   v   the edit buton
     */
    public void btnEditOnClick(View v)
    {
        Button editBtn = (Button) v ;
        Button viewBtn = (Button) findViewById(R.id.btnView);
        Button reportBtn = (Button) findViewById(R.id.btnRe);
        Button cancelBtn = (Button) findViewById(R.id.btnCancel);

        EditText name = (EditText) findViewById(R.id.edtName);
        EditText password = (EditText) findViewById(R.id.edtPassword);
        EditText email = (EditText) findViewById(R.id.edtEmail);
        EditText contactD = (EditText) findViewById(R.id.edtCon);


        Password = password.getText().toString() ;
        Name = name.getText().toString() ;
        Email = email.getText().toString() ;
        ContactD = contactD.getText().toString();


       if (editBtn.getText().equals("Edit")) {
           password.setEnabled(true);
           email.setEnabled(true);
           contactD.setEnabled(true);
           editBtn.setText("save");
           cancelBtn.setEnabled(true);
           cancelBtn.setVisibility(View.VISIBLE);
           viewBtn.setEnabled(false);
           reportBtn.setEnabled(false);
           return ;
       }
       if(editBtn.getText().equals("save")){
           Password = password.getText().toString() ;
           Email = email.getText().toString() ;
           ContactD = contactD.getText().toString() ;

           File file = new File(path + "/people.json");
           String json =  Load(file);

           try
           {
               JSONArray jsonArray = new JSONArray(json);
               for(int i = 0; i < jsonArray.length(); i++)
               {
                   JSONObject jsonObj = jsonArray.getJSONObject(i) ;
                   if(Name.compareTo(jsonObj.getString("name")) == 0){

                       jsonObj.put("password",Password);
                       jsonObj.put("email", Email);
                       jsonObj.put("contact",ContactD);

                       Save(file,jsonArray.toString());
                   }
               }

           } catch (JSONException e) {
               e.printStackTrace();
           }


           editBtn.setText("Edit");
           password.setEnabled(false);
           email.setEnabled(false);
           contactD.setEnabled(false);
           viewBtn.setEnabled(true);
           reportBtn.setEnabled(true);
           cancelBtn.setEnabled(false);
           cancelBtn.setVisibility(View.INVISIBLE);
           return ;
       }
    }
    /**
     * This function will cancl lany edited texts and restore them to their previous states
     * 
     * @param   v   the View
     */
    public void btnCancelOnClick(View v){
        Button cancelBtn = (Button) v ;
        Button viewBtn = (Button) findViewById(R.id.btnView);
        Button editBtn = (Button) findViewById(R.id.btnEdit);
        Button reBtn = (Button) findViewById(R.id.btnRe);
        EditText password = (EditText) findViewById(R.id.edtPassword);
        EditText email = (EditText) findViewById(R.id.edtEmail);
        EditText unitedEarned = (EditText) findViewById(R.id.edtUnitsEarned);
        EditText researchGroup = (EditText) findViewById(R.id.edtReasearch);
        EditText conD = (EditText) findViewById(R.id.edtCon);
        password.setText(Password);
        email.setText(Email);
        conD.setText(ContactD);
        editBtn.setText("Edit");
        password.setEnabled(false);
        email.setEnabled(false);
        conD.setEnabled(false);
        viewBtn.setEnabled(true);
        reBtn.setEnabled(true);
        cancelBtn.setVisibility(View.INVISIBLE);
        cancelBtn.setEnabled(false);
        return ;
    }
    /**
     * This function simply opens the view papers activity
     *
     * @param v
     */
    public void btnViewOnClick(View v)
    {
        Intent intent = new Intent(this, ViewPublicationsActivity.class);
        intent.putExtra("User",username);
        startActivity(intent);
    }
    @Override
    /**
     * This method adds items to the action bar if it is present.
     * @return boolean
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_publications, menu);
        return true;
    }

    @Override  
    /**
     * This method handles action bar item clicks.
     * @return boolean
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Settings was clicked.
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnReportOnClick(View v)
    {
        Intent intent = new Intent(this, ViewReport.class);
        startActivity(intent);
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

}
