package com.gpetuhov.android.sampledialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

// Fragment for the dialog with date picker
public class DatePickerFragment extends DialogFragment {

    // Called when host activity's FragmentManager displays DialogFragment on screen
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Create view with date picker
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        // Create new AlertDialog with this view
        AlertDialog dateDialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_dialog_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();

        return dateDialog;
    }
}
