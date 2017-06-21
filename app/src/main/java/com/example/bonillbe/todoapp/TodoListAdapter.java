package com.example.bonillbe.todoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bonillbe on 6/20/2017.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.NumberViewHolder>{

    final private ListItemOnLongClickListener mOnClickListener;

    private static int viewHolderCount;

    private ArrayList<String> mtodoItems;

    public interface ListItemOnLongClickListener{
        void onListItemOnLongClick(int clickedItemIndex);
    }

    public TodoListAdapter(ArrayList<String> todoItems, ListItemOnLongClickListener listener) {
        mtodoItems = todoItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        //viewHolder.viewHolderIndex.setText(viewHolderCount);

        //viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        String todoItem = mtodoItems.get(position);
        TextView tv_todoItem = holder.todoItem;
        tv_todoItem.setText(todoItem);
        holder.viewHolderIndex.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return mtodoItems.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder implements OnLongClickListener {

        TextView todoItem;
        TextView viewHolderIndex;

        public NumberViewHolder(final View itemView) {
            super(itemView);
            todoItem = (TextView) itemView.findViewById(R.id.tv_todoItem);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            itemView.setOnLongClickListener(this);
        }

        void bind(int listIndex){
            todoItem.setText(String.valueOf(listIndex));
        }

//        public boolean OnLongClickListener(View view){
//
//            return true;
//        }

        private void removeAt(int position) {
            mtodoItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mtodoItems.size());
        }

        @Override
        public boolean onLongClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemOnLongClick(clickedPosition);
            return true;
        }
    }






}
