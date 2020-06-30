package ru.sc222.smartringapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.tasks.AddActionDbSaver;
import ru.sc222.smartringapp.ui.dialogs.ActionIconPickerDialog;
import ru.sc222.smartringapp.viewmodels.AddOrEditCommandViewModel;

public class AddCommandActivity extends AppCompatActivity {

    private AddOrEditCommandViewModel addOrEditCommandViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_command);
        Context c = this;
        addOrEditCommandViewModel =
                ViewModelProviders.of(this).get(AddOrEditCommandViewModel.class);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ActionIconPickerDialog actionIconPickerDialog = new ActionIconPickerDialog(c, addOrEditCommandViewModel);

        final LinearLayoutCompat linearLayoutLocationBg = findViewById(R.id.location_bg);
        linearLayoutLocationBg.setOnClickListener(v -> actionIconPickerDialog.show());

        addOrEditCommandViewModel.getIsCommandAdded().observe(this, isCommandAdded -> {
            if (isCommandAdded) {
                Intent parent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(parent);
            }
        });

        final AppCompatImageView imageViewCommandIcon = findViewById(R.id.command_selected_icon);

        addOrEditCommandViewModel
                .getSelectedIcon()
                .observe(this, integer -> imageViewCommandIcon.setImageResource(Action.icons[integer]));

        final TextInputLayout textFieldName = findViewById(R.id.text_field_name);
        final TextInputLayout textFieldDescription = findViewById(R.id.text_field_description);
        final TextInputLayout textFieldPhone = findViewById(R.id.text_field_phone);

        final AppCompatSpinner spinnerActionType = findViewById(R.id.spinner_action_type);
        final List<String> entries = new ArrayList<>();
        entries.add(Action.TYPE_HOME_CONTROL);
        entries.add(Action.TYPE_ALERT);
        setupSpinner(spinnerActionType, entries);
        spinnerActionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (entries.get(position).equals(Action.TYPE_HOME_CONTROL))
                    textFieldPhone.setVisibility(View.GONE);
                else if (entries.get(position).equals(Action.TYPE_ALERT))
                    textFieldPhone.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> tryToAddCommand(textFieldName, textFieldDescription, textFieldPhone, spinnerActionType));
    }

    private void tryToAddCommand(TextInputLayout textFieldName, TextInputLayout textFieldDescription, TextInputLayout textFieldPhone, AppCompatSpinner spinnerActionType) {
        String name = Objects.requireNonNull(textFieldName.getEditText()).getText().toString();
        String description = Objects.requireNonNull(textFieldDescription.getEditText()).getText().toString();
        String phone = Objects.requireNonNull(textFieldPhone.getEditText()).getText().toString();

        if (name.equals(""))
            textFieldName.setError(getString(R.string.set_action_name));
        else
            textFieldName.setErrorEnabled(false);

        if (description.equals(""))
            textFieldDescription.setError(getString(R.string.set_action_description));
        else
            textFieldDescription.setErrorEnabled(false);

        if (phone.equals(""))
            textFieldPhone.setError(getString(R.string.set_action_number));
        else
            textFieldPhone.setErrorEnabled(false);

        if (!name.equals("") && !description.equals("")) {
            String actionType = spinnerActionType.getSelectedItem().toString();
            Action action = null;
            if (actionType.equals(Action.TYPE_HOME_CONTROL))
                action = new Action(name, description, addOrEditCommandViewModel.getSelectedIcon().getValue(), actionType, "");
            if (actionType.equals(Action.TYPE_ALERT) && !phone.equals(""))
                action = new Action(name, description, addOrEditCommandViewModel.getSelectedIcon().getValue(), actionType, phone);
            if (action != null) {
                AddActionDbSaver addActionDbSaver = new AddActionDbSaver(addOrEditCommandViewModel, AppDatabase.getInstance(getApplicationContext()));
                addActionDbSaver.execute(action);
            }
        }
    }

    //todo move to utils
    private void setupSpinner(AppCompatSpinner spinner, List<String> entries) {
        // TODO CREATE CUSTOM SPINNER ITEM AND CUSTOM ARRAY ADAPTER
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, entries);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
