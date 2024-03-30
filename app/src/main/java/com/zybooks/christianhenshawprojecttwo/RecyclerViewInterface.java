package com.zybooks.christianhenshawprojecttwo;

/*Author: Christian Henshaw
 * Course: SNHU CS-360
 *
 * RecyclerViewInterface determines certain functions that are required to be implemented by classes
 * that implement InventoryAdapter class.
 */
public interface RecyclerViewInterface {
    void onDeleteClick(int position);
    void onEditClick(int position);
}
