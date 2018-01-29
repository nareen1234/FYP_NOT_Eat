package com.brunel.fyp_not_eat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserInputActivity extends AppCompatActivity {
    static ListView lv;
    EditText et;
    TextView t1;
    String A="";
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        lv = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(UserInputActivity.this, android.R.layout.simple_list_item_1, arrayList);
        t1.setText("Here1");
        lv.setAdapter(adapter);
        FirebaseDatabase database_b = FirebaseDatabase.getInstance();
        final DatabaseReference myRefb = database_b.getReference();
        myRefb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue().toString();
                btnclk(value);
                t1.setText("Add Players");
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        String item = ((TextView) view).getText().toString();

                            item = item.trim();
                            item = item.substring(0, item.indexOf("§"));
                            item = item.trim();
                            String A = item.substring(item.indexOf("=") + 1, item.length());
                            item = item.trim();
                            String B = item.substring(0, item.indexOf("="));
                            FirebaseDatabase database_b = FirebaseDatabase.getInstance();
                            final DatabaseReference myRefb = database_b.getReference("button");
                            A = A.trim();
                            B = B.trim();
                            myRefb.setValue("");
                            myRefb.push().setValue("§" + B + "§" + A + "§");

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
    public void btnclk(String result)
    {
        adapter.clear();
        result=result.substring(1,result.length());
        Scanner in=new Scanner(result).useDelimiter("\\,");
        while (in.hasNext()==true)
        {
            String G = in.next();
            adapter.add(G);
            adapter.notifyDataSetChanged();
        }
        //adapter.notifyDataSetChanged();
    }
}
