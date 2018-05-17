package com.example.chris.rolli;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goButton = findViewById(R.id.goBtn);
        Button stopButton = findViewById(R.id.stopBtn);
        Button plusButton = findViewById(R.id.plusBtn);
        Button minusButton = findViewById(R.id.minusBtn);

        goButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBtn:
                //send Go Message via Bluetooth
                Toast.makeText(this, "GoButton clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.stopBtn:
                //send Go Message via Bluetooth
                Toast.makeText(this, "StopButton clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.plusBtn:
                Toast.makeText(this, "PlusButton clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.minusBtn:
                Toast.makeText(this,"MinusButton clicked",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
