package com.codinginfinity.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dian on 4/14/2016.
 */
public class TestClass extends AppCompatActivity
{
    TestClass(Intent intent)
    {
        try {

        intent.putExtra("groupName", "Sexy-Girl");
        intent.putExtra("groupLink", "icsa.cs.up.ac.za");

        String peopleJsonString = "[" +
                "{" +
                "\"name\" : \"Kimi Raikkonen\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Kimi.Raikkonen@gmail.com\"," +
                "\"group\" : \"Sexy-Girl\"," +
                "\"units\" : \"5\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Felipe Massa\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Felipe.Massa@gmail.com\"," +
                "\"group\" : \"Sexy-Girl\"," +
                "\"units\" : \"1\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"2\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Fernando Alonso\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Fernando.Alonso@gmail.com\"," +
                "\"group\" : \"\"," +
                "\"units\" : \"8\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Jenson Button\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Jenson.Button@gmail.com\"," +
                "\"group\" : \"Sexy-Girl\"," +
                "\"units\" : \"7\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Valteri Bottas\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Valteri.Bottas@gmail.com\"," +
                "\"group\" : \"Sexy-Girl\"," +
                "\"units\" : \"12\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"3\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Sebastian Vettel\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Sebastian.Vettel@gmail.com\"," +
                "\"group\" : \"Sexy-Girl\"," +
                "\"units\" : \"9\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Nico Rosbergl\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Nico.Rosberg@gmail.com\"," +
                "\"group\" : \"TheG\"," +
                "\"units\" : \"5\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"3\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Valentino Rossi\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Valentino.Rossi@gmail.com\"," +
                "\"group\" : \"\"," +
                "\"units\" : \"3\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Colin McRae\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Colin.McRae@gmail.com\"," +
                "\"group\" : \"Sexy-Girl\"," +
                "\"units\" : \"5\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Michael Schumacher\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Michael.Schumacher@gmail.com\"," +
                "\"group\" : \"TheG\"," +
                "\"units\" : \"8\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Juan Pablo Montoya\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Juan.Pablo.Montoya@gmail.com\"," +
                "\"group\" : \"\"," +
                "\"units\" : \"9\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"Robert Kubica\"," +
                "\"password\" : \"qwerty123\"," +
                "\"email\" : \"Robert.Kubica@gmail.com\"," +
                "\"group\" : \"TheG\"," +
                "\"units\" : \"5\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}," +
                "{" +
                "\"name\" : \"demo\"," +
                "\"password\" : \"demo\"," +
                "\"email\" : \"demo@gmail.com\"," +
                "\"group\" : \"demo\"," +
                "\"units\" : \"5\"," +
                "\"contact\" : \"0123456789\"," +
                "\"permission\" : \"1\"," +
                "\"publications\" : []" +
                "}" +
                "]";
        intent.putExtra("peopleString",peopleJsonString);
    }
    catch (Exception e)
    {
        System.out.println("ERRRRRROR2: "+e);
    }
    }
}
