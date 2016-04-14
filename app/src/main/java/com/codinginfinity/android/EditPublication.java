package com.codinginfinity.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;

import com.codinginfinity.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Integer;import java.lang.Override;import java.lang.String;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.codinginfinity.android.R.array.state_array;

public class EditPublication extends AppCompatActivity {

    int id = 0;
    ArrayList<String> list;
    ArrayList<Integer> placeholder, placeholder2;
    LinearLayout linearLayout1, linearLayout2;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    String pub_name;
    EditText edtName1, edtOwner1, edtType1, edtDate1, edtURL1;
    Spinner edtSpinner;
    String[] spinnerOps;// = {"In Progress", "Submitted", "In Revision", "Rejected" , "Published", "Abandoned"};
    String name, owner, type, state, envisionedDate, url, user;
    boolean authors;
    JSONArray publications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_publication);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
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

        spinnerOps = new String[6];
        spinnerOps[0] = "In Progress";
        spinnerOps[1] = "Submitted";
        spinnerOps[2] = "In Revision";
        spinnerOps[3] = "Rejected";
        spinnerOps[4] = "Published";
        spinnerOps[5] = "Abandoned";
        authors = false;

        pub_name = getIntent().getExtras().getString("pulication_name");
        user = getIntent().getExtras().getString("user_name");
        File file = new File(path + "/people.json");

        String author = "";// = new JSONArray();

        String pubString = Load(file);

        try {
            JSONArray jsonArray = new JSONArray(pubString);
            JSONArray jsonPubArray;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each person from array

                if (user.compareTo(jsonObject.getString("name")) == 0) {
                    publications = jsonObject.getJSONArray("publications");
                    for (i = 0; i < jsonArray.length(); i++) {
                        jsonObject = publications.getJSONObject(i);
                        if (pub_name.compareTo(jsonObject.getString("name")) == 0) {
                            name = pub_name;
                            type = jsonObject.getString("type");
                            state = jsonObject.getString("state");
                            envisionedDate = jsonObject.getString("envisioned");
                            url = jsonObject.getString("url");
                            owner = jsonObject.getString("owner");
                            if (!jsonObject.isNull("author")) {
                                authors = true;
                                author = jsonObject.getString("author");
                            }
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        int index = 0;
        for (int i = 0; i < spinnerOps.length; i++) {
            if (state != null && state.equals(spinnerOps[i])) {
                index = i;
                break;
            }
        }


        edtSpinner = (Spinner) findViewById(R.id.state_edit);
        edtSpinner.setSelection(index);

        edtName1 = (EditText) findViewById(R.id.name_edit);
        edtName1.setText(name);
        edtName1.setEnabled(false);

        edtOwner1 = (EditText) findViewById(R.id.owner_edit);
        edtOwner1.setText(owner);
        edtOwner1.setEnabled(false);

        edtType1 = (EditText) findViewById(R.id.type_edit);
        edtType1.setText(type);
        edtType1.setEnabled(false);

        edtDate1 = (EditText) findViewById(R.id.date_edit);
        edtDate1.setText(envisionedDate);
        edtDate1.setEnabled(false);

        edtURL1 = (EditText) findViewById(R.id.url_edit);
        edtURL1.setText(url);
        edtURL1.setEnabled(false);


        if (authors) {

            author = author.substring(1,author.length()-2);
            String array [] = author.split(",");
            list = new ArrayList<>();

            for (int i = 0; i < array.length ; i++)
            {
                    list.add(array[i]);
            }


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

            linearLayout1.setLayoutParams(params);
            linearLayout2.setLayoutParams(params);

            placeholder = new ArrayList<>();
            placeholder2 = new ArrayList<>();


            params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            int prevTextViewId = 0;
            int height = 0;
            for (int i = 0; i < list.size(); i++) {
                final EditText textView = new EditText(this);
                textView.setText(list.get(i));
                textView.setTextSize(18);
                textView.setWidth(300);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                int curTextViewId = prevTextViewId + 1;
                placeholder.add(curTextViewId);
                textView.setId(curTextViewId);
                textView.setEnabled(false);
                params.topMargin = 10;
                textView.setLayoutParams(params);
                height = textView.getLayoutParams().height;
                prevTextViewId = curTextViewId;
                assert linearLayout1 != null;
                linearLayout1.addView(textView, params);
            }

            String uri = "@drawable/minus";  // where myresource (without the extension) is the file
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            final Drawable res = getResources().getDrawable(imageResource);


            int prevImageButtonId = 200;
            for (int i = 0; i < list.size(); i++) {
                params = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                final ImageButton button = new ImageButton(this);
                int curImageButtonId = prevImageButtonId + 1;
                button.setMaxWidth(18);
                button.setId(curImageButtonId);
                placeholder2.add(curImageButtonId);
                button.setImageDrawable(res);
                button.setBackgroundColor(256);
                button.setEnabled(false);

                params.topMargin = 10;
                params.height = 58;
                params.width = 58;
                button.setLayoutParams(params);
                prevImageButtonId = curImageButtonId;
                assert linearLayout2 != null;
                linearLayout2.addView(button, params);


                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        int et_id;
                        int button_id;
                        button_id = button.getId();
                        et_id = button_id - 200;

                        final EditText et = (EditText) findViewById(et_id);
                        final ImageButton ib = (ImageButton) findViewById(button_id);

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(EditPublication.this);
                        builder1.setMessage("Are you sure you want to remove " + et.getText().toString() + " from the Publication?");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id1) {
                                        int i = placeholder.indexOf(et.getId());
                                        placeholder.remove(i);
                                        i = placeholder2.indexOf(et.getId()+200);
                                        placeholder2.remove(i);
                                        list.remove(et.getText().toString());
                                        linearLayout1.removeView(et);
                                        linearLayout2.removeView(ib);
                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

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
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            params.topMargin = 10;
            editText.setLayoutParams(params);
            editText.setWidth(300);
            editText.setTextSize(18);
            editText.setHint("Add New Author...");
            editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            editText.setEnabled(false);

            editText.setId(401);
            editText.setLayoutParams(params);
            params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            button_Add.setId(402);
            button_Add.setEnabled(false);

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
            button_Add.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final EditText et = (EditText) findViewById(401);
                    if (!isEmpty(et)) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(EditPublication.this);
                        builder1.setMessage("Are you sure you want to add " + et.getText().toString() + " to the Publication?");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id1) {
                                        final EditText editText = new EditText(EditPublication.this);
                                        list.add(et.getText().toString());
                                        int id = placeholder.get(placeholder.size() - 1);
                                        id++;
                                        editText.setId(id);
                                        placeholder.add(id);
                                        placeholder.add(id);
                                        editText.setWidth(300);
                                        editText.setTextSize(18);
                                        editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                        editText.setText(et.getText().toString());

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                                        params.topMargin = 10;
                                        editText.setLayoutParams(params);

                                        final ImageButton newbutton = new ImageButton(EditPublication.this);
                                        newbutton.setImageDrawable(res);
                                        newbutton.setBackgroundColor(256);
                                        button_Add.setMaxHeight(height2);
                                        button_Add.setMinimumHeight(height2);
                                        id = placeholder2.get(placeholder2.size() - 1);
                                        id++;
                                        placeholder2.add(id);
                                        newbutton.setId(id);
                                        params = new LinearLayout.LayoutParams(
                                                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                                        params.topMargin = 10;
                                        params.height = 58;
                                        params.width = 58;
                                        newbutton.setLayoutParams(params);

                                        EditText et = (EditText) findViewById(401);
                                        ImageButton ib = (ImageButton) findViewById(402);

                                        linearLayout1.removeViewInLayout(et);
                                        linearLayout2.removeViewInLayout(ib);

                                        params = new LinearLayout.LayoutParams(
                                                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

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
        }

        public void setButton(int id_et, int id_button, ImageButton button) {
            int et_id = id_et;
            int button_id = id_button;

            final EditText et = (EditText) findViewById(et_id);
            final ImageButton ib = (ImageButton) findViewById(button_id);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(EditPublication.this);
            builder1.setMessage("Are you sure you want to remove " + et.getText().toString() + " from the Publication?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id1) {
                            placeholder.remove(et.getId());
                            placeholder2.remove(et.getId()+200);
                            linearLayout1.removeView(et);
                            linearLayout2.removeView(ib);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                            linearLayout1.setLayoutParams(params);
                            linearLayout2.setLayoutParams(params);
                            list.remove(et.getText().toString());
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

        private boolean isEmpty(EditText etText) {
            if (etText.getText().toString().trim().length() > 0) {
                return false;
            } else {
                return true;
            }
        }

        public void oneditclick(View v) {
            Button btnEdit = (Button) v;
            Button btnCancel = (Button) findViewById(R.id.btnCan);
            EditText edtName = (EditText) findViewById(R.id.name_edit);
            EditText edtOwner = (EditText) findViewById(R.id.owner_edit);
            EditText edtType = (EditText) findViewById(R.id.type_edit);
            EditText edtDate = (EditText) findViewById(R.id.date_edit);
            EditText edtURL = (EditText) findViewById(R.id.url_edit);

            ArrayList<EditText> etList = new ArrayList<>();
            ArrayList<ImageButton> ibList = new ArrayList<>();
            EditText editAdd;
            ImageButton buttonAdd;
            if(authors) {
                editAdd = (EditText) findViewById(401);
                buttonAdd = (ImageButton) findViewById(402);

                for (int i = 0; i < placeholder.size(); i++) {
                    EditText et = (EditText) findViewById(placeholder.get(i));
                    etList.add(et);
                }

                for (int i = 0; i < placeholder2.size(); i++) {
                    ImageButton ib = (ImageButton) findViewById(placeholder2.get(i));
                    ibList.add(ib);
                }
            }

            if (btnEdit.getText().equals("Edit")) {
                edtName.setEnabled(true);
                edtOwner.setEnabled(true);
                edtType.setEnabled(true);
                edtDate.setEnabled(true);
                edtURL.setEnabled(true);
                if(authors) {
                    editAdd = (EditText) findViewById(401);
                    buttonAdd = (ImageButton) findViewById(402);
                    editAdd.setEnabled(true);
                    buttonAdd.setEnabled(true);
                    for (int i = 0; i < placeholder.size(); i++) {
                        etList.get(i).setEnabled(true);
                    }

                    for (int i = 0; i < placeholder2.size(); i++) {
                        ibList.get(i).setEnabled(true);
                    }
                }
                btnEdit.setText("Save");
                btnCancel.setVisibility(View.VISIBLE);
                btnCancel.setEnabled(true);
                return;
            }
            if (btnEdit.getText().equals("Save")) {
                name = edtName.getText().toString();
                owner = edtOwner.getText().toString();
                type = edtType.getText().toString();
                Spinner s = (Spinner) findViewById(R.id.state_edit);
                state = s.getSelectedItem().toString();
                envisionedDate = edtDate.getText().toString();
                url = edtURL.getText().toString();
                btnEdit.setText("Edit");
                btnEdit.setEnabled(true);
                btnCancel.setVisibility(View.INVISIBLE);
                btnCancel.setEnabled(false);
                edtName.setEnabled(false);
                edtOwner.setEnabled(false);
                edtType.setEnabled(false);
                edtDate.setEnabled(false);
                edtURL.setEnabled(false);
                if(authors) {
                    editAdd = (EditText) findViewById(401);
                    buttonAdd = (ImageButton) findViewById(402);
                    editAdd.setEnabled(false);
                    buttonAdd.setEnabled(false);
                    for (int i = 0; i < placeholder.size(); i++) {
                        etList.get(i).setEnabled(false);
                    }

                    for (int i = 0; i < placeholder2.size(); i++) {
                        ibList.get(i).setEnabled(false);
                    }
                }

                int e = 0;
                File file = new File(path + "/people.json");
                String pubString = Load(file);
                String startDate = "";
                JSONArray jsonArray;
                try {
                    jsonArray = new JSONArray(pubString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each person from array
                        if (user.compareTo(jsonObject.getString("name")) == 0)
                        {
                            publications = jsonObject.getJSONArray("publications");
                            for (int j = 0; j < publications.length(); j++)
                            {
                                jsonObject = publications.getJSONObject(j);
                                if (pub_name.compareTo(jsonObject.getString("name")) == 0)
                                {
                                    JSONObject person = new JSONObject();
                                    jsonObject.put("name", name);
                                    jsonObject.put("owner", owner);
                                    jsonObject.put("type", type);
                                    jsonObject.put("state", state);
                                    jsonObject.put("envisioned", envisionedDate);
                                    jsonObject.put("url", url);
                                    jsonObject.put("author", list);
                                    publications.put(person);
                                    break;
                                }
                                e++;
                            }
                        }
                    }


                    Save(file,jsonArray.toString());
                    pubString = Load(file);
                    Intent intent = new Intent(this, ViewPublicationsActivity.class);
                    intent.putExtra("User",user);
                    startActivity(intent);

                } catch (JSONException ew) {
                    ew.printStackTrace();
                }


            }
        }

        public void cancelClick(View v) {
            Button btnCan = (Button) v;
            Button btnEdit = (Button) findViewById(R.id.btnEdit);
            EditText edtName = (EditText) findViewById(R.id.name_edit);
            EditText edtOwner = (EditText) findViewById(R.id.owner_edit);
            EditText edtType = (EditText) findViewById(R.id.type_edit);
            EditText edtDate = (EditText) findViewById(R.id.date_edit);
            EditText edtURL = (EditText) findViewById(R.id.url_edit);

            EditText editAdd = (EditText) findViewById(401);
            ImageButton buttonAdd = (ImageButton) findViewById(402);
            btnEdit.setText("Edit");
            edtName.setText(name);
            edtOwner.setText(owner);
            edtType.setText(type);
            edtDate.setText(envisionedDate);
            edtURL.setText(url);
            edtName.setEnabled(false);
            edtOwner.setEnabled(false);
            edtType.setEnabled(false);
            edtDate.setEnabled(false);
            edtURL.setEnabled(false);
            btnCan.setVisibility(View.INVISIBLE);
            btnCan.setEnabled(false);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            return;
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

