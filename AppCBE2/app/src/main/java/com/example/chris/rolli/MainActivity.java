package com.example.chris.rolli;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.os.Handler;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.UUID;

// import com.example.bluetooth1.R;
// import com.example.bluetooth2.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    final int RECIEVE_MESSAGE = 1;		// Status  for Handler
    private StringBuilder sb = new StringBuilder();
    private ConnectedThread mConnectedThread;

    Button goButton
    Button stopButton
    Button plusButton
    Button minusButton

    TextView txtArduino;
    Handler h;



    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // MAC-address of Bluetooth module (you must edit this line)
    private static String address = "00:15:FF:F2:19:5F";

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goBtn);
        stopButton = findViewById(R.id.stopBtn);
        plusButton = findViewById(R.id.plusBtn);
        minusButton = findViewById(R.id.minusBtn);

        goButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);

        txtArduino = (TextView) findViewById(R.id.txtArduino);		// for display the received data from the Arduino

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case RECIEVE_MESSAGE:													// if receive massage
                        byte[] readBuf = (byte[]) msg.obj;
                        String strIncom = new String(readBuf, 0, msg.arg1);					// create string from bytes array
                        sb.append(strIncom);												// append string
                        int endOfLineIndex = sb.indexOf("\r\n");							// determine the end-of-line
                        if (endOfLineIndex > 0) { 											// if end-of-line,
                            String sbprint = sb.substring(0, endOfLineIndex);				// extract string
                            sb.delete(0, sb.length());										// and clear
                            txtArduino.setText("Data from Arduino: " + sbprint); 	        // update TextView
                            goButton.setEnabled(true);
                            stopButton.setEnabled(true);
                            plusButton.setEnabled(true);
                            minusButton.setEnabled(true);
                        }
                        //Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");
                        break;
                }
            };
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter();		// get Bluetooth adapter
        checkBTState();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBtn:
                //send Go Message via Bluetooth
                Toast.makeText(this, "GoButton clicked", Toast.LENGTH_SHORT).show();
                goButton.setEnabled(false);
                mConnectedThread.write("1");	// Send "1" via Bluetooth for full speed
                break;
            case R.id.stopBtn:
                //send Go Message via Bluetooth
                Toast.makeText(this, "StopButton clicked", Toast.LENGTH_SHORT).show();
                stopButton.setEnabled(false);
                mConnectedThread.write("0");	// Send "0" via Bluetooth
                break;
            case R.id.plusBtn:
                Toast.makeText(this, "PlusButton clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.minusBtn:
                Toast.makeText(this,"MinusButton clicked",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on
        // Emulator doesn't support Bluetooth and will return null
        if(btAdapter==null) {
            errorExit("Fatal Error", "Bluetooth not support");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }


    private void errorExit(String title, String message){
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);		// Get number of bytes and message in "buffer"
                    h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();		// Send to message queue Handler
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String message) {
            Log.d(TAG, "...Data to send: " + message + "...");
            byte[] msgBuffer = message.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) {
                Log.d(TAG, "...Error data send: " + e.getMessage() + "...");
            }
        }
    }
}
}
