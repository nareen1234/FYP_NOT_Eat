package com.brunel.fyp_not_eat;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, UserInputActivity.class);
        startActivity(intent);
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
}
