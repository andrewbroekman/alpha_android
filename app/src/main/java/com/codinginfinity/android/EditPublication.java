package com.codinginfinity.android;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.lang.Integer;import java.lang.Override;import java.lang.String;import java.util.ArrayList;

public class EditPublication extends AppCompatActivity {

    int id = 0;
    ArrayList<String> list;
    ArrayList<Integer> placeholder, placeholder2;
    LinearLayout linearLayout1, linearLayout2;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    String pub_name;
    EditText edtName1, edtOwner1, edtType1, edtDate1, edtURL1;
    Spinner edtSpinner;
    String [] spinnerOps;
    String name, owner, type, state, envisionedDate, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_publication);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


/*
        int count=0;
        BufferedReader br;
        try {
            if((br = new BufferedReader(new FileReader(""))) != null) {
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {//just to count the number of elements in the file to create the array
                    count++;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String [] strArray = new String[count];
        try{
            if ((br = new BufferedReader(new FileReader(""))) != null) {
                String sCurrentLine;
                int i=0;
                while ((sCurrentLine = br.readLine()) != null) {
                    strArray[i] = sCurrentLine;
                    i++;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

*/
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

        linearLayout1.setLayoutParams(params);
        linearLayout2.setLayoutParams(params);

        placeholder = new ArrayList<>();
        placeholder2 = new ArrayList<>();


        /*LinearLayout.LayoutParams */params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int prevTextViewId = 0;
        int height = 0;
        for(int i = 0; i < list.size(); i++)
        {
            final EditText textView = new EditText(this);
            textView.setText(list.get(i));
            textView.setTextSize(18);
            textView.setWidth(300);
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
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            final ImageButton button = new ImageButton(this);
            int curImageButtonId = prevImageButtonId + 1;
            button.setMaxWidth(18);
            button.setId(curImageButtonId);
            placeholder2.add(curImageButtonId);
            button.setImageDrawable(res);
            button.setBackgroundColor(256);


            params.topMargin = 10;
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

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(EditPublication.this);
                    builder1.setMessage("Are you sure you want to remove " + et.getText().toString() + " from the Publication?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id1) {

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
        editText.setId(401);
        editText.setLayoutParams(params);
        params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

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
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(EditPublication.this);
                    builder1.setMessage("Are you sure you want to add " + et.getText().toString() + " to the Publication?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id1) {
                                    final EditText editText = new EditText(EditPublication.this);
                                    int id = placeholder.get(placeholder.size() - 1);
                                    id++;
                                    editText.setId(id);
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

    public void setButton(int id_et, int id_button, ImageButton button)
    {
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
/*
    dunno what all this stuff does

    private String Name ;
    private String Owner;
    private String Type;
    private String Date;
    private String URL;
    public void oneditclick(View v){
        Button btnEdit = (Button) v ;
        Button btnCancel = (Button) findViewById(R.id.btnCan) ;
        EditText edtName = (EditText) findViewById(R.id.name_edit) ;
        EditText edtOwner = (EditText) findViewById(R.id.owner_edit) ;
        EditText edtType = (EditText) findViewById(R.id.type_edit) ;
        EditText edtDate = (EditText) findViewById(R.id.date_edit) ;
        EditText edtURL = (EditText) findViewById(R.id.url_edit) ;
        Name = edtName.getText().toString() ;
        Owner = edtOwner.getText().toString() ;
        Type = edtType.getText().toString() ;
        Date = edtDate.getText().toString() ;
        URL = edtURL.getText().toString() ;

        if(btnEdit.getText().equals("Edit"))
        {
            edtName.setEnabled(true);
            edtOwner.setEnabled(true);
            edtType.setEnabled(true);
            edtDate.setEnabled(true);
            edtURL.setEnabled(true);
            btnEdit.setText("Save");
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setEnabled(true);
            return;
        }
        if(btnEdit.getText().equals("Save")){
            Name = edtName.getText().toString() ;
            Owner = edtName.getText().toString() ;
            Type = edtName.getText().toString() ;
            Date = edtName.getText().toString() ;
            URL = edtName.getText().toString() ;
            btnEdit.setText("Edit");
            btnEdit.setEnabled(true);
            btnCancel.setVisibility(View.INVISIBLE);
            btnCancel.setEnabled(false);
            edtName.setEnabled(false);
            edtOwner.setEnabled(false);
            edtType.setEnabled(false);
            edtDate.setEnabled(false);
            edtURL.setEnabled(false);
            return;
        }
    }

    public void cancelClick(View v) {
        Button btnCan = (Button) v ;
        Button btnEdit = (Button) findViewById(R.id.btnEdit) ;
        EditText edtName = (EditText) findViewById(R.id.name_edit) ;
        EditText edtOwner = (EditText) findViewById(R.id.owner_edit) ;
        EditText edtType = (EditText) findViewById(R.id.type_edit) ;
        EditText edtDate = (EditText) findViewById(R.id.date_edit) ;
        EditText edtURL = (EditText) findViewById(R.id.url_edit) ;
        btnEdit.setText("Edit");
        edtName.setText(Name);
        edtOwner.setText(Owner);
        edtType.setText(Type);
        edtDate.setText(Date);
        edtURL.setText(URL);
        edtName.setEnabled(false);
        edtOwner.setEnabled(false);
        edtType.setEnabled(false);
        edtDate.setEnabled(false);
        edtURL.setEnabled(false);
        btnCan.setVisibility(View.INVISIBLE);
        btnCan.setEnabled(false);
        return;
    }
*/
   
}

