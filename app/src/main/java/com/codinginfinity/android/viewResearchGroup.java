package com.codinginfinity.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Dian on 4/13/2016.
 */
public class viewResearchGroup extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {

            String GroupName = extra.getString("groupName");
            String GroupLink = extra.getString("groupLink");
            String Member = extra.getString("peopleString");


            TextView Group = (TextView) findViewById(R.id.GroupName);
            Group.setText(GroupName);
            Group = (TextView) findViewById(R.id.GroupLink);

            Group.setText("http://"+GroupLink);
            Group = (TextView) findViewById(R.id.GroupLink);
            Group.setMovementMethod(LinkMovementMethod.getInstance());


            try
            {
                JSONArray jsonArray = new JSONArray(Member);
                TableLayout Researchers = (TableLayout) findViewById(R.id.GroupMemebers);

                for (int i =0; i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each person from array

                    if (GroupName.compareTo(jsonObject.getString("group")) == 0){
                        TableRow row = new TableRow(this);
                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                        row.setLayoutParams(lp);
                        TextView tv = new TextView(this);
                        tv.setText(jsonObject.getString("name"));
                        row.addView(tv);
                        Researchers.addView(row);
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("ERRRRRROR: "+e);
            }
        }
    }
}
