package com.example.ontrack;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;
import android.graphics.Color;
import com.rarepebble.colorpicker.ColorPickerView;

public class NewEventPage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private int pass_type;
    TextView dateToEdit;
    TextView colorText;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_page);
        setDate(1,1,1);
        dateToEdit = findViewById(R.id.editTextDate);
        colorText = findViewById(R.id.tvMakeColor);

        dateToEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                com.example.ontrack.DatePickerFragment picker;
                picker = new com.example.ontrack.DatePickerFragment();
                picker.show(getSupportFragmentManager(), "DATE_PICK");
            }
        });

        Intent intent = getIntent();
        pass_type = intent.getIntExtra("pass_type", 0);

        if(pass_type == 1)
        {
            EditText editText = (EditText) findViewById(R.id.etEventName);
            EditText editDes = (EditText) findViewById(R.id.etmlMakeDescription);
            editText.setText(intent.getStringExtra("event_name"));
            editDes.setText(intent.getStringExtra("event_des"));
            setDate(intent.getIntExtra("make_year", 0), intent.getIntExtra("make_month", 0), intent.getIntExtra("make_day", 0));
            TextView dateText = (TextView) findViewById(R.id.editTextDate);
            dateText.setText(intent.getStringExtra("event_date_string"));
        }
    }

    public void setDate(int y, int m, int d)
    {
        year = y; month = m; day = d;
    }

    public void openColorPicker(View view)
    {
        /*
        ColorPickerDialog cpick = new ColorPickerDialog();
        cpick.show(getSupportFragmentManager(), "COLOR_PICK");*/

        Intent intent = new Intent(this, ColorPickerPage.class);
       launchColorPicker.launch(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        setDate(year, month+1, day);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        dateToEdit.setText(selectedDate);
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
            intent.putExtra("make_year", year);
            intent.putExtra("make_month", month);
            intent.putExtra("make_day", day);
        }
        else
        {
            intent.putExtra("make_new", nametoPassBack);
            intent.putExtra("make_des", descriptionToPass);
            intent.putExtra("make_year", year);
            intent.putExtra("make_month", month);
            intent.putExtra("make_day", day);
        }

        setResult(RESULT_OK, intent); //RESULT_OK = -1 i believe
        finish();
    }

    ActivityResultLauncher<Intent> launchColorPicker = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == Activity.RESULT_OK)
        {
            Intent dataIntent = result.getData();
        }
    });
}