package com.codinginfinity.android;
//package com.example.jocelyn.editpublication;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditPublication extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication);

    }
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

    /*public void nextActivity(View v)
    {
        Intent nextActivity = new Intent();
        nextActivity.setClass(this, PublicationDetails.class);
        startActivity(nextActivity);
    }*/

}
