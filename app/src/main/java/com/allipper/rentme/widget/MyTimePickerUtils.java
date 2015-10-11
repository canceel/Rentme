package com.allipper.rentme.widget;

import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.allipper.rentme.R;

import java.lang.reflect.Field;

/**
 * Created by allipper on 2015/9/25.
 */
public class MyTimePickerUtils {

    public static void changeDivider(TimePickerDialog timePickerDialog) {
        TimePicker timePicker = null;
        Field f = null; //NoSuchFieldException
        try {
            f = timePickerDialog.getClass().getDeclaredField("mTimePicker");
            f.setAccessible(true);
            timePicker = (TimePicker) f.get(timePickerDialog); //IllegalAccessException
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Class<?> internalRID = null;
        try {
            internalRID = Class.forName("com.android.internal.R$id");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Field hour = null;
        try {
            hour = internalRID.getField("hour");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        NumberPicker npMonth = null;
        try {
            npMonth = (NumberPicker) timePicker.findViewById(hour.getInt(null));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field minute = null;
        try {
            minute = internalRID.getField("minute");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        NumberPicker npDay = null;
        try {
            npDay = (NumberPicker) timePicker.findViewById(minute.getInt(null));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field amPm = null;
        try {
            amPm = internalRID.getField("amPm");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        NumberPicker npYear = null;
        try {
            npYear = (NumberPicker) timePicker.findViewById(amPm.getInt(null));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Class<?> numberPickerClass = null;
        try {
            numberPickerClass = Class.forName("android.widget.NumberPicker");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Field selectionDivider = null;
        try {
            selectionDivider = numberPickerClass.getDeclaredField("mSelectionDivider");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            selectionDivider.setAccessible(true);
            selectionDivider.set(npMonth, timePicker.getResources().getDrawable(R.color
                    .title_background));
            selectionDivider.set(npDay, timePicker.getResources().getDrawable(R.color
                    .title_background));
            selectionDivider.set(npYear, timePicker.getResources().getDrawable(R.color
                    .title_background));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}