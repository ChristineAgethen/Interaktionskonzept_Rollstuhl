package com.example.chris.rolliapp;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.ListView;

import java.util.Set;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnClickListener;
import android.widget.TextView;
import android.content.Intent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;



public class deviceList {
/*
    Button btnPaired;
    ListView devicelist;

    goButton = (Button)findViewById(R.id.go);
    devicelist = (ListView)findViewById(R.id.listView);

    //create variables to control BT
    private BluetoothAdapter myBluetooth = null;
    private Set pairedDevices;

    myBluetooth = BluetoothAdapter.getDefaultAdapter();

    if(myBluetooth == null)
    {
        //Show a mensag. that thedevice has no bluetooth adapter
        Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
        //finish apk
        finish();
    }
    else
    {

        if (myBluetooth.isEnabled())
        { }
        else
        {
            //Ask to the user turn the bluetooth on
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon,1);
        }
    }

    /*
    According to Android documents, an Intent is a messaging object you can use to request an action from another app component. Although intents facilitate communication between components in several ways, there are three fundamental use-cases:

    To start an activity:
    An Activity represents a single screen in an app. You can start a new instance of an Activity by passing an Intent to startActivity(). The Intent describes the activity to start and carries any necessary data.

    To start a service:
    A Service is a component that performs operations in the background without a user interface. You can start a service to perform a one-time operation (such as download a file) by passing an Intent to startService(). The Intent describes the service to start and carries any necessary data.

    To deliver a broadcast:
    A broadcast is a message that any app can receive. The system delivers various broadcasts for system events, such as when the system boots up or the device starts charging. You can deliver a broadcast to other apps by passing an Intent to sendBroadcast(), sendOrderedBroadcast(), or sendStickyBroadcast().

    We need to “listen” when the button is clicked to show paired devices. So OnClickListener Api will handle it
     */
/*

    btnPaired.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            pairedDevicesList(); //method that will be called
        }
    }

    private void pairedDevicesList()
    {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }

    //Allow the List View to be clicked
    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            // Make an intent to start next activity.
            Intent i = new Intent(DeviceList.this, ledControl.class);
            //Change the activity.
            i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
            startActivity(i);
        }
    };
    */
}

