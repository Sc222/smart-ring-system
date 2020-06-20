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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import ru.sc222.smartringapp.core.Geoposition;
import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.ui.dialogs.LocationBgPickerDialog;
import ru.sc222.smartringapp.ui.dialogs.LocationGeolocationPickerDialog;

public class AddLocationActivity extends AppCompatActivity {

    private AddOrEditLocationViewModel addOrEditLocationViewModel;
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        c=this;
        addOrEditLocationViewModel =
                ViewModelProviders.of(this).get(AddOrEditLocationViewModel.class);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final LocationBgPickerDialog locationBgPickerDialog = new LocationBgPickerDialog(c, addOrEditLocationViewModel);

        final LinearLayoutCompat linearLayoutLocationBg = findViewById(R.id.location_bg);
        linearLayoutLocationBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationBgPickerDialog.show();
            }
        });


        addOrEditLocationViewModel.getIsLocationAdded().observe(this, new Observer<Boolean>() {
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

        addOrEditLocationViewModel.getSelectedBackground().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                imageViewCurrentLocation.setImageResource(Location.backgroundIcons[integer]);
            }
        });

        final AppCompatSpinner spinnerSingleClick = findViewById(R.id.spinner_single_click);
        final AppCompatSpinner spinnerDoubleClick = findViewById(R.id.spinner_double_click);
        final AppCompatSpinner spinnerTripleClick = findViewById(R.id.spinner_triple_click);
        final AppCompatSpinner spinnerLongPress = findViewById(R.id.spinner_long_press);



        addOrEditLocationViewModel.getActions().observe(this, new Observer<List<Action>>() {
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

        addOrEditLocationViewModel.getIsLocationAdded().observe(this, new Observer<Boolean>() {
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
        AddLocationDbLoader addLocationDbLoader = new AddLocationDbLoader(addOrEditLocationViewModel, AppDatabase.getInstance(this));
        addLocationDbLoader.execute();

        final TextInputLayout textFieldName = findViewById(R.id.text_field_name);
        final TextInputLayout textFieldLocation = findViewById(R.id.text_field_location);
        final TextInputEditText textFieldLocationEditText = findViewById(R.id.text_input_location);


        final LocationGeolocationPickerDialog geolocationPickerDialog = new LocationGeolocationPickerDialog(c, addOrEditLocationViewModel);
        textFieldLocationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo DEBUG
                //todo LOCATION CODE HERE
                //TODO PASS VIEW MODEL
              //  Intent intent = new Intent(c, GeolocationPickerActivity.class);
              //  startActivity(intent);
                geolocationPickerDialog.show(getSupportFragmentManager(),null);
            }
        });

        //todo observers setup in separate method
        addOrEditLocationViewModel.getGeoposition().observe(this, new Observer<Geoposition>() {
            @Override
            public void onChanged(Geoposition geoposition) {
                if(geoposition!=null)
                    textFieldLocationEditText.setText(geoposition.getAddress());
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TryToAddLocation(textFieldName, textFieldLocation, spinnerSingleClick, spinnerDoubleClick, spinnerTripleClick, spinnerLongPress);
            }
        });
    }

    private void TryToAddLocation(TextInputLayout textFieldName, TextInputLayout textFieldLocation, AppCompatSpinner spinnerSingleClick, AppCompatSpinner spinnerDoubleClick, AppCompatSpinner spinnerTripleClick, AppCompatSpinner spinnerLongPress) {
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
                    addOrEditLocationViewModel.getGeoposition().getValue(),
                    addOrEditLocationViewModel.getSelectedBackground().getValue(),
                    spinnerSingleClick.getSelectedItem().toString(),
                    spinnerDoubleClick.getSelectedItem().toString(),
                    spinnerTripleClick.getSelectedItem().toString(),
                    spinnerLongPress.getSelectedItem().toString());

            AddLocationDbSaver addLocationDbSaver = new AddLocationDbSaver(addOrEditLocationViewModel, AppDatabase.getInstance(getApplicationContext()));
            addLocationDbSaver.execute(location);
        }
    }

    private void setupSpinner(AppCompatSpinner spinner, List<String> entries) {
        // TODO CREATE CUSTOM SPINNER ITEM AND CUSTOM ARRAY ADAPTER
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, entries);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

}
