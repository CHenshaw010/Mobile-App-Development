package com.zybooks.christianhenshawprojecttwo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.Manifest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * InventoryOverviewActivity is a controller class.
 * Layout for this activity: activity_inventory.xml
 * Implements required methods defined by RecyclerViewInterface
 */
public class InventoryOverviewActivity extends AppCompatActivity implements RecyclerViewInterface {
    //global variables
    private Menu mMenu;
    ArrayList<Item> items;
    ItemDatabase db;
    InventoryAdapter itemAdapter;
    RecyclerView recyclerView;
    static Boolean SMSAllowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //instantiate the ItemDatabase
        db = new ItemDatabase(this);
        //store all ItemDatabase items in an ArrayList via the ItemDatabase.getAllItems() function
        items = db.getAllItems();
        //Instantiate and populate the recyclerview and adapter
        recyclerView = findViewById(R.id.inventoryRecyclerView);
        itemAdapter = new InventoryAdapter(this, items, db, this);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    //hosts menu option for SMS feature
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu button
        getMenuInflater().inflate(R.menu.inventory_menu, menu);
        mMenu = menu;
        //set the action_SMS menu button to visible
        mMenu.findItem(R.id.action_SMS).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //determine when and which menu option was selected
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //action_SMS menu button is selected
        if (item.getItemId() == R.id.action_SMS) {
            //check self permissions to determine if SEND_SMS is granted
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                //assign global variable to true to be used by the SMS feature later
                SMSEnabled();
            }
            //check self permissions to determine if SEND_SMS is not granted
            else {
                //request necessary SEND_SMS permissions from user
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.SEND_SMS}, 100);
            }
        }
        //populate and show the SMSDialog class for user to determine whether to grant or not grant permissions
        SMSDialog dialog = new SMSDialog();
        dialog.show(getSupportFragmentManager(), "SMSDialog");
        return super.onOptionsItemSelected(item);
    }

    //utilized when the floating action bar is clicked to add a new item to the ItemDatabase
    public void onAddInventoryClick(View view) {
        //intent to start AddItemActivity (Add item screen)
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    @Override
    //utilized when a delete button is pressed on any recyclerview row
    //receives the position of the row
    public void onDeleteClick(int position) {
        //call ItemDatabase delete method and pass the associated row position
        db.deleteItem(items.get(position));
        //refresh the recyclerview widget
        recyclerView.setLayoutManager(new LinearLayoutManager(this));;
    }

    @Override
    //utilized when an edit button is pressed on any recyclerview row
    //receives the position of the row
    public void onEditClick(int position) {
        //intent to start EditItemActivity (Edit item screen)
        Intent intent = new Intent(this, EditItemActivity.class);

        //put item name and item position as extras in the intent to be utilized by EditItemActivity
        intent.putExtra("ITEMNAME", items.get(position).getItemName());
        intent.putExtra("ITEMPOSITION", position);
        startActivity(intent);
    }

    //sets value of boolean SMSAllowed to be used in SendSMSNotification() method below
    public static void SMSEnabled() {
        SMSAllowed = true;
    }
    //sets value of boolean SMSAllowed to be used in SendSMSNotification() method below
    public static void SMSDisabled() {
        SMSAllowed = false;
    }

    //function to process SMS requests by the InventoryAdapter and send SMS notifications based on permissions
    public static void SendSMSNotification(Context context) {

        //SMS notification message
        String SMSMessage = "One or more Inventory Items have reached zero capacity!";
        //only process if the permission is granted
        if (SMSAllowed) {
            try {
                //create SmsManager instance
                SmsManager SMSManager = SmsManager.getDefault();
                String phoneNum = "15551234567";
                //send SMS notification to appropriate device
                SMSManager.sendTextMessage(phoneNum, "null", SMSMessage, null, null);
            } catch (Exception e){
                //handle errors
                Toast.makeText(context, R.string.SMSAreDisabled, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}