package com.zybooks.christianhenshawprojecttwo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * SMSDialog is the SMS Dialog builder class utilized by InventoryOverviewActivity class.
 */
public class SMSDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        //build new AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity())
                //set builder values necessary to inform user
                .setTitle(R.string.SMSTitle)
                .setMessage(R.string.SMSMessage)
                //display a positive button to the user
                .setPositiveButton(R.string.SMSEnable, (dialog, id) -> {
                    //call InventoryOverviewActivity SMSEnabled() method to change a boolean variable to true
                    InventoryOverviewActivity.SMSEnabled();
                    dialog.cancel();
                })
                //display a negative button to the user
                .setNegativeButton(R.string.SMSDisable, (dialog, id) -> {
                    //call InventoryOverviewActivity SMSDisabled() method to change a boolean variable to false
                    InventoryOverviewActivity.SMSDisabled();
                    dialog.cancel();
                });

        return builder.create();
    }
}
