package com.brunel.fyp_not_eat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class UserInputActivity extends AppCompatActivity {
    static ListView lv;
    EditText et;static int indexx=0;
    static  String item="";
    TextView t1;
    String A="";
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    String value;
    int c=0;
    static String[] whatnottoeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        lv = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(UserInputActivity.this, android.R.layout.simple_list_item_1, arrayList);
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
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        item = ((TextView) view).getText().toString();
                        indexx=position;
                        Intent intent = new Intent(UserInputActivity.this, DisplayActivity.class);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
    public void btnclk(String result) {
        if (c == 0) {
            c++;
            int count = 0, num = 0;
            System.out.println(result);
            result = result.substring(1, result.length());
            for (int i = 0; i < result.length(); i++) {
                Scanner in = new Scanner(result).useDelimiter("\\§");
                while (in.hasNext() == true) {
                    String G = in.next();
                    count++;
                }
            }
            System.out.println(count);
            whatnottoeat = new String[count];
            for (int i = 0; i < result.length(); i++) {
                System.out.println(result);
                Scanner in = new Scanner(result).useDelimiter("\\§");
                while (in.hasNext() == true) {
                    String G = in.next();
                    System.out.println(G);
                    if (G.equalsIgnoreCase(null) == false) {
                        String itemm = G.substring(0, G.indexOf("="));
                        whatnottoeat[num++] = G.substring(G.indexOf("=") + 1, G.length());
                        adapter.add(itemm + "");
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}
