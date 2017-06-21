package com.example.bonillbe.todoapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TodoListAdapter.ListItemOnLongClickListener{

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private EditText etNewItem;
    private FloatingActionButton mAddToDoItemFAB;

    private ItemListController listController;

    private TodoListAdapter mAdapter;
    private RecyclerView mTodoList;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lvItems = (ListView) findViewById(R.id.rcItem);
        //etNewItem = (EditText) findViewById(R.id.etNewItem);
        mAddToDoItemFAB = (FloatingActionButton)findViewById(R.id.addToDoItemFAB);

        listController = new ItemListController(this);
        items = listController.getmItems();
        //items.add("First Item");
        //items.add("Second Item");
        mTodoList = (RecyclerView) findViewById(R.id.rcItem);
        mAdapter = new TodoListAdapter(items, this);

        mTodoList.setAdapter(mAdapter);
        mTodoList.setLayoutManager(new LinearLayoutManager(this));


        mAddToDoItemFAB.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
//                app.send(this, "Action", "FAB pressed");
//                Intent newTodo = new Intent(MainActivity.this, AddToDoActivity.class);
//                ToDoItem item = new ToDoItem("", false, null);
//                int color = ColorGenerator.MATERIAL.getRandomColor();
//                item.setTodoColor(color);
//                newTodo.putExtra(TODOITEM, item);
//                startActivityForResult(newTodo, REQUEST_ID_TODO_ITEM);

                Intent newTodo = new Intent(MainActivity.this, AddToDoActivity.class);

                startActivity(newTodo);

                //Log.d("Main", "onClick: FAB");
            }
        });



        //itemsAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, items);
        //lvItems.setAdapter(itemsAdapter);
//        items.add("First Item");
//        items.add("Second Item");
//        saveNewItems();

        //setupListViewListener();

    }

    public void addTodoItem(View view){
        String newItem = etNewItem.getText().toString();
        if(!newItem.isEmpty()){
            items.add(newItem);
            mAdapter.notifyItemInserted(items.size()-1);
            etNewItem.setText("");
            listController.setmItems(items);
            listController.saveList();
            etNewItem.clearFocus();
            hideKeyboard();
        }
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    public void removeTodoItem(int position){
        items.remove(position);
        listController.setmItems(items);
        listController.saveList();
    }

    @Override
    public void onListItemOnLongClick(int clickedItemIndex) {
        removeTodoItem(clickedItemIndex);
        mAdapter.notifyDataSetChanged();

        if (mToast != null) {
            mToast.cancel();
        }

        // COMPLETED (12) Show a Toast when an item is clicked, displaying that item number that was clicked
        /*
         * Create a Toast and store it in our Toast field.
         * The Toast that shows up will have a message similar to the following:
         *
         *                     Item #42 clicked.
         */
        String toastMessage = "Item #" + clickedItemIndex + " deleted.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }
}
