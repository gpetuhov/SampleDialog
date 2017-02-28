package com.gpetuhov.android.sampledialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

// Main fragment (displayed when app starts)
public class MainFragment extends Fragment {

    // ID of the fragment with date dialog in FragmentManager's list
    public static final String DIALOG_DATE_TAG = "DialogDateTag";

    // Request code for returning results from date dialog to this fragment
    public static final int DATE_REQUEST_CODE = 0;

    // Button displays date dialog
    @BindView(R.id.date_button) Button mDateButton;

    // Keeps ButterKnife Unbinder object to properly unbind views in onDestroyView of the fragment
    private Unbinder mUnbinder;

    private Date mDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // Bind views and save reference to Unbinder object
        mUnbinder = ButterKnife.bind(this, v);

        // Create new date (by default new date's value is set to current date)
        mDate = new Date();

        // Display current date inside date button
        setDateButtonText(mDate);

        return v;
    }

    // Display date inside date button
    private void setDateButtonText(Date date) {
        // Format date to convenient format and set as button label
        mDateButton.setText(DateFormat.format("EEEE, MMM dd, yyyy", mDate));
    }

    // Called by date dialog fragment to return results
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // If result is not OK, do nothing and return
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        // Check if the result comes from date dialog fragment
        if (requestCode == DATE_REQUEST_CODE) {
            // Get date from the intent
            mDate = (Date) intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            // Display date inside date button
            setDateButtonText(mDate);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // This is recommended to do here when using Butterknife in fragments
        mUnbinder.unbind();
    }

    // Called when date button is clicked
    @OnClick(R.id.date_button)
    public void setDate() {
        // Get fragment manager of the host of this fragment
        // (in our case - host activity's fragment manaager).
        FragmentManager manager = getFragmentManager();

        // Create new fragment with date dialog and pass date as fragment's arguments
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mDate);

        // Set this MainFragment as target fragment for date dialog fragment
        // (this is needed to return results from date dialog fragment to MainFragment).
        // This connection is managed by FragmentManager.
        datePickerFragment.setTargetFragment(MainFragment.this, DATE_REQUEST_CODE);

        // Add fragment into FragmentManager and show fragment on screen
        datePickerFragment.show(manager, DIALOG_DATE_TAG);
    }
}
