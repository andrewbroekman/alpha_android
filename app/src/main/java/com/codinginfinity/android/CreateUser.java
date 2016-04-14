package com.codinginfinity.android;

import android.content.Context;
import android.os.Environment;

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
 * Created by AmandaGirl on 4/14/2016.
 */
public class CreateUser {

    JSONObject obj;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    public CreateUser(){} ;
    public boolean AddUser(String name, String password, String email, String group, String contact, Context ctx) throws JSONException
    {
        obj= new JSONObject();
        obj.put("name",name);
        obj.put("password","swag1");
        obj.put("email", email);
        obj.put("group", group);
        obj.put("units", 0);
        obj.put("contact",contact);
        obj.put("permission", 1);

        File file = new File(path + "/people.json");
        String json =  Load(file);


        try
        {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObj = jsonArray.getJSONObject(i) ;
                if(name.compareTo(jsonObj.getString("name")) == 0){
                    return false ;
                }
            }
            jsonArray.put(obj);

            Save(file,jsonArray.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true ;
    }

    public JSONObject getList()
    {
        return obj;
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
