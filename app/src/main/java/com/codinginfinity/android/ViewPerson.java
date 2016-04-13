package com.codinginfinity.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

/**
 * ViewPersons activity will retrive and display the current
 * users information and allow them to edit and save the new
 * infomation.
 *
 * The activity contains some un-enabled edit boxes that display the current
 * users details, and two buttons edit and view papers, once the
 * edit button is clicked it enables the edit boxes and changes the edit
 * button into a save button. When the save button is clicked it stores
 * the new values into variables and will update the database.
 * When the view papers button is clicked it will go to the view
 * papers activity
 *
 * @author Mark Klingenberg
 */
public class ViewPerson extends AppCompatActivity {

    @Override
    /**
     * This method gets called when the activity is created.
     * @param savedInstanceState
     * @return nothing
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);
    }
    private String Name ;
    private String Email ;
    private String ResearchGroup ;
    private Float UnitedEarned ;
    private String ContactD;
    /**
     * when this button is clicked it makes all the edit
     * boxes enabled and changes the button to a save button.
     * when the save button is clicked it stores the text in
     * the edit boxes into variable and update the database.
     *
     * @param   v   the edit buton
     */
    public void btnEditOnClick(View v)
    {
       Button editBtn = (Button) v ;
       Button viewBtn = (Button) findViewById(R.id.btnView);
        Button reportBtn = (Button) findViewById(R.id.btnRe);
        Button cancelBtn = (Button) findViewById(R.id.btnCancel);
        EditText name = (EditText) findViewById(R.id.edtName);
        EditText email = (EditText) findViewById(R.id.edtEmail);
        EditText contactD = (EditText) findViewById(R.id.edtCon);
        EditText unitedEarned = (EditText) findViewById(R.id.edtUnitsEarned);
        EditText researchGroup = (EditText) findViewById(R.id.edtReasearch);
        Name = name.getText().toString() ;
        Email = email.getText().toString() ;
        String s = unitedEarned.getText().toString() ;
        try
        {
            UnitedEarned = Float.parseFloat(s);
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(getApplicationContext(), s + " is not a valid number", Toast.LENGTH_SHORT).show() ;
        }
        ResearchGroup = researchGroup.getText().toString() ;
        ContactD = contactD.getText().toString();
       if (editBtn.getText().equals("Edit")) {
           name.setEnabled(true);
           email.setEnabled(true);
           researchGroup.setEnabled(true);
           contactD.setEnabled(true);
           editBtn.setText("save");
           cancelBtn.setEnabled(true);
           cancelBtn.setVisibility(View.VISIBLE);
           viewBtn.setEnabled(false);
           reportBtn.setEnabled(false);
           return ;
       }
       if(editBtn.getText().equals("save")){
           Name = name.getText().toString() ;
           Email = email.getText().toString() ;
           s = unitedEarned.getText().toString() ;
           try
           {
               UnitedEarned = Float.parseFloat(s);
           }
           catch (NumberFormatException e)
           {
               Toast.makeText(getApplicationContext(), s + " is not a valid number", Toast.LENGTH_SHORT).show() ;
           }
           ResearchGroup = researchGroup.getText().toString() ;
           ContactD = contactD.getText().toString() ;
           editBtn.setText("Edit");
           name.setEnabled(false);
           email.setEnabled(false);
           researchGroup.setEnabled(false);
           contactD.setEnabled(false);
           viewBtn.setEnabled(true);
           reportBtn.setEnabled(true);
           cancelBtn.setEnabled(false);
           cancelBtn.setVisibility(View.INVISIBLE);
           return ;
       }
    }
    /**
     * This function will cancl lany edited texts and restore them to their previous states
     * 
     * @param   v   the View
     */
    public void btnCancelOnClick(View v){
        Button cancelBtn = (Button) v ;
        Button viewBtn = (Button) findViewById(R.id.btnView);
        Button editBtn = (Button) findViewById(R.id.btnEdit);
        Button reBtn = (Button) findViewById(R.id.btnRe);
        EditText name = (EditText) findViewById(R.id.edtName);
        EditText email = (EditText) findViewById(R.id.edtEmail);
        EditText unitedEarned = (EditText) findViewById(R.id.edtUnitsEarned);
        EditText researchGroup = (EditText) findViewById(R.id.edtReasearch);
        EditText conD = (EditText) findViewById(R.id.edtCon);
        name.setText(Name);
        email.setText(Email);
        unitedEarned.setText(UnitedEarned.toString());
        researchGroup.setText(ResearchGroup);
        conD.setText(ContactD);
        editBtn.setText("Edit");
        name.setEnabled(false);
        email.setEnabled(false);
        unitedEarned.setEnabled(false);
        researchGroup.setEnabled(false);
        conD.setEnabled(false);
        viewBtn.setEnabled(true);
        reBtn.setEnabled(true);
        cancelBtn.setVisibility(View.INVISIBLE);
        cancelBtn.setEnabled(false);
        return ;
    }
    /**
     * This function simply opens the view papers activity
     *
     * @param v
     */
    public void btnViewOnClick(View v)
    {
        Intent intent = new Intent(this, ViewPublicationsActivity.class);
        startActivity(intent);
    }
    @Override
    /**
     * This method adds items to the action bar if it is present.
     * @return boolean
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_publications, menu);
        return true;
    }

    @Override  
    /**
     * This method handles action bar item clicks.
     * @return boolean
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Settings was clicked.
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnReportOnClick(View v)
    {
        Intent intent = new Intent(this, ViewReport.class);
        startActivity(intent);
    }



}
