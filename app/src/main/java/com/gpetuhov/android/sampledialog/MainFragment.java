package com.gpetuhov.android.sampledialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

// Main fragment (displayed when app starts)
public class MainFragment extends Fragment {

    // ID of the fragment with date dialog in FragmentManager's list
    public static final String DIALOG_DATE_TAG = "DialogDateTag";

    @BindView(R.id.date_button) Button mDateButton;

    // Keeps ButterKnife Unbinder object to properly unbind views in onDestroyView of the fragment
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // Bind views and save reference to Unbinder object
        mUnbinder = ButterKnife.bind(this, v);

        return v;
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

        // Create new fragment with date dialog
        DatePickerFragment datePickerFragment = new DatePickerFragment();

        // Add fragment into FragmentManager and show fragment on screen
        datePickerFragment.show(manager, DIALOG_DATE_TAG);
    }
}
