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
    int year;
    int month;
    int day;
    int color;
    String displayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("event_name");
        color = intent.getIntExtra("event_color", 0xff000000);
        year = intent.getIntExtra("event_year", 1);
        month = intent.getIntExtra("event_month", 1);
        day = intent.getIntExtra("event_day", 1);
        displayDate = intent.getStringExtra("event_date_string");

        //intent.putExtra("event_year", event.date.getYear());
        //intent.putExtra("event_month", event.date.getMonthValue()-1);
       // intent.putExtra("event_day", event.date.getDayOfMonth());

        index = intent.getIntExtra("position", 0);

        TextView textView = (TextView) findViewById(R.id.tvInputEventName);
        textView.setText(name);

        TextView datetv = (TextView) findViewById(R.id.tvShowInputDate);
        datetv.setText(displayDate);

        String des = intent.getStringExtra("event_des");
        TextView textViewDes = (TextView) findViewById(R.id.tvShowInputDescription);
        textViewDes.setText(des);
    }

    public void editEventPage(View view)
    {
        TextView textView = (TextView) findViewById(R.id.tvInputEventName);
        String name = textView.getText().toString();
        TextView textViewDes = (TextView) findViewById(R.id.tvShowInputDescription);
        String des = textViewDes.getText().toString();

        Intent intent = new Intent(this, NewEventPage.class);
        intent.putExtra("pass_type", 1);
        intent.putExtra("event_name", name);
        intent.putExtra("event_des", des);

        intent.putExtra("event_date_string", displayDate);
        intent.putExtra("make_color", color);
        intent.putExtra("make_year", year);
        intent.putExtra("make_month", month);
        intent.putExtra("make_day", day);

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
                String des = dataIntent.getStringExtra("editDes");
                int year = dataIntent.getIntExtra("make_year", 1);
                int month = dataIntent.getIntExtra("make_month", 1);
                int day = dataIntent.getIntExtra("make_day", 1);
                int color = dataIntent.getIntExtra("make_color", 0xff000000);

                Intent passBack = new Intent();
                passBack.putExtra("edit_name", returnString);
                passBack.putExtra("edit_des", des);
                passBack.putExtra("position", index);
                passBack.putExtra("make_color", color);
                passBack.putExtra("make_year", year);
                passBack.putExtra("make_month", month);
                passBack.putExtra("make_day", day);
                setResult(0, passBack);
                finish();
            }
        }
    });
}