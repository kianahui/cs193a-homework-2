package com.example.kianahui.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.Window;
import java.util.*;
import android.app.*;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> TaskArray;
    private ArrayAdapter<String> Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);
        TaskArray = new ArrayList<>();
        Adapter = new ArrayAdapter<>(
                this,
                R.layout.mylistlayout,
                R.id.list_row_text,
                TaskArray);
        list.setAdapter(Adapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> list,
                                            View row,
                                            int index,
                                            long rowID) {
                        TaskArray.remove(index);
                        Adapter.notifyDataSetChanged();
                    }
                });
    }

    public void addTask(View view) {
        EditText newTask = (EditText) findViewById(R.id.newTask);
        String newTaskString = newTask.getText().toString();
        if (newTaskString.matches("")) {
            Toast.makeText(this,"Enter a Task!", Toast.LENGTH_SHORT).show();
            return;
        }
        TaskArray.add(0, newTaskString);
        Adapter.notifyDataSetChanged();
        newTask.setText("");
    }


    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("array", TaskArray);

    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        TaskArray = (ArrayList) bundle.getSerializable("array");
        Adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                TaskArray);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(Adapter);
        Adapter.notifyDataSetChanged();

    }

}
