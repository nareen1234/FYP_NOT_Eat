package com.brunel.fyp_not_eat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class UserInputActivity extends AppCompatActivity {
    static ListView lv;
    static int indexx=0;
    static  String item="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_input);
        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(HomeActivity.adapter);
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
}
















