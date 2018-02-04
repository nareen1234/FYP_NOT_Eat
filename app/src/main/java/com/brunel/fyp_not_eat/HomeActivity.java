package com.brunel.fyp_not_eat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Scanner;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    static  String result="";
    static int num=0,lengthh=0;
    static ArrayList<String> arrayList;
    static ArrayAdapter<String> adapter;
    String value;
    static String[] whatnottoeat;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_1, arrayList);
        FirebaseDatabase database_b = FirebaseDatabase.getInstance();
        final DatabaseReference myRefb = database_b.getReference("Condition");
        myRefb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    value = dataSnapshot.getValue().toString();
                    result=value;
                    lengthh = 0;
                    for (int i = 0; i < result.length(); i++) {
                        char c = result.charAt(i);
                        if (c == '§') {
                            lengthh++;
                        }
                    }
                    num = 0;
                    result = result.substring(1, result.length());
                    whatnottoeat = new String[lengthh];
                }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
    @Override
    public void onClick(View v) {
        new HomeActivity.PostClass(this).execute();
    }
    public void onClick2(View v) {
        String androidDeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if(androidDeviceId.equalsIgnoreCase("f8eac382d2bfcf29"))        {
            Intent intent = new Intent(this, DatabaseActivity.class);
            startActivity(intent);
        }
    }
    public void onClick1(View v) {
        Intent intent = new Intent(this, UserInputHealthActivity.class);
        startActivity(intent);
    }


    private class PostClass extends AsyncTask<String, Void, Void> {
        private final Context context;

        public PostClass(Context c) {
            this.context = c;
        }

        protected void onPreExecute() {
            progress = new ProgressDialog(this.context);
            progress.setMessage("Loading");
            progress.show();
        }

        @Override
        protected Void doInBackground(String... params) {

                Scanner in = new Scanner(result).useDelimiter("\\§");
                while (in.hasNext() == true) {
                    String G = in.next();
                    G = G.trim();
                    if (G.length() != 0) {
                        String itemm = G.substring(0, G.indexOf("="));
                        if (num < lengthh) {
                            whatnottoeat[num++] = G.substring(G.indexOf("=") + 1, G.length() - 1);
                            adapter.add(itemm + "");
                        }
                    }
                }
            adapter.notifyDataSetChanged();
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(HomeActivity.this, UserInputActivity.class);
                    startActivity(intent);

                    progress.dismiss();

                }
            });
            return null;
        }
        protected void onPostExecute() {
            progress.dismiss();
        }
    }
}
