package com.brunel.fyp_not_eat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayActivity extends AppCompatActivity {
    static ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display);
        lv = (ListView) findViewById(R.id.listview);
        lv.setSelector( R.drawable.selector);
        arrayList = new ArrayList<String>();
        lv.setSelector( R.drawable.selector);
        adapter = new ArrayAdapter<String>(DisplayActivity.this, android.R.layout.simple_list_item_1, arrayList);
        lv.setSelector( R.drawable.selector);
        lv.setAdapter(adapter);
        btnclk();
        lv.setAdapter(adapter);
    }
    public void btnclk() {
        if (c == 0) {
            c++;
            Scanner in = new Scanner(HomeActivity.whatnottoeat[UserInputActivity.indexx]).useDelimiter("\\,");
            while (in.hasNext() == true) {
                String G = in.next();
                adapter.add(G+"");
            }
            adapter.notifyDataSetChanged();
        }
    }
}
