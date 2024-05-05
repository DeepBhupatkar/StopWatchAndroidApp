package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar1;
    TextView result;
    Button start, stop, reset;
    int seconds = 0;
    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.BLACK);
        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);

        result = findViewById(R.id.result);

        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        reset = findViewById(R.id.reset);

        startTimer();

        start.setOnClickListener(v -> {
            isRunning = true;
        });

        stop.setOnClickListener(v -> {
            isRunning = false;
        });

        reset.setOnClickListener(v -> {
            isRunning = false;
            seconds = 0;
        });

    }

    public void startTimer() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int s = seconds % 60;
                int m = seconds / 60;
                int h = m / 60;

                if(isRunning)
                    seconds++;

                String formatString = String.format(Locale.getDefault(),"%02d:%02d:%02d", h,m,s);
                result.setText(formatString);
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.closeapp) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}