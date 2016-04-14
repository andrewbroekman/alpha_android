package com.codinginfinity.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.security.SecureRandom;
import java.util.Random;

public class addGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

    }
    protected CreateGroup newGroup;
    private boolean succ ;
    private String Name ;
    private String Field ;
    private String Leader ;

    public void onAddClick(View v){
        Button addBtn = (Button) v ;
        EditText name = (EditText) findViewById(R.id.edtName);
        EditText field = (EditText) findViewById(R.id.edtField);
        EditText leader = (EditText) findViewById(R.id.edtLeader);
        Name = name.getText().toString() ;
        Field = field.getText().toString() ;
        Leader = leader.getText().toString() ;
        if(Name.matches(""))
        {
            Toast.makeText(getApplicationContext(), "Please fill in the name field", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(Field.matches(""))
        {
            Toast.makeText(getApplicationContext(), "Please fill in the field of study", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(Field.matches(""))
        {
            Toast.makeText(getApplicationContext(), "Please fill in the leader", Toast.LENGTH_SHORT).show();
            return ;
        }

        try {
            newGroup = new CreateGroup() ;
            succ = newGroup.AddGroup(
                    Name,
                    Field,
                    Leader,
                    getApplicationContext()
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(succ == true) {
            Toast.makeText(getApplicationContext(), "Group added", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "A Group with that name is already in the system", Toast.LENGTH_SHORT).show();
        }
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

}
