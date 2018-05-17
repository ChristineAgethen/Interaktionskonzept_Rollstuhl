package com.example.chris.rolliapp;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goButton = findViewById(R.id.go);
        Button stopButton = findViewById(R.id.stop);
        Button plusButton = findViewById(R.id.plus);
        Button minusButton = findViewById(R.id.minus);

        goButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.go:
                        //send Go Message via Bluetooth
                        Toast.makeText(this, "GoButton clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.stop:
                        //send Go Message via Bluetooth
                        Toast.makeText(this, "StopButton clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.plus:
                        //send Go Message via Bluetooth
                        Toast.makeText(this, "PlusButton clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.minus:
                        //send Go Message via Bluetooth
                        Toast.makeText(this, "MinusButton clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            // Example of a call to a native method
            //TextView tv = (TextView) findViewById(R.id.sample_text);
            //tv.setText(stringFromJNI())
            //}

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }

            /**
             * A native method that is implemented by the 'native-lib' native library,
             * which is packaged with this application.
             */
            public native String stringFromJNI();
        }
    }
}