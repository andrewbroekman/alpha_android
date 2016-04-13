package com.codinginfinity.android;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

     public void loginFunction(View view) {
         String enteredEmail = ((EditText) findViewById(R.id.edt_email)).getText().toString();
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
                     "\"group\" : \"Sexy-Girl\"," +
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
                     "\"group\" : \"Sexy-Girl\"," +
                     "\"units\" : \"7\"," +
                     "\"contact\" : \"0123456789\"," +
                     "\"permission\" : \"1\"" +
                     "}," +
                     "{" +
                     "\"name\" : \"Valteri Bottas\"," +
                     "\"password\" : \"qwerty123\"," +
                     "\"email\" : \"Valteri.Bottas@gmail.com\"," +
                     "\"group\" : \"Sexy-Girl\"," +
                     "\"units\" : \"12\"," +
                     "\"contact\" : \"0123456789\"," +
                     "\"permission\" : \"3\"" +
                     "}," +
                     "{" +
                     "\"name\" : \"Sebastian Vettel\"," +
                     "\"password\" : \"qwerty123\"," +
                     "\"email\" : \"Sebastian.Vettel@gmail.com\"," +
                     "\"group\" : \"Sexy-Girl\"," +
                     "\"units\" : \"9\"," +
                     "\"contact\" : \"0123456789\"," +
                     "\"permission\" : \"1\"" +
                     "}," +
                     "{" +
                     "\"name\" : \"Nico Rosbergl\"," +
                     "\"password\" : \"qwerty123\"," +
                     "\"email\" : \"Nico.Rosberg@gmail.com\"," +
                     "\"group\" : \"TheG\"," +
                     "\"units\" : \"5\"," +
                     "\"contact\" : \"0123456789\"," +
                     "\"permission\" : \"3\"" +
                     "}," +
                     "{" +
                     "\"name\" : \"Valentino Rossi\"," +
                     "\"password\" : \"qwerty123\"," +
                     "\"email\" : \"Valentino.Rossi@gmail.com\"," +
                     "\"group\" : \"\"," +
                     "\"units\" : \"3\"," +
                     "\"contact\" : \"0123456789\"," +
                     "\"permission\" : \"1\"" +
                     "}," +
                     "{" +
                     "\"name\" : \"Colin McRae\"," +
                     "\"password\" : \"qwerty123\"," +
                     "\"email\" : \"Colin.McRae@gmail.com\"," +
                     "\"group\" : \"Sexy-Girl\"," +
                     "\"units\" : \"5\"," +
                     "\"contact\" : \"0123456789\"," +
                     "\"permission\" : \"1\"" +
                     "}," +
                     "{" +
                     "\"name\" : \"Michael Schumacher\"," +
                     "\"password\" : \"qwerty123\"," +
                     "\"email\" : \"Michael.Schumacher@gmail.com\"," +
                     "\"group\" : \"TheG\"," +
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
                     "\"group\" : \"TheG\"," +
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

         if (enteredEmail.compareTo("demo") == 0 && enteredPassword.compareTo("demo") == 0) {
             //if(1==1){ //Used to speed up debugging
             Intent intent = new Intent(this, MainActivity.class);
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

}
