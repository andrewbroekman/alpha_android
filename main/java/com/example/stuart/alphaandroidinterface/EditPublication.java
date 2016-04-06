package com.example.stuart.alphaandroidinterface;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class EditPublication extends AppCompatActivity {

    int id = 0;
    ArrayList<String> list;
    ArrayList<Integer> placeholder, placeholder2;
    RelativeLayout layout;
    TextView textView1, textView2;

    int size;
    int top = 0;
    int bottom = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_publication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        size = list.size();
        layout = (RelativeLayout) findViewById(R.id.edit_pub);
        textView1 = (TextView) findViewById(R.id.temp1);
        textView2 = (TextView) findViewById(R.id.temp2);
        textView1.setId(998);
        textView2.setId(999);
        top = 1;
        bottom = list.size();
        int prevTextViewId = 0;
        for(int i = 0; i < list.size(); i++)
        {
            final EditText textView = new EditText(this);
            textView.setText(list.get(i));
            textView.setTextSize(18);
            int curTextViewId = prevTextViewId + 1;
            placeholder.add(curTextViewId);
            textView.setId(curTextViewId);
            final RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);

            if(i == 0) {
                params.addRule(RelativeLayout.BELOW, textView1.getId());
                params.topMargin = 20;
                params.leftMargin = 60;
            }
            else {
                params.addRule(RelativeLayout.BELOW, prevTextViewId);
                params.topMargin = 20;
                params.leftMargin = 60;
            }


            textView.setLayoutParams(params);

            prevTextViewId = curTextViewId;
            layout.addView(textView, params);
        }

        String uri = "@drawable/minus";  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);

        int prevImageButtonId = list.size()+1;
        prevTextViewId = 0;
        for(int i = 0; i < list.size(); i++)
        {
            final ImageButton button = new ImageButton(this);

            int curTextViewId = prevTextViewId + 1;
            int curImageButtonId = curTextViewId + list.size();
            button.setId(curImageButtonId);
            placeholder2.add(curImageButtonId);
            button.setImageDrawable(res);
            button.setBackgroundColor(256);

            final RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);

            if(i == 0)
                params.addRule(RelativeLayout.BELOW, textView2.getId());
            else {
                params.addRule(RelativeLayout.BELOW, prevImageButtonId);
                params.addRule(RelativeLayout.ALIGN_BOTTOM, curTextViewId);
            }
            params.leftMargin = 300;
            //params.topMargin = 20;
            button.setLayoutParams(params);

            prevImageButtonId = curImageButtonId;
            prevTextViewId = curTextViewId;

            id = button.getId();
            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    int b_id = button.getId();
                    id = button.getId() - size;
                    EditText et1 = (EditText) findViewById(id);
                    ImageButton it1 = (ImageButton) findViewById(button.getId());

                    if(et1 != null && it1 != null && !placeholder.isEmpty()) {
                        if (id == placeholder.get(0) && top != placeholder.get(placeholder.size()-1))
                        {
                            EditText et2 = (EditText) findViewById(placeholder.get(1));
                           // et2.setText(placeholder2.size() + " -");
                            ImageButton it2 = (ImageButton) findViewById(placeholder2.get(1));
                            if(et2 != null && it2 != null) {
                                final RelativeLayout.LayoutParams params1 =
                                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                                params1.addRule(RelativeLayout.BELOW, textView1.getId());
                                params1.topMargin = 20;
                                params1.leftMargin = 60;

                                et2.setLayoutParams(params1);

                                top = et2.getId();
                                placeholder.remove(placeholder.indexOf(id));
                                placeholder2.remove(placeholder2.indexOf(button.getId()));
                                layout.removeViewInLayout(et1);
                                layout.removeViewInLayout(it1);
                                list.remove(et1.getText().toString());
                            }
                        }
                        else if (id == placeholder.get(placeholder.size()-1) && id != placeholder.get(0))
                        {
                            EditText et2 = (EditText) findViewById(id-1);
                            ImageButton it2 = (ImageButton) findViewById(button.getId()-1);
                            if(et2 != null && it2 != null) {
                                placeholder.remove(placeholder.indexOf(id));
                                placeholder2.remove(placeholder2.indexOf(button.getId()));
                                layout.removeViewInLayout(et1);
                                layout.removeViewInLayout(it1);
                                list.remove(et1.getText().toString());
                            }
                        }
                        else if(id > placeholder.get(0) && id < placeholder.get(placeholder.size()-1))
                        {
                            int index = placeholder.indexOf(id)+1;

                            EditText et2 = (EditText) findViewById(placeholder.get(index));
                            ImageButton it2 = (ImageButton) findViewById(placeholder2.get(index));
                            if(et2 != null && it2 != null)
                            {
                                placeholder.remove(placeholder.get(placeholder.indexOf(id)));
                                id += 12;
                                placeholder2.remove(placeholder2.get(placeholder2.indexOf(id)));

                                et2.setLayoutParams(et1.getLayoutParams());

                                layout.removeViewInLayout(et1);
                                layout.removeViewInLayout(it1);
                                list.remove(et1.getText().toString());
                            }
                        }
                        else if(id == placeholder.get(0) && id == placeholder.get(placeholder.size()-1))
                        {
                            layout.removeViewInLayout(et1);
                            layout.removeViewInLayout(it1);
                            list.remove(et1.getText().toString());
                            placeholder.clear();
                            placeholder2.clear();
                        }
                    }
                }
            });
            layout.addView(button, params);
        }
    }
}
