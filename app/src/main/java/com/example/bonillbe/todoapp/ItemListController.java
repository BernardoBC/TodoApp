package com.example.bonillbe.todoapp;

import android.content.Context;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bonillbe on 6/21/2017.
 */

public class ItemListController {

    private ArrayList<String> mItems;
    private Context context;

    public ItemListController(Context context){
        this.context = context;
        setmItems(readItems());
    }

    public ArrayList<String> readItems(){
        Log.d("read", "readItems: working");
        File filesDir = context.getFilesDir();
        File todoFile = new File(filesDir,"todo.txt");
        try {
            return new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }

    public void saveNewItem(String newItem){
        mItems.add(newItem);
        saveList();
    }

    public void saveList(){
        File filesDir = context.getFilesDir();
        File todoFile = new File(filesDir,"todo.txt");
        try {
            FileUtils.writeLines(todoFile, mItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getmItems() {
        return mItems;
    }

    public void setmItems(ArrayList<String> mItems) {
        this.mItems = mItems;
    }
}
