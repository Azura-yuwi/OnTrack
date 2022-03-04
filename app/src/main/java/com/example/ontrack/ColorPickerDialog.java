package com.example.ontrack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.fragment.app.DialogFragment;

import com.rarepebble.colorpicker.ColorPickerView;

public class ColorPickerDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose a Color");
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(com.rarepebble.colorpicker.R.layout.picker, null));



        builder.setPositiveButton("Save", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
           public void onClick(DialogInterface dialog, int id)
           {

           }
        });

        return builder.create();
    }

    public void buildTheView()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    }

}
