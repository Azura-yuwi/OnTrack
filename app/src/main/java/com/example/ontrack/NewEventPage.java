package com.example.ontrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewEventPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_page);
    }

    public void save(View view)
    {
        EditText editText = (EditText) findViewById(R.id.etEventName);
        String toPassBack = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("make_new", toPassBack);
        setResult(RESULT_OK, intent); //RESULT_OK = -1 i believe
        finish();
    }
}