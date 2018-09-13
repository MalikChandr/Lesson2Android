package com.lesson.mcm_pc.lesson3listview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused,
        // otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(
                            R.layout.item_user,
                            parent,
                            false);
        }
        
        Button btnClick = convertView.findViewById(R.id.button2);
        btnClick.setTag(position);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                User user = getItem(position);

                Intent intent = new Intent(view.getContext(), SecondActivity.class);
                intent.putExtra("data", user.name);

                view.getContext().startActivity(intent);

            }
        });

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        tvName.setText(user.name);
        tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}
