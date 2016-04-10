package com.example.stuart.alphaandroidinterface;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;

public class EditPublication extends AppCompatActivity {

    int id = 0;
    ArrayList<String> list;
    ArrayList<Integer> placeholder, placeholder2;
    LinearLayout linearLayout1, linearLayout2;

    int size;
    int top = 0;
    int bottom = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_publication);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

        linearLayout1.setLayoutParams(params);
        linearLayout2.setLayoutParams(params);


        list= new ArrayList<>();
        placeholder = new ArrayList<>();
        placeholder2 = new ArrayList<>();
        list.add("Kimi Raikkonen");
        list.add("Felipe Massa");
        list.add("Fernando Alonso");
        list.add("Jenson Button");
        list.add("Valteri Bottas");
        list.add("Sebastian Vettel");
        list.add("Nico Rosberg");
        list.add("Valentino Rossi");
        list.add("Colin McRae");
        list.add("Michael Schumacher");
        list.add("Juan Pablo Montoya");
        list.add("Robert Kubica");

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
        int id = placeholder.get(placeholder.size()-1);
        id++;
        editText.setId(401);
        editText.setLayoutParams(params);
        params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        id = placeholder2.get(placeholder2.size()- 1);
        id++;
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
}

