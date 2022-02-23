package com.example.ontrack;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayEvent extends AppCompatActivity {
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("event_name");
        index = intent.getIntExtra("position", 0);
        TextView textView = (TextView) findViewById(R.id.tvInputEventName);
        textView.setText(name);
    }

    public void editEventPage(View view)
    {
        TextView textView = (TextView) findViewById(R.id.tvInputEventName);
        String name = textView.getText().toString();
        Intent intent = new Intent(this, NewEventPage.class);
        intent.putExtra("pass_type", 1);
        intent.putExtra("event_name", name);
        editEventLaunch.launch(intent);
    }

    public void deleteEvent(View view)
    {
        Intent passBack = new Intent();
        passBack.putExtra("position", index);
        setResult(2, passBack);
        finish();
    }

    ActivityResultLauncher<Intent> editEventLaunch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result)
        {
            if(result.getResultCode() == Activity.RESULT_OK)
            {
                Intent dataIntent = result.getData();
                String returnString = dataIntent.getStringExtra("edit");
                Intent passBack = new Intent();
                passBack.putExtra("edit_name", returnString);
                passBack.putExtra("position", index);
                setResult(0, passBack);
                finish();
            }
        }
    });
}