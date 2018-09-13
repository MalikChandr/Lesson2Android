package com.lesson.mcm_pc.lesson3listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        // Create the adapter to convert the array to views
        adapter = new UserAdapter(this, arrayOfUsers);
        setData();

                            // Or even append an entire new collection
                            // Fetching some data, data has now returned
                            // If data was JSON, convert to ArrayList of User objects.
                            //JSONArray jsonArray = ...;
                            //ArrayList<User> newUsers = User.fromJson(jsonArray)
                            //adapter.addAll(newUsers);

        // Attach the adapter to a ListView
                ListView listView = findViewById(R.id.lvItems);
                listView.setAdapter(adapter);
    }

    public void setData(){
        // Add item to adapter
        User newUser1 = new User("Malik", "San Diaga");
        User newUser2 = new User("Alifhar", "San");
        User newUser3 = new User("Andrian", "San Uno");
        User newUser4 = new User("Ryan", "Diaga");
        User newUser5 = new User("Arief", "Uno");
        User newUser6 = new User("Vikri", "Diago");
        User newUser7 = new User("Khairil", "Cimahi");
        User newUser8 = new User("Henkur", "Cimandul");

        User newUser12 = new User("Malik", "San Diaga");
        User newUser22 = new User("Alifhar", "San");
        User newUser32 = new User("Andrian", "San Uno");
        User newUser42 = new User("Ryan", "Diaga");
        User newUser52 = new User("Arief", "Uno");
        User newUser62 = new User("Vikri", "Diago");
        User newUser72 = new User("Khairil", "Cimahi");
        User newUser82 = new User("Henkur", "Cimandul");
        adapter.add(newUser1);
        adapter.add(newUser2);
        adapter.add(newUser3);
        adapter.add(newUser4);
        adapter.add(newUser5);
        adapter.add(newUser6);
        adapter.add(newUser7);
        adapter.add(newUser8);

        adapter.add(newUser12);
        adapter.add(newUser22);
        adapter.add(newUser32);
        adapter.add(newUser42);
        adapter.add(newUser52);
        adapter.add(newUser62);
        adapter.add(newUser72);
        adapter.add(newUser82);
    }
}
