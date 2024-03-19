package com.example.midterms_colormatching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnColorMatching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnColorMatching = findViewById(R.id.btnColorMatching);
        btnColorMatching.setOnClickListener(v -> {
            String toastMessage = "FULL NAME" + "\n" + "        ColorMatching Activity";
            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, ColorMatching.class));
        });


    }
}