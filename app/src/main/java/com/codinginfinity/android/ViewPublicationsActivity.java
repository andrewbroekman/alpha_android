package com.codinginfinity.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

/**
 * ViewPublicationsActivity
 * This activity would be used to retrieve and display a list of
 * publications that the current user is affiliated with.
 *
 * The activity contains a ListView to display the publications, each
 * item in the ListView has an edit and view button. An EditText is used to
 * search for a specific article. The Activity has a navigation drawer activity
 * that contains sorting and filtering options.
 *
 *
 * @author  Ruan Klinkert - 14022282
 * @version 1.0
 * @since   2016-03-31
 */
public class ViewPublicationsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();

    //Temporary until I know what the object returned from the server looks like
    public class Publication{
        String name;
        String owner;
        String type;
        String state;
        String url;
        Date date;

        public Publication() {}

        public Publication(String name, String owner, String type, String state, String url, Date date) {
            this.name = name;
            this.date = date;
            this.state = state;
            this.owner = owner;
            this.type = type;
            this.url = url;
        }
    }

    private ArrayList<Publication> items; //This list would contain all the objects returned by the server
    private ArrayList<Publication> listItems=new ArrayList<Publication>(); //This list contains only the objects to be displayed
    private MyListAdapter adapter;
    private ListView listView;
    private EditText editText;
    private String username;

    @Override
    /**
     * This method gets called when the activity is created.
     * @param savedInstanceState
     * @return nothing
     */
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

        username = getIntent().getExtras().getString("User");

        listView = (ListView)findViewById(R.id.publications_listview);
        editText = (EditText)findViewById(R.id.search_bar);
        initList();
        loadItems();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            //Not required
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            /**
             * This method gets called when the EditText's text is changed.
             * It is used to call the searchItem function.
             * @param s the current CharSequence in the EditText
             * @param start
             * @param before
             * @param count
             * @return nothing
             */
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadItems();
                searchItem(s.toString());
            }

            @Override
            //Not required
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * This method is used to request all publications from the server via a REST request, and
     * store it in an array of publications.
     * It would be called once inside the onCreate method.
     * @return nothing
     */
    public void initList(){
        File file = new File(path + "/people.json");
        String jsonString = Load(file);

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            items = new ArrayList<Publication>();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date d;

            for (int i =0; i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each publication from array

                if (jsonObject.getString("name").compareTo(username) == 0) {
                    JSONArray jsonPubArray = jsonObject.getJSONArray("publications");

                    for (int k = 0; k <jsonPubArray.length();k++) {
                        JSONObject jsonPub = jsonPubArray.getJSONObject(k);

                        d = null;
                        try {
                            d = format.parse(jsonPub.getString("startdate")); //Create date
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        items.add(new Publication(jsonPub.getString("name"),
                                        jsonPub.getString("owner"),
                                        jsonPub.getString("type"),
                                        jsonPub.getString("state"),
                                        jsonPub.getString("url"),
                                        d
                                )
                        );
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to load all items inside the array of publications into
     * an ArrayList, it would be called multiple times, when sorting searching or filtering the list.
     * This function would return the list to it's original state.
     * @return nothing
     */
    public void loadItems(){
        listItems=new ArrayList<>(items);
        adapter = new MyListAdapter(this, R.layout.list_item_view_publications, listItems);
        listView.setAdapter(adapter);
    }

    /**
     * This method is used to search if any of the publications' names,status or researchGroup name contains the String that was
     * entered in the searchbox. If a particular pulication does not, it is removed from the ArrayList
     * of publications.
     * @param textToSearch
     * @return nothing
     */
    public void searchItem(String textToSearch){
        for (Publication item:items){
            if(!(item.name.toLowerCase()).contains(textToSearch.toLowerCase())
            && !(item.owner.toLowerCase()).contains(textToSearch.toLowerCase())
            && !(item.type.toLowerCase()).contains(textToSearch.toLowerCase())
            && !(item.state.toLowerCase()).contains(textToSearch.toLowerCase())
            && !(item.url.toLowerCase()).contains(textToSearch.toLowerCase())){
                listItems.remove(item);
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    /**
     * This method is used called when the back button is pressed. If the navigation drawer is open,
     * it would close it, else it would call the parents onBackPressed method.
     * @return nothing
     */
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    /**
     * This method adds items to the action bar if it is present.
     * @return boolean
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addpublications, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    /**
     * This method handles navigation drawer item clicks.
     * @param item
     * @return boolean
     */
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Sort from A - Z was clicked.
        if (id == R.id.nav_sort_alp_asc) {
            loadItems();

            Collections.sort(listItems, new Comparator<Publication>() {
                @Override
                /**
                 * This method is called in the sort function, overriding it allows you to call
                 * sort on custom objects.
                 * @param publication1
                 * @param publication2
                 * @return int
                 */
                public int compare(Publication publication1, Publication publication2)
                {
                    return publication1.name.compareTo(publication2.name);
                }
            });
        }
        //Sort from Z - A was clicked.
        else if (id == R.id.nav_sort_alp_desc) {
            loadItems();

            Collections.sort(listItems, new Comparator<Publication>() {
                @Override
                /**
                 * This method is called in the sort function, overriding it allows you to call
                 * sort on custom objects.
                 * @param publication1
                 * @param publication2
                 * @return int
                 */
                public int compare(Publication publication1, Publication publication2)
                {
                    return publication2.name.compareTo(publication1.name);
                }
            });
        }
        //Sort newest to oldest was clicked.
        else if (id == R.id.nav_sort_date_asc) {
            loadItems();

            Collections.sort(listItems, new Comparator<Publication>() {
                @Override
                /**
                 * This method is called in the sort function, overriding it allows you to call
                 * sort on custom objects.
                 * @param publication1
                 * @param publication2
                 * @return int
                 */
                public int compare(Publication publication1, Publication publication2)
                {
                    return publication1.date.compareTo(publication2.date);
                }
            });
        }
        //Sort oldest to newest was clicked.
        else if (id == R.id.nav_sort_date_desc) {
            loadItems();

            Collections.sort(listItems, new Comparator<Publication>() {
                @Override
                /**
                 * This method is called in the sort function, overriding it allows you to call
                 * sort on custom objects.
                 * @param publication1
                 * @param publication2
                 * @return int
                 */
                public int compare(Publication publication1, Publication publication2)
                {
                    return publication2.date.compareTo(publication1.date);
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * MyListAdapter
     * This custom ArrayAdapter class is used to load custom list items containing clickable edit and
     * view ImageButtons, into the ListView.
     * @author Ruan
     */
    private class MyListAdapter extends ArrayAdapter<Publication>{
        private int layout;
        /**
         * The constructor for MyListAdapter, when called it would call the parent class constructor
         * and set a pointer to the custom layout file that would be used to populate the ListView.
         * @param context
         * @param resource
         * @param objects
         */
        private MyListAdapter(Context context, int resource, List<Publication> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @Override
        /**
         * @param position
         * @param convertView
         * @param parent
         * @return View
         */
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewholder = new ViewHolder();
                viewholder.publication_name = (TextView) convertView.findViewById(R.id.list_view_item_name_view_publication);
                viewholder.view_btn = (ImageButton) convertView.findViewById(R.id.list_view_item_view_view_publication);
                convertView.setTag(viewholder);
            }
            mainViewHolder = (ViewHolder) convertView.getTag();


            mainViewHolder.view_btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    /**
                     * This method is called when the view button for an item is clicked.
                     * @param v reference to object calling the function
                     * @return void
                     */
                    public void onClick(View v) {
                            Intent intent = new Intent(ViewPublicationsActivity.this, EditPublication.class);
                            intent.putExtra("pulication_name",getItem(position).name);
                            intent.putExtra("user_name",username);
                            startActivity(intent);

                    }
                });

            mainViewHolder.publication_name.setText(getItem(position).name);

            return convertView;
        }
    }
    /**
     * ViewHolder
     * This class is used to store a reference to the ImageButtons and TextView of each item, to reduce the
     * number of times it has to be inflated.
     * @author Ruan
     */
    public class ViewHolder{
        TextView publication_name;
        ImageButton view_btn;
    }

    public static String Load(File file)
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl=0;
        try
        {
            while ((test=br.readLine()) != null)
            {
                anzahl++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        try
        {
            fis.getChannel().position(0);
        }
        catch (IOException e) {e.printStackTrace();}

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try
        {
            while((line=br.readLine())!=null)
            {
                array[i] = line;
                i++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        String returnString = "";
        for (int k = 0; k < array.length; k++)
        {
            returnString += array[k];
        }

        return returnString;
    }
}
