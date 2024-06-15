package com.example.jbcalci;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinalBill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_bill);

        // Unopening intent shared material from mainscreen or execution screen
        // Getting Bill and show it for final output
        Intent intent = getIntent();
        String bill = intent.getStringExtra(MainScreen.BILL);
        TextView textView = findViewById(R.id.textView100);
        textView.setText(bill);
    }
}