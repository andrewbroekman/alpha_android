package com.codinginfinity.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

     public void loginFunction(View view) {
        String enteredEmail = ((EditText)findViewById(R.id.edt_email)).getText().toString();
        String enteredPassword = ((EditText)findViewById(R.id.edt_password)).getText().toString();

         //if (enteredEmail.compareTo("demo") == 0 && enteredPassword.compareTo("demo") == 0) {
         if(1==1){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
        }
    }

}
