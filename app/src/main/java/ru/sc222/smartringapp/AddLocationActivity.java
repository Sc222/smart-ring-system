package ru.sc222.smartringapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        final Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        AppCompatSpinner spinnerBackground = findViewById(R.id.spinner_background);
        List<String> backgrounds = new ArrayList<>();
        backgrounds.add("Основной цвет");
        backgrounds.add("Лес");
        backgrounds.add("Поле");
        setupSpinner(spinnerBackground, backgrounds);

        AppCompatSpinner spinnerSingleClick = findViewById(R.id.spinner_single_click);
        AppCompatSpinner spinnerDoubleClick = findViewById(R.id.spinner_double_click);
        AppCompatSpinner spinnerTripleClick = findViewById(R.id.spinner_triple_click);
        AppCompatSpinner spinnerLongPress = findViewById(R.id.spinner_long_press);
        List<String> actions = new ArrayList<>();
        actions.add("Не назначено");
        actions.add("Включить свет");
        actions.add("Поставить чайник");
        actions.add("Включить телевизор");
        actions.add("Включить компьютер");
        setupSpinner(spinnerSingleClick, actions);
        setupSpinner(spinnerDoubleClick, actions);
        setupSpinner(spinnerTripleClick, actions);
        setupSpinner(spinnerLongPress, actions);


        final TextInputLayout textFieldName = findViewById(R.id.text_field_name);
        final TextInputLayout textFieldLocation = findViewById(R.id.text_field_location);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textFieldName.getEditText().getText().toString();
                String location = textFieldLocation.getEditText().getText().toString();

                if (name.equals("")) {
                    textFieldName.setError("Укажите название локации");
                } else {
                    textFieldName.setErrorEnabled(false);
                }

                if (location.equals("")) {
                    textFieldLocation.setError("Укажите местоположение локации");
                } else {
                    textFieldLocation.setErrorEnabled(false);
                }


                //processInput();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void setupSpinner(AppCompatSpinner spinner, List<String> entries) {
        // TODO CREATE CUSTOM SPINNER ITEM AND CUSTOM ARRAY ADAPTER
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, entries);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

}
