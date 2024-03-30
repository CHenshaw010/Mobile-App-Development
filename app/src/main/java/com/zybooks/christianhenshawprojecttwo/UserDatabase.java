package com.zybooks.christianhenshawprojecttwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * UserDatabase is the database to hold all user data.
 */
public class UserDatabase extends SQLiteOpenHelper {
    //UserDatabase version, can be incremented to start anew
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "UserData.db";

    //UserDatabase constructor
    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //UserDatabase defined layout
    private static final class UserTable {
        private static final String TABLE = "users";
        private static final String COL_ID = "_id";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.TABLE + " (" +
                UserTable.COL_ID + " integer primary key autoincrement, " +
                UserTable.COL_USERNAME + " text, " +
                UserTable.COL_PASSWORD + " text)");
    }

    @Override
    //create new UserDatabase based on database version
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserTable.TABLE);
        onCreate(db);
    }

    //user Database CRUD Operations

    //add user to user database
    //receives an User object and will use getters/setters to set ContentValues
    public void createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserTable.COL_USERNAME, user.getUsername());
        values.put(UserTable.COL_PASSWORD, user.getPassword());

        //insert values (User information) into UserDatabase
        db.insert(UserTable.TABLE, null, values);
        db.close();
    }

    //read user from user database
    //receives an user id to identify associated UserDatabase entry
    public User readUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        //sql query to match _id with passed id
        String sql = "select * from " + UserTable.TABLE + " where username = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{(username)});

        //create new empty user
        User user = new User(null, null);
        //find matching UserDatabase entry
        if (cursor.moveToFirst()) {
            do {
                //set new user values based on UserDatabase entry
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        //return the new user
        return user;
    }

    //update user in user database
    //receives User object and will use getters to set ContentValues
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserTable.COL_USERNAME, user.getUsername());
        values.put(UserTable.COL_PASSWORD, user.getPassword());

        //only update rows matching userId and update username and password
        return db.update(UserTable.TABLE, values, UserTable.COL_ID + " = ?", new String[] { String.valueOf(user.getId()) });
    }

    //delete user from user database
    //receives an User object and will use getters for queries
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        //only delete UserDatabase entries matching the user.getId()
        db.delete(UserTable.TABLE, UserTable.COL_ID + " = ?", new String[] { String.valueOf(user.getId()) });
        db.close();
    }
}