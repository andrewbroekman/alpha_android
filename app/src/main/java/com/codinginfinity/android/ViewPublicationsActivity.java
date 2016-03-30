package com.codinginfinity.android;
//Ruan Klinkert - 14022282
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPublicationsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String[] items;
    private ArrayList<String> listItems=new ArrayList<String>();
    private MyListAdapter adapter;
    private ListView listView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_publications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView)findViewById(R.id.publications_listview);
        editText = (EditText)findViewById(R.id.search_bar);
        initList();
        loadItems();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            //Not required
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadItems();
                searchItem(s.toString());
            }

            @Override
            //Not required
            public void afterTextChanged(Editable s) {}
        });
    }
    public void initList(){
        //Load publications from server
        items=new String[]{"Canada","China","Japan","USA","South-Africa"};
    }

    public void loadItems(){
        //load publications into list view
        listItems=new ArrayList<>(Arrays.asList(items));
        adapter = new MyListAdapter(this, R.layout.list_item_view_publications, listItems);
        listView.setAdapter(adapter);
    }

    public void searchItem(String textToSearch){
        for (String item:items){
            //Search if any items starts with entered text - case insensitive as both entered text and items text gets set to lower case
            if(!(item.toLowerCase()).contains(textToSearch.toLowerCase())){
                listItems.remove(item);
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_publications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MyListAdapter extends ArrayAdapter<String>{
        private int layout;
        private MyListAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewholder = new ViewHolder();
                viewholder.publication_name = (TextView) convertView.findViewById(R.id.list_view_item_name_view_publication);
                viewholder.view_btn = (ImageButton) convertView.findViewById(R.id.list_view_item_view_view_publication);
                viewholder.edit_btn = (ImageButton) convertView.findViewById(R.id.list_view_item_edit_view_publication);
                convertView.setTag(viewholder);
            }
            mainViewHolder = (ViewHolder) convertView.getTag();


            mainViewHolder.view_btn.setOnClickListener(new View.OnClickListener(){
                    //View button clicked
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "View button was clicked for item " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            mainViewHolder.edit_btn.setOnClickListener(new View.OnClickListener() {
                //View button clicked
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Edit button was clicked for item " + position, Toast.LENGTH_SHORT).show();
                    }
                });

            mainViewHolder.publication_name.setText(getItem(position));


            return convertView;
        }
    }
    public class ViewHolder{
        TextView publication_name;
        ImageButton view_btn;
        ImageButton edit_btn;
    }
}
