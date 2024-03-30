package com.zybooks.christianhenshawprojecttwo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * ItemDatabase is the database to hold all item data.
 */
public class ItemDatabase extends SQLiteOpenHelper {

    //ItemDatabase version, can be incremented to start anew
    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "ItemData.db";

    //ItemDatabase constructor
    public ItemDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //ItemDatabase defined layout
    private static final class ItemTable {
        private static final String TABLE = "items";
        private static final String COL_ID = "_id";
        private static final String COL_ITEMNAME = "name";
        private static final String COL_ITEMQUANTITY = "quantity";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemTable.TABLE + " (" +
                ItemTable.COL_ID + " integer primary key autoincrement, " +
                ItemTable.COL_ITEMNAME + " text, " +
                ItemTable.COL_ITEMQUANTITY + " text)");
    }

    @Override
    //create new ItemDatabase based on database version
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ItemDatabase.ItemTable.TABLE);
        onCreate(db);
    }

    //user Database CRUD Operations

    //add item to item database
    //receives an Item object and will use getters/setters to set ContentValues
    public void createItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemTable.COL_ITEMNAME, item.getItemName());
        values.put(ItemTable.COL_ITEMQUANTITY, item.getItemQuantity());

        //insert values (Item information) into ItemDatabase
        db.insert(ItemDatabase.ItemTable.TABLE, null, values);
        db.close();
    }

    //read item from item database
    //receives an item id to identify associated ItemDatabase entry
    public Item readItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        //sql query to match _id with passed id
        String sql = "select * from " + ItemDatabase.ItemTable.TABLE + " where _id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{
                String.valueOf(id)});

        //create new empty item
        Item item = new Item(null, -1);
        //find matching ItemDatabase entry
        if (cursor.moveToFirst()) {
            do {
                //set new item values based on ItemDatabase entry
                item.setItemName(cursor.getString(1));
                item.setItemQuantity(cursor.getInt(2));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        //return the new item
        return item;
    }

    //update item in item database
    //receives itemName for queries, newItemName/newQuantity to update existing ItemDatabase entry
    public boolean updateItem(String itemName, String newItemName, int newQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemDatabase.ItemTable.COL_ITEMNAME, newItemName);
        values.put(ItemDatabase.ItemTable.COL_ITEMQUANTITY, newQuantity);

        //only update rows matching itemName and update itemName and itemQuantity
        int numRowsUpdated = db.update(ItemDatabase.ItemTable.TABLE, values, ItemTable.COL_ITEMNAME + " = ?", new String[] { itemName });
        //return a boolean to be used for success/failure determination
        return numRowsUpdated > 0;
    }

    //delete item from item database
    //receives an Item object and will use getters/setters for queries
    public void deleteItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        //only delete ItemDatabase entries matching the Item.getId()
        db.delete(ItemDatabase.ItemTable.TABLE, ItemDatabase.ItemTable.COL_ID + " = ?", new String[] { String.valueOf(item.getId()) });
        db.close();
    }

    //retrieve All Items
    //receives nothing, returns a list of all ItemDatabase items in an ArrayList
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> itemList = new ArrayList<>();
        //blank query to obtain all entries
        String sql = "select * from " + ItemTable.TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        //iterate through ItemDatabase fully
        if (cursor.moveToFirst()) {
            do {
                //iteratively assign cursor values to new Item objects
                Item item = new Item();
                item.setId(cursor.getInt(0));
                item.setItemName(cursor.getString(1));
                item.setItemQuantity(cursor.getInt(2));

                //add new Item object to ArrayList
                itemList.add(item);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        //return the ArrayList containing all ItemDatabase Items
        return itemList;
    }
}