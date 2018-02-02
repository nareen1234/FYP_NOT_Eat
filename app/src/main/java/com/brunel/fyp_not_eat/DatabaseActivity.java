package com.brunel.fyp_not_eat;
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
    public void updateCondition()throws IOException {
        FirebaseDatabase database_5 = FirebaseDatabase.getInstance();
        DatabaseReference myRef5 = database_5.getReference("Condition");
        URL url = new URL("http://www.prominergroup.com/nareen/Condition.txt");
        URL url1 = new URL("http://www.prominergroup.com/nareen/Condition1.txt");
        BufferedReader inn = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedReader inn1 = new BufferedReader(new InputStreamReader(url1.openStream()));
        String line,line1;
        while ((line = inn.readLine()) != null && (line1 = inn1.readLine()) != null) {
            if (line.length() != 0 && line1.length()!=0) {
                myRef5.child("§"+line1).setValue(""+line);
            }
        }
        inn.close();
    }
    public void updateFactor()throws IOException {
        FirebaseDatabase database_5 = FirebaseDatabase.getInstance();
        DatabaseReference myRef5 = database_5.getReference("Factor");
        URL url = new URL("http://www.prominergroup.com/nareen/Factor.txt");
        URL url1 = new URL("http://www.prominergroup.com/nareen/Factor1.txt");
        BufferedReader inn = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedReader inn1 = new BufferedReader(new InputStreamReader(url1.openStream()));
        String line,line1;
        while ((line = inn.readLine()) != null && (line1 = inn1.readLine()) != null) {
            if (line.length() != 0 && line1.length()!=0) {
                myRef5.child("§"+line1).setValue(""+line);
            }
        }
        inn.close();
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
                    finish();
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
