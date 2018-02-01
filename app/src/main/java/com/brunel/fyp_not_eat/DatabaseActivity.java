package com.brunel.fyp_not_eat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class DatabaseActivity extends AppCompatActivity {

    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_database);
    }



    public void updateCondition() throws IOException {

        try {

            FirebaseDatabase database_5 = FirebaseDatabase.getInstance();
            DatabaseReference myRef5 = database_5.getReference("Condition");
            URL url = new URL("http://www.prominergroup.com/nareen/Condition.txt");
            BufferedReader inn = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = inn.readLine()) != null) {
                if (line.length() != 0) {
                    Scanner in = new Scanner(line).useDelimiter("\\§");
                    int count = 0;
                    String disease = "", whatnottoeat = "";
                    while (in.hasNext() == true) {
                        String delimitedword = in.next();
                        count++;
                        if (count == 1) {
                            disease = delimitedword;
                        }
                        if (count == 2) {
                            whatnottoeat = delimitedword;
                        }
                    }
                    System.out.println("§" + disease + "  " + whatnottoeat);
                    myRef5.setValue("§" + disease+"");
                    myRef5.child("§" + disease + "").setValue("" + whatnottoeat);
                    myRef5.push();
                }
            }
            inn.close();
        } catch (Exception e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }


    public void updateFactor()throws IOException
    {
        try {

            FirebaseDatabase database_5 = FirebaseDatabase.getInstance();
            DatabaseReference myRef5 = database_5.getReference("Factor");
            URL url = new URL("http://www.prominergroup.com/nareen/Factor.txt");
            BufferedReader inn = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = inn.readLine()) != null) {
                if (line.length() != 0) {
                    Scanner in = new Scanner(line).useDelimiter("\\§");
                    int count = 0;
                    String disease = "", whatnottoeat = "";
                    while (in.hasNext() == true) {
                        String delimitedword = in.next();
                        count++;
                        if (count == 1) {
                            disease = delimitedword;
                        }
                        if (count == 2) {
                            whatnottoeat = delimitedword;
                        }
                    }
                    myRef5.setValue("§" + disease+"");
                    myRef5.child("§" + disease + "").setValue("" + whatnottoeat);
                    myRef5.push();
                }
            }
            inn.close();
        } catch (Exception e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }


    public  void sendPostRequest(View View) {
        new DatabaseActivity.PostClass(this).execute();
    }
    private class PostClass extends AsyncTask<String, Void, Void> {
        private final Context context;
        public PostClass(Context c){
            this.context = c;
        }
        protected void onPreExecute(){
            progress= new ProgressDialog(this.context);
            progress.setMessage("Loading");
            progress.show();
        }
        @Override
        protected Void doInBackground(String... params) {
        try {
            updateCondition();
            updateFactor();
        }catch (Exception re){}



            DatabaseActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(DatabaseActivity.this, UserInputActivity.class);
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
