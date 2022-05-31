package com.example.womenssafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ComplaintShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_show);
        TextView Display = findViewById(R.id.display);
        Bundle bn= getIntent().getExtras();
        String name = bn.getString("ABC");
        Display.setText(String.valueOf(name));
    }
}