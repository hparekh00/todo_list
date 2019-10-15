package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<TodoItems> itemList;

    //ArrayAdapter<String> adapter;
    private TodoListAdapter mAdapter;

    EditText itemTitle;
    EditText itemDescription;

    Button addButton;

    ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        itemTitle = findViewById(R.id.edit_title);
        itemDescription = findViewById(R.id.edit_description);
        addButton = findViewById(R.id.add_btn);

        // new arraylist defined to hold todoitems
        itemList = new ArrayList<>();

        //mAdapter = new TodoListAdapter(MainActivity.this, itemList);

        View.OnClickListener addlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add(new TodoItems(itemTitle.getText().toString(), itemDescription.getText().toString()));
                itemTitle.setText(""); itemDescription.setText("");
                mAdapter.notifyDataSetChanged();

                // we want to save contents of listview into text file on phone
                // lets take the length of the arraylist
                // take the elements from each position in there and append to String Builder
                StringBuilder list_contents_builder = new StringBuilder();

                int count = lv.getCount();
                for(int item=count-1; item>=0; item--){
                    TodoItems holder = (TodoItems) lv.getItemAtPosition(item);
                    list_contents_builder.append(holder.getMtitle().toString()+"\n");
                    list_contents_builder.append(holder.getMdescription().toString()+"\n\n");

                }
                String list_contents = list_contents_builder.toString();

                try {
                    File locationFile = new File("/todo_list.txt");
                    locationFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(locationFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(list_contents);
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(getBaseContext(), "Saved List to Text File", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Failed: Saved List to Text File", Toast.LENGTH_SHORT).show();
                }


            }
        };

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                SparseBooleanArray positionChecker = lv.getCheckedItemPositions();
                int count = lv.getCount();
                for(int item= count-1; item >= 0; item--){
                    if(positionChecker.get(item)){
                        mAdapter.remove(itemList.get(item));

                    }
                }

                positionChecker.clear();

                mAdapter.notifyDataSetChanged();

                return false;
            }
        });


        addButton.setOnClickListener(addlistener);

        mAdapter = new TodoListAdapter(MainActivity.this, itemList);
        lv.setAdapter(mAdapter);
    }
}
