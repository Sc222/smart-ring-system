package ru.sc222.smartringapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.Location;

public class AddLocationActivity extends AppCompatActivity {

    private AddLocationViewModel addLocationViewModel;
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        c=this;
        addLocationViewModel =
                ViewModelProviders.of(this).get(AddLocationViewModel.class);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final LocationBgPickerDialog locationBgPickerDialog = new LocationBgPickerDialog(c,addLocationViewModel);

        final LinearLayoutCompat linearLayoutLocationBg = findViewById(R.id.location_bg);
        linearLayoutLocationBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationBgPickerDialog.show();
            }
        });


        addLocationViewModel.getIsLocationAdded().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLocationAdded) {
                if(isLocationAdded)
                {
                    //todo workaround, use go back to parent
                    Intent parent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(parent);
                    // AddLocationActivity.super.onBackPressed();
                    // finish(); //todo is it ok or i should launch parent activity manually?
                }
            }
        });

        final AppCompatImageView imageViewCurrentLocation = findViewById(R.id.location_selected_bg);

        addLocationViewModel.getSelectedBackground().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                imageViewCurrentLocation.setImageResource(Location.backgroundIcons[integer]);
            }
        });

        final AppCompatSpinner spinnerSingleClick = findViewById(R.id.spinner_single_click);
        final AppCompatSpinner spinnerDoubleClick = findViewById(R.id.spinner_double_click);
        final AppCompatSpinner spinnerTripleClick = findViewById(R.id.spinner_triple_click);
        final AppCompatSpinner spinnerLongPress = findViewById(R.id.spinner_long_press);



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
                    //todo workaround, use go back to parent
                    Intent parent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(parent);
                   // AddLocationActivity.super.onBackPressed();
                   // finish(); //todo is it ok or i should launch parent activity manually?
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
                            Location.backgrounds[addLocationViewModel.getSelectedBackground().getValue()],
                            spinnerSingleClick.getSelectedItem().toString(),
                            spinnerDoubleClick.getSelectedItem().toString(),
                            spinnerTripleClick.getSelectedItem().toString(),
                            spinnerLongPress.getSelectedItem().toString());

                    AddLocationDbSaver addLocationDbSaver = new AddLocationDbSaver(addLocationViewModel,AppDatabase.getInstance(getApplicationContext()));
                    addLocationDbSaver.execute(location);
                }
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
