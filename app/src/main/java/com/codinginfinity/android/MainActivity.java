package com.codinginfinity.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String username;
    @Override
    /**
     * This method gets called when the activity is created.
     * @param savedInstanceState
     * @return nothing
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            String user = "Admin";
            username = getIntent().getExtras().getString("User");
            if (user.equals("Admin"))
                setContentView(R.layout.activity_main_admin);
            else
                setContentView(R.layout.activity_main_user);

            showDate();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }
        catch (Exception e)
        {

        }

    }


    public void showDate()
    {
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("EEEE, d MMMM yyyy");
            String formattedDate = df.format(c.getTime());

            TextView headerValue = (TextView) findViewById(R.id.textView4);
            headerValue.setText("Today - " + formattedDate);
        }
        catch (Exception e) { }


    }
    public void viewPapersFunc(View v){
        Intent intent = new Intent(this, ViewPublicationsActivity.class);
        intent.putExtra("User",username);
        startActivity(intent);
    }
    public void viewPapersFunc(){
        Intent intent = new Intent(this, ViewPublicationsActivity.class);
        intent.putExtra("User",username);
        startActivity(intent);
    }

    public void addPaperFunc(View v)
    {
        boolean userpriv = false;
        if(userpriv) {
            Intent intent = new Intent(this, AddPublicationAsSuper.class);
            intent.putExtra("User",username);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, AddPublication.class);
            intent.putExtra("User",username);
            startActivity(intent);
        }
    }
    public void addPaperFunc(){
        Intent intent = new Intent(this, addPapersActivity.class);
        intent.putExtra("User",username);
        startActivity(intent);
    }

    public void editUserFunc(View v){
        Intent intent = new Intent(this, ViewPerson.class);
        intent.putExtra("User",username);
        startActivity(intent);
    }
    public void editUserFunc(){
        Intent intent = new Intent(this, editUserActivity.class);
        intent.putExtra("User",username);
        startActivity(intent);
    }
    
    public void addUserFunc(View v)
    {
        Intent intent = new Intent(this, AddUser.class);
        intent.putExtra("User",username);
        startActivity(intent);
    }
    
    public void groupsFunc(View v)
    {
        Intent intent = new Intent(this, GroupList.class);
        intent.putExtra("User",username);
        startActivity(intent);
    }

    public void addGroupFunc(View v)
    {
        Intent intent = new Intent(this, addGroup.class);
        intent.putExtra("User",username);
        startActivity(intent);
    }

    @Override
    /**
     * This method is used called when the back button is pressed. If the navigation drawer is open,
     * it would close it, else it would call the parents onBackPressed method.
     * @return nothing
     */
    public void onBackPressed() {
        try {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
        catch (Exception e) { }
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

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_Logout)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
   /* @Override
    *
     * This method handles navigation view item clicks.
     * @param item
     * @return boolean*/

    public boolean onNavigationItemSelected(MenuItem item) {
        /*try {
            int id = item.getItemId();

            //Sort from A - Z was clicked.
            if (id == R.id.nav_add_papers) {
                addPaperFunc();
            }
            //Sort from Z - A was clicked.
            else if (id == R.id.nav_view_publication) {
                viewPapersFunc();
            } else if (id == R.id.nav_view_user) {
                editUserFunc();
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }*/
        return false;
    }
}
