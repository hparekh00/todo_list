package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TodoListAdapter extends ArrayAdapter<TodoItems> {

    private Context mContext;

    private List<TodoItems> todo_list = new ArrayList<>();

    public TodoListAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<TodoItems> list){

        super(context, 0, list);
        mContext = context;
        todo_list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;

        if(listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }

        TodoItems current_list = todo_list.get(position);

        TextView item_title = (TextView) listItem.findViewById(R.id.textView_title);
        item_title.setText(current_list.getMtitle());

        TextView item_description = (TextView) listItem.findViewById(R.id.textView_description);
        item_description.setText(current_list.getMdescription());

        return listItem;

    }

}
