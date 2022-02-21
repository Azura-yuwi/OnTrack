package com.example.ontrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        data = getData();
        adapter = new MainAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<String> getData()
    {
        ArrayList<String> ans = new ArrayList<>();

        for(int i = 0; i < 10; i++)
        {
            ans.add("string " + i);
        }

        return ans;
    }
}