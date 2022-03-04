package com.example.ontrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rarepebble.colorpicker.ColorPickerView;

public class ColorPickerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker_page);
        Intent intent = getIntent();
        ColorPickerView pick = (ColorPickerView) findViewById(R.id.colorPick);
        pick.setColor(0xffff0000);
    }

    public void saveColor(View view)
    {
        finish();
    }
}