package com.zybooks.christianhenshawprojecttwo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * EditItemActivity controls and handles items edited within the item database.
 * Layout for this activity: activity_edititem.xml
 */
public class EditItemActivity extends AppCompatActivity {

    //global variables
    EditText itemName;
    EditText itemQuantity;
    Button cancelButton;
    Button editButton;
    ItemDatabase db;
    //int itemPosition;
    ArrayList<Item> items;
    String itemNameToEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edititem);

        //assign two buttons and edit texts to variables
        itemName = findViewById(R.id.editItemNameEditText);
        itemQuantity = findViewById(R.id.editItemQuantityEditText);
        cancelButton = findViewById(R.id.editItemCancelButton);
        editButton = findViewById(R.id.editItemEditButton);
        //instantiate the ItemDatabase
        db = new ItemDatabase(this);
        //store all ItemDatabase items in an ArrayList via the ItemDatabase.getAllItems() function
        items = db.getAllItems();

        //grab old item name from the intent's extra
        itemNameToEdit = getIntent().getStringExtra("ITEMNAME");
        //assign old item name to itemName (editItemNameEditText) edit text
        //NOTE: THIS VALUE CAN BE EDITED/UPDATED
        itemName.setText(itemNameToEdit);

        //on click listener for Cancel Button
        cancelButton.setOnClickListener(view -> { cancel();});

        //on click listener for Add Button
        editButton.setOnClickListener(view -> { edit();});
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

    //called when the edit button is pressed
    public void edit() {
        //verify itemName (editItemNameEditText) and itemQuantity (editItemQuantityEditText) are not empty
        //if so, display relevant information to the user
        if (itemName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Item Name Field is Empty", Toast.LENGTH_SHORT).show();
        } else if (itemQuantity.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Item Quantity Field is Empty", Toast.LENGTH_SHORT).show();
        } else {
            //store new item quantity in itemQuantityTemp retrieved from itemQuantity (editItemQuantityEditText)
            int itemQuantityTemp = Integer.parseInt(itemQuantity.getText().toString().trim());
            //store new item name in itemNameTemp retrieved from itemName (editItemNameEditText)
            String itemNameTemp = itemName.getText().toString().trim();

            //call ItemDatabase function to update item.
            //passes old item name (to search db), new item name, and new item quantity
            //returns a boolean value based on numRowsUpdated > 0
            boolean accomplished = db.updateItem(itemNameToEdit, itemNameTemp, itemQuantityTemp);
            if (accomplished) {
                Toast.makeText(this, "Item Edited Successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Item Editing Failed!", Toast.LENGTH_SHORT).show();
            }

            //intent to start InventoryOverviewActivity (Inventory screen)
            Intent intent = new Intent(this, InventoryOverviewActivity.class);
            startActivity(intent);
        }
    }
}
