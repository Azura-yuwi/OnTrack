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

    private static final int NEW_EVENT_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new MainAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }


    public void send(View view)
    {
        Intent intent = new Intent(this, NewEventPage.class);
        //startActivityForResult(intent, NEW_EVENT_REQUEST_CODE);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == NEW_EVENT_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                String returnString = intent.getStringExtra("make_new");
                data.add(returnString);
                adapter.notifyItemChanged(data.size() - 1);
            }
        }
    }
}