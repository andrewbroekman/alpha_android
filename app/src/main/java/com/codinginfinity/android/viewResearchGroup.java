package com.codinginfinity.android;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Dian on 4/13/2016.
 */
public class viewResearchGroup extends AppCompatActivity {

    String GroupName;
    String Member;
    int id = 0;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<Integer> placeholder, placeholder2;
    LinearLayout linearLayout1, linearLayout2;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    String pub_name;
    EditText edtName1, edtOwner1, edtType1, edtDate1, edtURL1;
    Spinner edtSpinner;
    String [] spinnerOps;
    String name, owner, type, state, envisionedDate, url;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);


        Bundle extra = getIntent().getExtras();

        if (extra != null) {

            GroupName = extra.getString("group_name");
            String fieldOfStudy = extra.getString("fieldOfStudy");
            Member = getPersons();
            createList();
            DisplayMembers();
            TextView Group = (TextView) findViewById(R.id.GroupName);
            Group.setText(GroupName);
            Group = (TextView) findViewById(R.id.GroupLink);

            Group.setText(fieldOfStudy);
        }
    }

    String getPersons()
    {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path + "/people.json");
        String peopleJsonString = Load(file);

        return peopleJsonString;
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

    public void DisplayMembers()
    {
        linearLayout1 = (LinearLayout) findViewById(R.id.Group_linearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.Group_linearLayout2);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(550, LinearLayout.LayoutParams.MATCH_PARENT);

        linearLayout1.setLayoutParams(params);
        params = new LinearLayout.LayoutParams(90, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout2.setLayoutParams(params);

        placeholder = new ArrayList<>();
        placeholder2 = new ArrayList<>();


        /*LinearLayout.LayoutParams */params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int prevTextViewId = 0;
        int height = 0;
        for(int i = 0; i < list.size(); i++)
        {
            final EditText textView = new EditText(this);
            textView.setText(list.get(i));
            textView.setTextSize(18);
            textView.setWidth(500);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            int curTextViewId = prevTextViewId + 1;
            placeholder.add(curTextViewId);
            textView.setId(curTextViewId);
            params.topMargin = 10;
            textView.setLayoutParams(params);
            height = textView.getLayoutParams().height;
            prevTextViewId = curTextViewId;
            assert linearLayout1 != null;
            linearLayout1.addView(textView,params);
        }

        String uri = "@drawable/minus";  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        final Drawable res = getResources().getDrawable(imageResource);


        int prevImageButtonId = 200;
        for(int i = 0; i < list.size(); i++)
        {
            params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            final ImageButton button = new ImageButton(this);
            int curImageButtonId = prevImageButtonId + 1;
            button.setMaxWidth(18);
            button.setId(curImageButtonId);
            placeholder2.add(curImageButtonId);
            button.setImageDrawable(res);
            button.setBackgroundColor(256);


            params.topMargin = 25;
            params.bottomMargin = 60;
            params.height = 58;
            params.width = 58;
            button.setLayoutParams(params);
            prevImageButtonId = curImageButtonId;
            assert linearLayout2 != null;
            linearLayout2.addView(button,params);


            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    int et_id;
                    int button_id;
                    button_id = button.getId();
                    et_id = button_id - 200;

                    final EditText et = (EditText) findViewById(et_id);
                    final ImageButton ib = (ImageButton) findViewById(button_id);

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(viewResearchGroup.this);
                    builder1.setMessage("Are you sure you want to remove " + et.getText().toString() + " from the Publication?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id1) {

                                    linearLayout1.removeView(et);
                                    linearLayout2.removeView(ib);
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                    linearLayout1.setLayoutParams(params);
                                    linearLayout2.setLayoutParams(params);
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });
        }

        EditText editText = new EditText(this);
        final ImageButton button_Add = new ImageButton(this);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.topMargin = 10;
        editText.setLayoutParams(params);
        editText.setWidth(500);
        editText.setTextSize(18);
        editText.setHint("Add New Author...");
        editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editText.setId(401);
        editText.setLayoutParams(params);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        button_Add.setId(402);

        String uri2 = "@drawable/plus";  // where myresource (without the extension) is the file
        int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
        final Drawable res2 = getResources().getDrawable(imageResource2);

        button_Add.setImageDrawable(res2);
        button_Add.setBackgroundColor(256);
        button_Add.setMaxHeight(height);
        button_Add.setMinimumHeight(height);

        params.topMargin = 10;
        params.height = 58;
        params.width = 58;
        button_Add.setLayoutParams(params);

        linearLayout1.addView(editText);
        linearLayout2.addView(button_Add);
        final int height2 = height;
        button_Add.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                final EditText et = (EditText) findViewById(401);
                if(!isEmpty(et))
                {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(viewResearchGroup.this);
                    builder1.setMessage("Are you sure you want to add " + et.getText().toString() + " to the Publication?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id1) {
                                    final EditText editText = new EditText(viewResearchGroup.this);
                                    int id = placeholder.get(placeholder.size() - 1);
                                    id++;
                                    editText.setId(id);
                                    editText.setWidth(300);
                                    editText.setTextSize(18);
                                    editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    editText.setText(et.getText().toString());

                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                    params.topMargin = 10;
                                    editText.setLayoutParams(params);

                                    final ImageButton newbutton = new ImageButton(viewResearchGroup.this);
                                    newbutton.setImageDrawable(res);
                                    newbutton.setBackgroundColor(256);
                                    button_Add.setMaxHeight(height2);
                                    button_Add.setMinimumHeight(height2);
                                    id = placeholder2.get(placeholder2.size() - 1);
                                    id++;
                                    newbutton.setId(id);
                                    params = new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                    params.topMargin = 10;
                                    params.height = 58;
                                    params.width = 58;
                                    newbutton.setLayoutParams(params);

                                    EditText et = (EditText) findViewById(401);
                                    ImageButton ib = (ImageButton) findViewById(402);

                                    linearLayout1.removeViewInLayout(et);
                                    linearLayout2.removeViewInLayout(ib);

                                    params = new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                    linearLayout1.setLayoutParams(params);
                                    linearLayout2.setLayoutParams(params);

                                    linearLayout1.addView(editText);
                                    linearLayout2.addView(newbutton);

                                    newbutton.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            setButton(editText.getId(), newbutton.getId(), newbutton);
                                        }
                                    });


                                    linearLayout1.addView(et);
                                    et.setText("");
                                    et.setHint("Add New Author...");
                                    linearLayout2.addView(ib);

                                    linearLayout1.setLayoutParams(params);
                                    linearLayout2.setLayoutParams(params);

                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });
    }

    public void setButton(int id_et, int id_button, ImageButton button)
    {
        int et_id = id_et;
        int button_id = id_button;

        final EditText et = (EditText) findViewById(et_id);
        final ImageButton ib = (ImageButton) findViewById(button_id);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(viewResearchGroup.this);
        builder1.setMessage("Are you sure you want to remove " + et.getText().toString() + " from the Publication?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id1) {

                        linearLayout1.removeView(et);
                        linearLayout2.removeView(ib);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        linearLayout1.setLayoutParams(params);
                        linearLayout2.setLayoutParams(params);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
        linearLayout1.setMinimumHeight(linearLayout2.getHeight());
    }

    private boolean isEmpty(EditText etText)
    {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void createList()
    {
        try {
            JSONArray jsonArray = new JSONArray(Member);

            for (int i =0; i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each person from array

                if (GroupName.compareTo(jsonObject.getString("group")) == 0){
                    String name = jsonObject.getString("name");
                    list.add(name);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
