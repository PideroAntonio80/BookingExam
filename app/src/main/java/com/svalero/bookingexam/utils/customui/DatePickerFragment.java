package com.svalero.bookingexam.utils.customui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import com.svalero.bookingexam.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private OnDateChangeListener onDateChangeListener;

    public interface OnDateChangeListener{
        void onDateChange(int year, int month, int dayOfMonth);
    }

    public void setOnDateChangeListener(OnDateChangeListener onDateChangeListener) {
        this.onDateChangeListener = onDateChangeListener;
    }

    public static DatePickerFragment newInstance(OnDateChangeListener onDateChangeListener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setOnDateChangeListener(onDateChangeListener);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(onDateChangeListener != null) {
            onDateChangeListener.onDateChange(year, month, dayOfMonth);
        }
    }
}
