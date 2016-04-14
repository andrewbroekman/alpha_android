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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_WORLD_READABLE;

/**
 * Created by Stuart on 2016/04/03.
 */
public class CreatePublications
{
   // protected Publication pub;
    //protected ArrayList<Publication> publicationsList;
    JSONObject obj;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();

    public CreatePublications(String adderName, String name, String owner, String type, String state, String url, String target, String start, ArrayList<String> authors) throws JSONException {
       // pub = new Publication(name, owner, type, state, url, target, start, authors);
       // publicationsList.add(pub);
        obj= new JSONObject();
        obj.put("name",name);
        obj.put("owner",owner);
        obj.put("type", type);
        obj.put("state", state);
        obj.put("url", url);
        obj.put("envisioned",target);
        obj.put("startdate", start);
        obj.put("author", authors);
        String pub = obj.toString();
        String jsonString;
        File file = new File(path + "/publications.json");

        if(file.exists())
        {
            jsonString = Load(file);
            jsonString += "," + pub;

            Save(file,jsonString);
        }
        else
        {
            Save(file, pub);
        }

        try
        {
            file = new File(path + "/people.json");
            String json =  Load(file);
            JSONArray jsonArray = new JSONArray(json);
            JSONArray jsonPubArray;
            for (int i =0; i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each person from array

                if (adderName.compareTo(jsonObject.getString("name")) == 0)
                {
                    if(jsonObject.isNull("publications"))
                    {
                        jsonPubArray = new JSONArray();
                        jsonPubArray = jsonPubArray.put(obj);
                        jsonObject.put("publications", jsonPubArray);
                    }
                    else
                    {
                        jsonPubArray = jsonObject.getJSONArray("publications");
                        jsonPubArray = jsonPubArray.put(obj);
                        jsonObject.put("publications", jsonPubArray);
                    }
                }
            }
            Save(file, jsonArray.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public CreatePublications(String adderName, String name, String owner, String type, String state, String url, String target, String start) throws JSONException
    {
        obj= new JSONObject();
        obj.put("name",name);
        obj.put("owner",owner);
        obj.put("type", type);
        obj.put("state", state);
        obj.put("url", url);
        obj.put("envisioned",target);
        obj.put("startdate", start);

        File file = new File(path + "/people.json");
        String json =  Load(file);


        try
        {
            JSONArray jsonArray = new JSONArray(json);
            JSONArray jsonPubArray;
            for (int i =0; i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each person from array

                if (adderName.compareTo(jsonObject.getString("name")) == 0)
                {
                    if(jsonObject.isNull("publications"))
                    {
                        jsonPubArray = new JSONArray();
                        jsonPubArray = jsonPubArray.put(obj);
                        jsonObject.put("publications", jsonPubArray);
                    }
                    else
                    {
                        jsonPubArray = jsonObject.getJSONArray("publications");
                        jsonPubArray = jsonPubArray.put(obj);
                        jsonObject.put("publications", jsonPubArray);
                    }
                }
            }
            Save(file, jsonArray.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getList()
    {
        return obj;
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
