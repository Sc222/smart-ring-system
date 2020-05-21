package ru.sc222.smartringapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.Location;

public class AddLocationActivity extends AppCompatActivity {

    private AddLocationViewModel addLocationViewModel;

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

        final AppCompatSpinner spinnerSingleClick = findViewById(R.id.spinner_single_click);
        final AppCompatSpinner spinnerDoubleClick = findViewById(R.id.spinner_double_click);
        final AppCompatSpinner spinnerTripleClick = findViewById(R.id.spinner_triple_click);
        final AppCompatSpinner spinnerLongPress = findViewById(R.id.spinner_long_press);


        //get actions
        addLocationViewModel =
                ViewModelProviders.of(this).get(AddLocationViewModel.class);

        addLocationViewModel.getActions().observe(this, new Observer<List<Action>>() {
            @Override
            public void onChanged(@Nullable List<Action> actions) {
                List<String> actionDescriptions = new ArrayList<>(); //todo code it using linq
                actionDescriptions.add(Action.NOT_DEFINED);
                for (Action action : actions) {
                    actionDescriptions.add(action.actionDescription);
                }
                //set actions when they are loaded from db
                setupSpinner(spinnerSingleClick, actionDescriptions);
                setupSpinner(spinnerDoubleClick, actionDescriptions);
                setupSpinner(spinnerTripleClick, actionDescriptions);
                setupSpinner(spinnerLongPress, actionDescriptions);
            }
        });

        addLocationViewModel.getIsLocationAdded().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLocationAdded) {
                if(isLocationAdded)
                {
                    finish(); //todo is it ok or i should launch parent activity manually?
                }
            }
        });

        //load actions from db
        AddLocationDbLoader addLocationDbLoader = new AddLocationDbLoader(addLocationViewModel, AppDatabase.getInstance(this));
        addLocationDbLoader.execute();

        final TextInputLayout textFieldName = findViewById(R.id.text_field_name);
        final TextInputLayout textFieldLocation = findViewById(R.id.text_field_location);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textFieldName.getEditText().getText().toString();
                String address = textFieldLocation.getEditText().getText().toString();

                if (name.equals("")) {
                    textFieldName.setError("Укажите название локации");
                } else {
                    textFieldName.setErrorEnabled(false);
                }

                if (address.equals("")) {
                    textFieldLocation.setError("Укажите местоположение локации");
                } else {
                    textFieldLocation.setErrorEnabled(false);
                }

                if(!name.equals("")&&!address.equals("")) {
                    Location location = new Location(
                            name,
                            address,
                            R.color.colorPrimary,
                            spinnerSingleClick.getSelectedItem().toString(),
                            spinnerDoubleClick.getSelectedItem().toString(),
                            spinnerTripleClick.getSelectedItem().toString(),
                            spinnerLongPress.getSelectedItem().toString());

                    AddLocationDbSaver addLocationDbSaver = new AddLocationDbSaver(addLocationViewModel,AppDatabase.getInstance(getApplicationContext()));
                    addLocationDbSaver.execute(location);
                }

                //processInput();
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
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
