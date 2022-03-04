package com.example.ontrack;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.rarepebble.colorpicker.ColorPickerView;

public class ColorPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_pick);
        Intent intent = getIntent();
        /* ColorPickerView pick = (ColorPickerView) findViewById(R.id.colorPick);
        pick.setColor(0xffff0000);*/
    }
}
