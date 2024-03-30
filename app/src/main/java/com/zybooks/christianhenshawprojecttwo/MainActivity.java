package com.zybooks.christianhenshawprojecttwo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * MainActivity is the controller class for login and credential processing.
 * Layout for this activity: activity_main.xml
 */
public class MainActivity extends AppCompatActivity {

    //global variables
    EditText username;
    EditText password;
    Button LoginButton;
    Button RegisterButton;
    UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign two buttons and edit texts to variables
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        LoginButton = findViewById(R.id.loginButton);
        RegisterButton = findViewById(R.id.registerButton);
        //instantiate the UserDatabase
        db = new UserDatabase(this);

        //on click listener for Sign In Button
        LoginButton.setOnClickListener(view -> { login();});

        //on click listener for Register Button
        RegisterButton.setOnClickListener(view -> {register();});
    }

    @Override
    //hosts menu option for SMS feature
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu button
        getMenuInflater().inflate(R.menu.inventory_menu, menu);
        //set the action_SMS menu button to invisible (not needed on login screen)
        menu.findItem(R.id.action_SMS).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    //called when the login button is pressed
    public void login() {
        //verify username (usernameEditText) and password (passwordEditText) are not empty
        //if so, display relevant information to the user
        if (username.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Username Field is Empty", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
        } else {
            //store username (usernameEditText) and password (passwordEditText) for validation
            String passwordTemp = password.getText().toString().trim();
            //assign new user to the result of calling UserDatabase readUser method
            //this identifies if the username already exists
            User user = db.readUser(username.getText().toString().trim());

            //if the password entered matches the password associated with the previously read user in UserDatabase
            if (passwordTemp.equalsIgnoreCase(user.getPassword())) {
                Toast.makeText(this, "Sign In Successful!", Toast.LENGTH_SHORT).show();
                //intent to start InventoryOverviewActivity (Inventory screen)
                Intent intent = new Intent(this, InventoryOverviewActivity.class);
                startActivity(intent);
            } else {
                //the password entered does not match the previously read user's stored password in UserDatabase
                Toast.makeText(this, "Incorrect Sign In Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //called when the register button is pressed
    public void register() {
        //verify username (usernameEditText) and password (passwordEditText) are not empty
        //if so, display relevant information to the user
        if (username.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Username Field is Empty", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
        } else {
            //store username (usernameEditText) and password (passwordEditText)
            String usernameTemp = username.getText().toString().trim();
            String passwordTemp = password.getText().toString().trim();

            //create new User object and pass usernameTemp and passwordTemp for the constructor
            User user = new User(usernameTemp, passwordTemp);
            //call the UserDatabase createUser method to add the user to the UserDatabase
            db.createUser(user);

            Toast.makeText(this, "User Created Successfully!", Toast.LENGTH_SHORT).show();
            //intent to start InventoryOverviewActivity (Inventory screen)
            Intent intent = new Intent(this, InventoryOverviewActivity.class);
            startActivity(intent);
        }
    }
}