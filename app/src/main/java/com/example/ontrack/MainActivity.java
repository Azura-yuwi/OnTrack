package com.example.ontrack;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<String> data = new ArrayList<>();
    MainAdapter.ClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listener = new MainAdapter.ClickListener() {
            @Override
            public void click(int index) {
                Intent intent = new Intent(MainActivity.this, DisplayEvent.class);
                String name = data.get(index);
                intent.putExtra("event_name", name);
                intent.putExtra("position", index);
                seeEvents.launch(intent);
            }
        };
        adapter = new MainAdapter(data, listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }

    public void send(View view)
    {
        Intent intent = new Intent(this, NewEventPage.class);
        launchNewEventPage.launch(intent);
    }

    ActivityResultLauncher<Intent> launchNewEventPage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result)
        {
            if(result.getResultCode() == Activity.RESULT_OK)
            {
                Intent dataIntent = result.getData();
                String returnString = dataIntent.getStringExtra("make_new");
                data.add(returnString);
                adapter.notifyItemChanged(data.size() - 1);
            }
        }
    });

    ActivityResultLauncher<Intent> seeEvents = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result)
        {
            int resultCode = result.getResultCode();
            if(resultCode == 0)
            {
                Intent dataIntent = result.getData();
                String returnString = dataIntent.getStringExtra("edit_name");
                int position = dataIntent.getIntExtra("position", 0);
                data.set(position, returnString);
                adapter.notifyItemChanged(position);
            }
            else if(resultCode == 2)
            {
                Intent dataIntent = result.getData();
                int position = dataIntent.getIntExtra("position", 0);
                data.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }
    });

}