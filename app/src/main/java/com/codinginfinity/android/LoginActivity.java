package com.codinginfinity.android;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

     public void loginFunction(View view) {
         String enteredName = ((EditText) findViewById(R.id.edt_name)).getText().toString();
         String enteredPassword = ((EditText) findViewById(R.id.edt_password)).getText().toString();

         File file = new File(path + "/people.json");
         if (!file.exists()) { // Generate fake json
             String peopleJsonString = "[" +
                                         "{" +
                                             "\"name\" : \"Kimi Raikkonen\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Kimi.Raikkonen@gmail.com\"," +
                                             "\"group\" : \"Sexy-Girl\"," +
                                             "\"units\" : \"5\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Felipe Massa\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Felipe.Massa@gmail.com\"," +
                                             "\"group\" : \"SexyGurls\"," +
                                             "\"units\" : \"1\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"2\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Fernando Alonso\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Fernando.Alonso@gmail.com\"," +
                                             "\"group\" : \"\"," +
                                             "\"units\" : \"8\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Jenson Button\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Jenson.Button@gmail.com\"," +
                                             "\"group\" : \"SexyGurls\"," +
                                             "\"units\" : \"7\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Valteri Bottas\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Valteri.Bottas@gmail.com\"," +
                                             "\"group\" : \"SexyGurls\"," +
                                             "\"units\" : \"12\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"3\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Sebastian Vettel\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Sebastian.Vettel@gmail.com\"," +
                                             "\"group\" : \"SexyGurls\"," +
                                             "\"units\" : \"9\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Nico Rosbergl\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Nico.Rosberg@gmail.com\"," +
                                             "\"group\" : \"CIRG\"," +
                                             "\"units\" : \"5\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"3\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Valentino Rossi\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Valentino.Rossi@gmail.com\"," +
                                             "\"group\" : \"ICSA\"," +
                                             "\"units\" : \"3\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Colin McRae\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Colin.McRae@gmail.com\"," +
                                             "\"group\" : \"SSFM\"," +
                                             "\"units\" : \"5\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Michael Schumacher\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Michael.Schumacher@gmail.com\"," +
                                             "\"group\" : \"SESAr\"," +
                                             "\"units\" : \"8\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Juan Pablo Montoya\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Juan.Pablo.Montoya@gmail.com\"," +
                                             "\"group\" : \"\"," +
                                             "\"units\" : \"9\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"Robert Kubica\"," +
                                             "\"password\" : \"qwerty123\"," +
                                             "\"email\" : \"Robert.Kubica@gmail.com\"," +
                                             "\"group\" : \"CSEDAR\"," +
                                             "\"units\" : \"5\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}," +
                                         "{" +
                                             "\"name\" : \"demo\"," +
                                             "\"password\" : \"demo\"," +
                                             "\"email\" : \"demo@gmail.com\"," +
                                             "\"group\" : \"demo\"," +
                                             "\"units\" : \"5\"," +
                                             "\"contact\" : \"0123456789\"," +
                                             "\"permission\" : \"1\"" +
                                         "}" +
                                    "]";
             Save(file, peopleJsonString);
         }

         //Get people.json
         String peopleJsonString = Load(file);


         String username = "";
         String password = "";
         String permission = "";

         // Try to make json object
         try {
             JSONArray jsonArray = new JSONArray(peopleJsonString);

             for (int i =0; i<jsonArray.length();i++) {
                 JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each person from array

                 if (enteredName.compareTo(jsonObject.getString("name")) == 0){
                     username = jsonObject.getString("name");
                     password = jsonObject.getString("password");
                     permission = jsonObject.getString("permission");
                     break;
                 }
             }

         } catch (JSONException e) {
             e.printStackTrace();
         }

         if (enteredName.compareTo(username) == 0 && enteredPassword.compareTo(password) == 0) {
             Intent intent = new Intent(this, MainActivity.class);
             intent.putExtra("User",username);
             intent.putExtra("Permission",permission);
             startActivity(intent);
             finish();
         } else {
             Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
         }
     }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    // Before 2.0
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
