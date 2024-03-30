package com.zybooks.christianhenshawprojecttwo;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * User is the user model class.
 */
public class User {
    //global variables
    int id;
    private String username;
    private String password;

    //User constructor with userID
    public User(int i, String username, String password) {
        super();
        this.id = i;
        this.username = username;
        this.password = password;
    }

    //Base User constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //global variable getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
