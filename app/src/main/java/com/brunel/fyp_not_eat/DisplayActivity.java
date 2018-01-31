package com.brunel.fyp_not_eat;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayActivity extends AppCompatActivity {
    static ListView lv;
    EditText et;
    TextView t1;
    String A="";
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    String value;
    int c=0;
    String[] whatnottoeat;
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
                /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        String item = ((TextView) view).getText().toString();
                        System.out.println(item+"");
                    }
                });*/
    }
    public void btnclk() {
        if (c == 0) {
            c++;
            Scanner in = new Scanner(UserInputActivity.whatnottoeat[UserInputActivity.indexx]).useDelimiter("\\,");
            while (in.hasNext() == true) {
                String G = in.next();
                adapter.add(G+"");
            }
            adapter.notifyDataSetChanged();
        }
    }
}
