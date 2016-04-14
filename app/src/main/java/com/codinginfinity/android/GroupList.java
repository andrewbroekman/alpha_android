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
import java.io.FileOutputStream;
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
 * Group List
 * This activity would be used to retrieve and display a list of
 * Research groups that the current user is affiliated with.
 *
 * The activity contains a ListView to display the Groups, each
 * item in the ListView has aview button. An EditText is used to
 * search for a specific article. The Activity has a navigation drawer activity
 * that contains sorting and filtering options.
 */
public class GroupList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    //Temporary until I know what the object returned from the server looks like

    public class Group{
        String name;
        String fieldOfStudy;
        String leader ;

        public Group() {}

        public Group(String name, String fieldOfStudy, String leader) {
            this.name = name;
            this.fieldOfStudy = fieldOfStudy ;
            this.leader = leader ;
        }
    }

    private ArrayList<Group> items; //This list would contain all the objects returned by the server
    private ArrayList<Group> listItems=new ArrayList<Group>(); //This list contains only the objects to be displayed
    private MyListAdapter adapter;
    private ListView listView;
    private EditText editText;

    @Override
    /**
     * This method gets called when the activity is created.
     * @param savedInstanceState
     * @return nothing
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView)findViewById(R.id.group_listview);
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
     * This method is used to request all Groups from the json file, and
     * store it in an array of Groups.
     * It would be called once inside the onCreate method.
     * @return nothing
     */
    public void initList(){
        File file = new File(path + "/groups.json");
        if (!file.exists()) { // Generate fake json
            String groupsJsonString = "[" +
                    "{" +
                    "\"name\" : \"CIRG\"," +
                    "\"fieldOfStudy\" : \"Artifical Inteligence\"," +
                    "\"leader\" : \"Kimi.Raikkonen@gmail.com\"" +
                    "}," +
                    "{" +
                    "\"name\" : \"ICSA\"," +
                    "\"fieldOfStudy\" : \"Distributed Systems Security and Privacy\"," +
                    "\"leader\" : \"Felipe.Massa@gmail.com\"" +
                    "}," +
                    "{" +
                    "\"name\" : \"SSFM\"," +
                    "\"fieldOfStudy\" : \"System Specifications and Formal Methods\"," +
                    "\"leader\" : \"Fernando.Alonso@gmail.com\"" +
                    "}," +
                    "{" +
                    "\"name\" : \"SESAr\"," +
                    "\"fieldOfStudy\" : \"Software Engineering and Software Architecture\"," +
                    "\"leader\" : \"Jenson.Button@gmail.com\"" +
                    "}," +
                    "{" +
                    "\"name\" : \"CSEDAR\"," +
                    "\"fieldOfStudy\" : \"Education Didactics and Applications Research\"," +
                    "\"leader\" : \"Valteri.Bottas@gmail.com\"" +
                    "}," +
                    "{" +
                    "\"name\" : \"Sexy-Girl\"," +
                    "\"fieldOfStudy\" : \"Swag, dank memes and 360 noScopes\"," +
                    "\"leader\" : \"mark\"" +
                    "}" +
                    "]";
            Save(file, groupsJsonString);
        }

        //String jsonString = "[ {\"name\" : \"CIRG\",\"fieldOfStudy\" : \"Artifical Inteligence\",\"leader\" : \"1\"}, {\"name\" : \"ICSA\",\"fieldOfStudy\" : \"Distributed Systems Security and Privacy\",\"leader\" : \"2\"}, {\"name\" : \"SSFM\",\"fieldOfStudy\" : \"System Specifications and Formal Methods\",\"leader\" : \"3\"},{\"name\" : \"SESAr\",\"fieldOfStudy\" : \"Software Engineering and Software Architecture\",\"leader\" : \"4\"}, {\"name\" : \"CSEDAR\",\"fieldOfStudy\" : \"Education Didactics and Applications Research\",\"leader\" : \"5\"}, {\"name\" : \"SexyGurls\",\"fieldOfStudy\" : \"Swag\",\"leader\" : \"Snoop Dawgg\"} ]";
        String jsonString = Load(file) ;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            items = new ArrayList<Group>();

            for (int i =0; i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i); //Get each group from array

                
                items.add(new Group(jsonObject.getString("name"), jsonObject.getString("fieldOfStudy"), jsonObject.getString("leader")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to load all items inside the array of Groups into
     * an ArrayList, it would be called multiple times, when sorting searching or filtering the list.
     * This function would return the list to it's original state.
     * @return nothing
     */
    public void loadItems(){
        listItems=new ArrayList<>(items);
        adapter = new MyListAdapter(this, R.layout.list_item_group_list, listItems);
        listView.setAdapter(adapter);
    }

    /**
     * This method is used to search if any of the Group names, or feild of study contains the String that was
     * entered in the searchbox. If a particular group does not, it is removed from the ArrayList
     * of groups.
     * @param textToSearch
     * @return nothing
     */
    public void searchItem(String textToSearch){
        for (Group item:items){
            if(!(item.name.toLowerCase()).contains(textToSearch.toLowerCase())
            && !(item.fieldOfStudy.toLowerCase()).contains(textToSearch.toLowerCase())){
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

            Collections.sort(listItems, new Comparator<Group>() {
                @Override
                /**
                 * This method is called in the sort function, overriding it allows you to call
                 * sort on custom objects.
                 * @param group1
                 * @param group2
                 * @return int
                 */
                public int compare(Group group1, Group group2)
                {
                    return group1.name.compareTo(group2.name);
                }
            });
        }
        //Sort from Z - A was clicked.
        else if (id == R.id.nav_sort_alp_desc) {
            loadItems();

            Collections.sort(listItems, new Comparator<Group>() {
                @Override
                /**
                 * This method is called in the sort function, overriding it allows you to call
                 * sort on custom objects.
                 * @param group1
                 * @param group2
                 * @return int
                 */
                public int compare(Group group1, Group group2)
                {
                    return group2.name.compareTo(group1.name);
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
    private class MyListAdapter extends ArrayAdapter<Group>{
        private int layout;
        /**
         * The constructor for MyListAdapter, when called it would call the parent class constructor
         * and set a pointer to the custom layout file that would be used to populate the ListView.
         * @param context
         * @param resource
         * @param objects
         */
        private MyListAdapter(Context context, int resource, List<Group> objects) {
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
                viewholder.group_name = (TextView) convertView.findViewById(R.id.list_view_item_name_group_list);
                viewholder.view_btn = (ImageButton) convertView.findViewById(R.id.list_view_item_view_group_list);
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
                            Intent intent = new Intent(GroupList.this, viewResearchGroup.class);
                            intent.putExtra("group_name",getItem(position).name);
                            intent.putExtra("fieldOfStudy",getItem(position).fieldOfStudy);
                            startActivity(intent);

                        /*
                            //Retrieve variable in Viewgroup with
                            Bundle extras = getIntent().getExtras();
                            if (extras != null) {
                                String value = extras.getString("new_variable_name");
                            }
                        */

                    }
                });

            mainViewHolder.group_name.setText(getItem(position).name);

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
        TextView group_name;
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
    public static void Save(File file, String dataString)
    {
        String [] data = String.valueOf(dataString).split(System.getProperty("line.separator"));
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    fos.write(data[i].getBytes());
                    if (i < data.length-1)
                    {
                        fos.write("\n".getBytes());
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }

}

