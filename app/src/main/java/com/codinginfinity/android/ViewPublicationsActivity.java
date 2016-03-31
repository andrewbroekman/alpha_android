package com.codinginfinity.android;

import android.content.Context;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <h1>ViewPublicationsActivity.class</h1>
 * This activity would be used to retrieve and display a list of
 * publications that the current user is affiliated with.
 * <p>
 *     The activity contains a ListView to display the publications, each
 *     item in the ListView has an edit and view button. An EditText is used to
 *     search for a specific article. The Activity has a navigation drawer activity
 *     that contains sorting and filtering options.
 * </p>
 *
 * @author  Ruan Klinkert - 14022282
 * @version 1.0
 * @since   2016-03-31
 */
public class ViewPublicationsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String[] items;
    private ArrayList<String> listItems=new ArrayList<String>();
    private MyListAdapter adapter;
    private ListView listView;
    private EditText editText;

    @Override
    /**
     * This method gets called when the activity is created.
     * @param Bundle savedInstanceState
     * @return void
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

        listView = (ListView)findViewById(R.id.publications_listview);
        editText = (EditText)findViewById(R.id.search_bar);
        initList();
        loadItems();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            //Not required
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            /**
             * This method gets called when the EditText's text is changed.
             * It is used to call the searchItem function.
             * @param s the current CharSequence in the EditText
             * @param start
             * @param before
             * @param count
             * @return void
             */
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadItems();
                searchItem(s.toString());
            }

            @Override
            //Not required
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * This method is used to request all publications from the server via a REST request, and
     * store it in an array of publications.
     * It would be called once inside the onCreate method.
     * @return void
     */
    public void initList(){
        items=new String[]{"Canada","China","Japan","USA","South-Africa"};
    }

    /**
     * This method is used to load all items inside the array of publications into
     * an ArrayList, it would be called multiple times, when sorting searching or filtering the list.
     * This function would return the list to it's original state.
     * @return void
     */
    public void loadItems(){
        listItems=new ArrayList<>(Arrays.asList(items));
        adapter = new MyListAdapter(this, R.layout.list_item_view_publications, listItems);
        listView.setAdapter(adapter);
    }

    /**
     * This method is used to search if any of the publications' names contains the String that was
     * entered in the searchbox. If a particular pulication does not, it is removed from the ArrayList
     * of publications.
     * @param textToSearch
     * @return void
     */
    public void searchItem(String textToSearch){
        for (String item:items){
            if(!(item.toLowerCase()).contains(textToSearch.toLowerCase())){
                listItems.remove(item);
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    /**
     * This method is used called when the back button is pressed. If the navigation drawer is open,
     * it would close it, else it would call the parents onBackPressed method.
     * @return void
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    /**
     * This method handles navigation view item clicks.
     * @param item
     * @return boolean
     */
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Sort from A - Z was clicked.
        if (id == R.id.nav_sort_alp_asc) {
            loadItems();

            Collections.sort(listItems, new Comparator<String>() {
                @Override
                /**
                 * This method is called in the sort function, overriding it allows you to call
                 * sort on custom objects.
                 * @param publication1
                 * @param publication2
                 * @return int
                 */
                public int compare(String publication1, String publication2)
                {
                    return publication1.compareTo(publication2);
                }
            });
        }
        //Sort from Z - A was clicked.
        else if (id == R.id.nav_sort_alp_desc) {
            loadItems();

            Collections.sort(listItems, new Comparator<String>() {
                @Override
                /**
                 * This method is called in the sort function, overriding it allows you to call
                 * sort on custom objects.
                 * @param publication1
                 * @param publication2
                 * @return int
                 */
                public int compare(String publication1, String publication2)
                {
                    return publication2.compareTo(publication1);
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * <h1>MyListAdapter<h1/>
     * This custom ArrayAdapter class is used to load custom list items containing clickable edit and
     * view ImageButtons, into the ListView.
     * @author Ruan
     */
    private class MyListAdapter extends ArrayAdapter<String>{
        private int layout;
        /**
         * The constructor for MyListAdapter, when called it would call the parent class constructor
         * and set a pointer to the custom layout file that would be used to populate the ListView.
         * @param context
         * @param resource
         * @param objects
         */
        private MyListAdapter(Context context, int resource, List<String> objects) {
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
                viewholder.edit_btn = (ImageButton) convertView.findViewById(R.id.list_view_item_edit_view_publication);
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
                        Toast.makeText(getContext(), "View button was clicked for item " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            mainViewHolder.edit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    /**
                     * This method is called when the edit button for an item is clicked.
                     * @param v reference to object calling the function
                     * @return void
                    */
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Edit button was clicked for item " + position, Toast.LENGTH_SHORT).show();
                    }
                });

            mainViewHolder.publication_name.setText(getItem(position));

            return convertView;
        }
    }
    /**
     * <h1>ViewHolder<h1/>
     * This class is used to store a reference to the ImageButtons and TextView of each item, to reduce the
     * number of times it has to be inflated.
     * @author Ruan
     */
    public class ViewHolder{
        TextView publication_name;
        ImageButton view_btn;
        ImageButton edit_btn;
    }
}
