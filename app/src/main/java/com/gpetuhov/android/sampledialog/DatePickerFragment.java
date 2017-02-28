package com.gpetuhov.android.sampledialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

// Fragment for the dialog with date picker
public class DatePickerFragment extends DialogFragment {

    // Called when host activity's FragmentManager displays DialogFragment on screen
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Create new AlertDialog
        AlertDialog dateDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_dialog_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();

        return dateDialog;
    }
}
