package com.boylab.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ThreadSwitch().onSwitch(new ThreadSwitch.ThreadListener() {
            @Override
            public void onBackground() {

            }

            @Override
            public void onPostResult() {

            }
        });






    }
}
