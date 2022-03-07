package com.example.ontrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.rarepebble.colorpicker.ColorPickerView;

public class ColorPickerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker_page);
        Intent intent = getIntent();
        int firstColor = intent.getIntExtra("init_color", 0xff000000);
        ColorPickerView pick = (ColorPickerView) findViewById(R.id.colorPick);
        pick.setColor(firstColor);
    }

    public void saveColor(View view)
    {
        ColorPickerView pick = (ColorPickerView) findViewById(R.id.colorPick);
        int color = pick.getColor();
        Intent passBack = new Intent();
        passBack.putExtra("make_color", color);

        setResult(RESULT_OK, passBack);
        finish();
    }
}