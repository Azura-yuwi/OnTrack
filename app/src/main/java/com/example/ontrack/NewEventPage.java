package com.example.ontrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewEventPage extends AppCompatActivity {
    private int pass_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_page);

        Intent intent = getIntent();
        pass_type = intent.getIntExtra("pass_type", 0);

        if(pass_type == 1)
        {
            EditText editText = (EditText) findViewById(R.id.etEventName);
            EditText editDes = (EditText) findViewById(R.id.etmlMakeDescription);
            editText.setText(intent.getStringExtra("event_name"));
            editDes.setText(intent.getStringExtra("event_des"));
        }
    }

    public void save(View view)
    {
        EditText editText = (EditText) findViewById(R.id.etEventName);
        EditText editTextDes = (EditText) findViewById(R.id.etmlMakeDescription);
        String nametoPassBack = editText.getText().toString();
        String descriptionToPass = editTextDes.getText().toString();
        Intent intent = new Intent();

        if(pass_type == 1)
        {
            intent.putExtra("edit", nametoPassBack);
            intent.putExtra("editDes", descriptionToPass);
        }
        else
        {
            intent.putExtra("make_new", nametoPassBack);
            intent.putExtra("make_des", descriptionToPass);
        }

        setResult(RESULT_OK, intent); //RESULT_OK = -1 i believe
        finish();
    }
}