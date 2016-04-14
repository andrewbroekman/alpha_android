package com.codinginfinity.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.security.SecureRandom;
import java.util.Random;

public class AddUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

    }
    protected CreateUser newUser;
    private boolean succ ;
    private String Name ;
    private String Email ;
    private String ReGroup = "None";
    private Float UnitedEarned ;
    private String password ;
    private String ConD = "None";

    public void onAddClick(View v){
        Button addBtn = (Button) v ;
        EditText name = (EditText) findViewById(R.id.edtName);
        EditText email = (EditText) findViewById(R.id.edtEmail);
        EditText reGroup = (EditText) findViewById(R.id.edtReasearch);
        EditText conD = (EditText) findViewById(R.id.edtCon) ;
        Name = name.getText().toString() ;
        Email = email.getText().toString() ;
        if(Name.matches(""))
        {
            Toast.makeText(getApplicationContext(), "Please fill in the name field", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(Email.matches(""))
        {
            Toast.makeText(getApplicationContext(), "Please fill in the email field", Toast.LENGTH_SHORT).show();
            return ;
        }

        if(!reGroup.getText().equals(""))
        {
            ReGroup = reGroup.getText().toString();
        }
        if(!conD.getText().equals(""))
        {
            ConD = conD.getText().toString();
        }
        Random ran = new SecureRandom() ;
        password = generateString(ran, "abcdefhijklmnopqrstuvwxyzABCDEFHIJKLMNOPQRSTUVWXYZ123456789", 7 ) ;
        try {
            newUser = new CreateUser() ;
            succ = newUser.AddUser(
                    Name,
                    password,
                    Email,
                    ReGroup,
                    ConD,
                    getApplicationContext()
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(succ == true) {
            Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "A user with that name is already in the system", Toast.LENGTH_SHORT).show();
        }
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }

    public static String generateString(Random rng, String characters, int length)
    {
        char[] pass = new char[length];
        for (int i = 0; i < length; i++)
        {
            pass[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(pass);
    }
}
