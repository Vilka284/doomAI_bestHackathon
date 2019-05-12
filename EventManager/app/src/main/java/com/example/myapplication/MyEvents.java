package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        TextView text = findViewById(R.id.textView);
        text.setText("Best hackahton");
        TextView text2 = findViewById(R.id.textView2);
        text2.setText("hackahton");
    }
}
