package com.zybooks.christianhenshawprojecttwo;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * Item is the item model class.
 */
public class Item {

    //global variables
    int id;
    String itemName;
    int itemQuantity;

    //base item constructor
    public Item(String itemName, int itemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    //empty item constructor
    public Item() {
        super();
    }

    //global variable getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
