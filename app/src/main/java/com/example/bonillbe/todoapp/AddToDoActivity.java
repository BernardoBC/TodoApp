package com.example.bonillbe.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class AddToDoActivity extends AppCompatActivity {

    private EditText mNewItem;
    private ArrayList<String> mItems;
    private ItemListController listController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        mNewItem = (EditText) findViewById(R.id.et_newItem);
        listController = new ItemListController(this);
        mItems = listController.getmItems();
    }

    public void addTodoItem(View view){
        if(!mNewItem.getText().toString().isEmpty()){
            Log.d("add", "addTodoItem: adding");
            listController.saveNewItem(mNewItem.getText().toString());
            Log.d("add", "addTodoItem: starting intent");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
