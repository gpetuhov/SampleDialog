package com.gpetuhov.android.sampledialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

// Fragment for the dialog with date picker
public class DatePickerFragment extends DialogFragment {

    // Key for fragment's argument with date
    public static final String ARG_DATE_KEY = "ArgDateKey";

    // Key for saving date inside intent
    public static final String EXTRA_DATE = "com.gpetuhov.android.sampledialog.date";

    @BindView(R.id.dialog_date_date_picker)
    DatePicker mDatePicker;

    // Keeps ButterKnife Unbinder object to properly unbind views in onDestroyView of the fragment
    private Unbinder mUnbinder;

    // Return new instance of this fragment and attach arguments to it
    public static DatePickerFragment newInstance(Date date) {

        // Create new empty Bundle object for fragment arguments
        Bundle args = new Bundle();

        // Put date into Bundle object
        args.putSerializable(ARG_DATE_KEY, date);

        // Create new instance of this fragment
        DatePickerFragment fragment = new DatePickerFragment();

        // Attach arguments to fragment
        fragment.setArguments(args);

        return fragment;
    }

    // Called when host activity's FragmentManager displays DialogFragment on screen
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get date from the fragment's arguments
        Date date = (Date) getArguments().getSerializable(ARG_DATE_KEY);

        // Extract year, month and day values from the date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create view with date picker
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        // Bind views and save reference to Unbinder object
        mUnbinder = ButterKnife.bind(this, v);

        // Initialize date picker with values extracted from the date
        mDatePicker.init(year, month, day, null);

        // Create new AlertDialog with this view
        AlertDialog dateDialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_dialog_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Get year, month and day values from the date picker
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();

                        // Create date from these values
                        Date date = new GregorianCalendar(year, month, day).getTime();

                        // Send date to target fragment
                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                .create();

        return dateDialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // This is recommended to do here when using Butterknife in fragments
        mUnbinder.unbind();
    }

    private void sendResult(int resultCode, Date date) {

        // If there is no target fragment, do nothing and return
        if (getTargetFragment() == null) {
            return;
        }

        // Create new intent and put date into it
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        // Invoke target fragment's onActivityResult() with this intent
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
