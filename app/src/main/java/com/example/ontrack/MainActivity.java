package com.example.ontrack;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Event> data = new ArrayList<>();
    MainAdapter.ClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listener = new MainAdapter.ClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void click(int index) {
                Intent intent = new Intent(MainActivity.this, DisplayEvent.class);
                Event event = data.get(index);
                String name = event.name;
                String des = event.description;

                intent.putExtra("event_year", event.date.getYear());
                intent.putExtra("event_month", event.date.getMonthValue()-1);
                intent.putExtra("event_day", event.date.getDayOfMonth());
                intent.putExtra("event_date_string", event.makeDate());

                intent.putExtra("event_name", name);
                intent.putExtra("event_des", des);
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
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onActivityResult(ActivityResult result)
        {
            if(result.getResultCode() == Activity.RESULT_OK)
            {
                Intent dataIntent = result.getData();
                String returnString = dataIntent.getStringExtra("make_new");
                String returnDes = dataIntent.getStringExtra("make_des");
                int year = dataIntent.getIntExtra("make_year", 1);
                int month = dataIntent.getIntExtra("make_month", 1);
                int day = dataIntent.getIntExtra("make_day", 1);
                data.add(new Event(returnString, returnDes));
                data.get(data.size() - 1).setDate(year, month, day);
                adapter.notifyItemChanged(data.size() - 1);
            }
        }
    });

    ActivityResultLauncher<Intent> seeEvents = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onActivityResult(ActivityResult result)
        {
            int resultCode = result.getResultCode();

            if(resultCode == 0)
            {
                Intent dataIntent = result.getData();
                String returnString = dataIntent.getStringExtra("edit_name");
                String returnDes = dataIntent.getStringExtra("edit_des");
                int year = dataIntent.getIntExtra("make_year", 1);
                int month = dataIntent.getIntExtra("make_month", 1);
                int day = dataIntent.getIntExtra("make_day", 1);
                int position = dataIntent.getIntExtra("position", 0);
                data.get(position).setDate(year, month, day);
                data.get(position).name = returnString;
                data.get(position).description = returnDes;
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