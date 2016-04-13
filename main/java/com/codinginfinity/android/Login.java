package com.codinginfinity.android;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codinginfinity.android.R;import java.lang.Override;import java.lang.String;


public class Login extends ActionBarActivity {

    String FILENAME = "Session";
    EditText username;
    String user;
    EditText password;
    String pass;
    TextView error;
    String for_test_purpose_user = "JohnSmith";//ohnSmith";
    String for_test_purpose_password = "12345";//ohnRoxs";
    String for_test_purpose_user_type = "Super";
    String string = /*for_test_purpose_user + '\n' + for_test_purpose_password + '\n' +*/ for_test_purpose_user_type;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = (Button) findViewById(R.id.login);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                username = (EditText) findViewById(R.id.user);
                user = username.getText().toString();

                password = (EditText) findViewById(R.id.password);
                pass = password.getText().toString();

                error = (TextView) findViewById(R.id.error);
                String e = "Incorrect Login Details";


                if(user.equals(for_test_purpose_user) && pass.equals(for_test_purpose_password))
                {
                 /*   try
                    {
                        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        fos.write(string.getBytes());
                        fos.close();
                    }
                    catch(IOException io)
                    {

                    }
                    finally
                    {*/
                    String type = "admin";
                    Intent intent = new Intent(Login.this, MainMenu.class);
                    startActivity(intent);
                    //}
                }
                else
                    error.setText(e);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    // Before 2.0
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

