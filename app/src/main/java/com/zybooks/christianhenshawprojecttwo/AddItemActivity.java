package com.zybooks.christianhenshawprojecttwo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*Author: Christian Henshaw
* Course: SNHU CS-360
*
* AddItemActivity controls and handles items added to the item database.
* Layout for this activity: activity_additem.xml
*/

public class AddItemActivity extends AppCompatActivity {

    //global variables
    EditText itemName;
    EditText itemQuantity;
    Button cancelButton;
    Button addButton;
    ItemDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        //assign two buttons and edit texts to variables
        itemName = findViewById(R.id.addItemNameEditText);
        itemQuantity = findViewById(R.id.addItemQuantityEditText);
        cancelButton = findViewById(R.id.addItemCancelButton);
        addButton = findViewById(R.id.addItemAddButton);
        //instantiate the ItemDatabase
        db = new ItemDatabase(this);

        //on click listener for Cancel Button
        cancelButton.setOnClickListener(view -> { cancel();});

        //on click listener for Add Button
        addButton.setOnClickListener(view -> { add();});
    }

    //called when the cancel button is pressed
    public void cancel() {
        //clear edit text fields
        itemName.setText("");
        itemQuantity.setText("");
        //intent to start InventoryOverviewActivity (Inventory screen)
        Intent intent = new Intent(this, InventoryOverviewActivity.class);
        startActivity(intent);
    }

    //called when the add item button is pressed
    public void add() {
        //verify itemName (addItemNameEditText) and itemQuantity (addItemQuantityEditText) are not empty
        //if so, display relevant information to the user
        if (itemName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Item Name Field is Empty", Toast.LENGTH_SHORT).show();
        } else if (itemQuantity.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Item Quantity Field is Empty", Toast.LENGTH_SHORT).show();
        } else {
            //store new item name in itemNameTemp retrieved from itemName (addItemNameEditText)
            String itemNameTemp = itemName.getText().toString().trim();
            //convert and store new item quantity in itemQuantityTemp retrieved from itemQuantity (addItemQuantityEditText)
            int itemQuantityTemp = Integer.parseInt(itemQuantity.getText().toString().trim());

            //create new item object instance with above variables
            Item item = new Item(itemNameTemp, itemQuantityTemp);
            //call ItemDatabase function to add new item into the database
            db.createItem(item);

            Toast.makeText(this, "Item Added Successfully!", Toast.LENGTH_SHORT).show();
            //intent to start InventoryOverviewActivity (Inventory screen)
            Intent intent = new Intent(this, InventoryOverviewActivity.class);
            startActivity(intent);
        }
    }
}
