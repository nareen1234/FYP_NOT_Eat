package com.brunel.fyp_not_eat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplayHealthActivity extends AppCompatActivity {
    static ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_health);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_health);
        lv = (ListView) findViewById(R.id.listview);
        lv.setSelector( R.drawable.selector);
        arrayList = new ArrayList<String>();
        lv.setSelector( R.drawable.selector);
        adapter = new ArrayAdapter<String>(DisplayHealthActivity.this, android.R.layout.simple_list_item_1, arrayList);
        lv.setSelector( R.drawable.selector);
        lv.setAdapter(adapter);
        btnclk();
        lv.setAdapter(adapter);
    }
    public void btnclk() {
        if (c == 0) {
            c++;
            Scanner in = new Scanner(UserInputHealthActivity.whatnottoeat[UserInputHealthActivity.indexx]).useDelimiter("\\,");
            while (in.hasNext() == true) {
                String G = in.next();
                adapter.add(G+"");
            }
            adapter.notifyDataSetChanged();
        }
    }
}
