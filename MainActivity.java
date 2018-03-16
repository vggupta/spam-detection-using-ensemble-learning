package com.example.aky.spamdetector;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,R.string.text1,Toast.LENGTH_SHORT).show();
        new Thread()
        {

            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    startActivity(new Intent(MainActivity.this,LOGINPAGE.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
