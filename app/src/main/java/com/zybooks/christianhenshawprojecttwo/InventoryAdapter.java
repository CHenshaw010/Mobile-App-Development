package com.zybooks.christianhenshawprojecttwo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * InventoryAdapter is the adapter for the recyclerview.
 * Layout for this activity: inventory_recycler_view_row.xml
 */
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {

    //global variables
    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    public ArrayList<Item> items;
    ItemDatabase db;

    //adapter constructor
    public InventoryAdapter(Context context, ArrayList<Item> items, ItemDatabase db, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.items = items;
        this.db = db;
        //recyclerViewInterface is passed in to be utilized in MyViewHolder innerclass method
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    //create and inflate each individual row
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inventory_recycler_view_row, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    //bind data to widgets on each row
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //retrieve all ItemDatabase items and store them in an ArrayList
        items = db.getAllItems();
        //ensure items (and therefore ItemDatabase) do contain data
        if (items != null) {
            //assign data to text views in each recyclerview row
            holder.itemNameTextView.setText(String.valueOf(items.get(position).getItemName()));
            holder.itemQuantityTextView.setText(String.valueOf(items.get(position).getItemQuantity()));

            //iteratively store the item quantity for each row
            //used for SMS messaging when inventory items are low (0)
            String quantity = holder.itemQuantityTextView.getText().toString().trim();
            //if item quantity is zero
            if (quantity.equals("0")) {
                //dynamically change the background and text color of the associated item quantity text view
                holder.itemQuantityTextView.setBackgroundColor(Color.RED);
                holder.itemQuantityTextView.setTextColor(Color.WHITE);
                //call InventoryOverviewActivity method to send SMS notification for low inventory
                InventoryOverviewActivity.SendSMSNotification(context.getApplicationContext());
            }
        }
    }

    @Override
    //used by recyclerview to determine positions and buffers
    public int getItemCount() {
        return db.getAllItems().size();
    }

    //innerclass recyclerview method MyViewHOlder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //local variables
        ImageButton deleteButton;
        ImageButton editButton;
        TextView itemNameTextView;
        TextView itemQuantityTextView;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            //assign two buttons and two text views to variables
            deleteButton = itemView.findViewById(R.id.inventoryRowDeleteImageButton);
            editButton = itemView.findViewById(R.id.inventoryRowEditImageButton);
            itemNameTextView = itemView.findViewById(R.id.inventoryRowItemNameTextView);
            itemQuantityTextView = itemView.findViewById(R.id.inventoryRowItemAmountTextView);

            // onClick() method utilized when the delete button is pressed on any row
            itemView.findViewById(R.id.inventoryRowDeleteImageButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getBindingAdapterPosition();
                        //ensure position in recyclerview is valid
                        if (position != RecyclerView.NO_POSITION){
                            //call RecyclerViewInterface onDeleteClick method implemented by InventoryOverviewActivity
                            //passes recyclerview position for correct item deletion
                            recyclerViewInterface.onDeleteClick(position);
                        }
                    };
                }
            });

            // onClick() method utilized when the edit button is pressed on any row
            itemView.findViewById(R.id.inventoryRowEditImageButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getBindingAdapterPosition();
                        //ensure position in recyclerview is valid
                        if (position != RecyclerView.NO_POSITION){
                            //call RecyclerViewInterface onEditClick method implemented by InventoryOverviewActivity
                            //passes recyclerview position for correct item modification
                            recyclerViewInterface.onEditClick(position);
                        }
                    };
                }
            });
        }
    }
}
