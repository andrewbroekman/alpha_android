package com.codinginfinity.android;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public CreatePublications(String name, String owner, String type, String state, String url, String target, String start, ArrayList<String> authors, Context ctx) throws JSONException {
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

        String FILENAME = "publications.json";

        FileOutputStream fos = null;
        try
        {
            fos = ctx.openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(pub.getBytes());
            fos.write("\n\r".getBytes());
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public CreatePublications(String name, String owner, String type, String state, String url, String target, String start, Context ctx)
    {
        //pub = new Publication(name, owner, type, state, url, target, start);
        //publicationsList.add(pub);
    }

    public JSONObject getList()
    {
        return obj;
    }
}
