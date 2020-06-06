package ru.sc222.smartringapp.ui.locations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.sc222.smartringapp.AddLocationActivity;
import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.ui.commands.CommandsDbLoader;

public class LocationsFragment extends Fragment {

    private LocationsViewModel locationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        locationsViewModel =
                ViewModelProviders.of(this).get(LocationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_locations, container, false);
        FloatingActionButton fabAdd = root.findViewById(R.id.fab_add);
        Log.e("fragment", "RELOAD DATA");
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddLocationActivity.class);
                startActivity(intent);
                // AddLocationFragment bottomSheetFragment = new AddLocationFragment();
                // bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        //todo replace with recyclerlayout in the future
        final LinearLayoutCompat cardContainer = root.findViewById(R.id.card_container);
        locationsViewModel.getLocations().observe(getViewLifecycleOwner(), new Observer<List<Location>>() {
            @Override
            public void onChanged(@Nullable List<Location> locations) {
                Log.e("locations updated","!!!UPDATED!!!");
                for (Location location : locations) {
                    View card = LayoutInflater.from(getContext()).inflate(R.layout.card_location, cardContainer, false);
                    AppCompatImageView imageView=card.findViewById(R.id.location_bg);
                    AppCompatTextView textViewName=card.findViewById(R.id.text_name);
                    AppCompatTextView textViewAddress=card.findViewById(R.id.text_address);
                    AppCompatTextView textViewCommandsCount=card.findViewById(R.id.text_commands_count);
                    imageView.setImageResource(location.locationBackground);
                    textViewName.setText(location.locationName);
                    textViewAddress.setText(location.locationAddress);
                    if(location.getCommandsCount()==0) //todo workaround remove later
                        textViewCommandsCount.setText(String.format("%d команд", location.getCommandsCount()));
                    else
                        textViewCommandsCount.setText(String.format(getString(R.string.format_commands_count), location.getCommandsCount()));
                    cardContainer.addView(card);
                }
            }
        });

        LocationsDbLoader locationsDbLoader = new LocationsDbLoader(locationsViewModel, AppDatabase.getInstance(getContext()));
        locationsDbLoader.execute();

        return root;
    }

    // View view = getLayoutInflater().inflate(R.layout.add_location_fragment, null);

    // AddLocationFragment dialog = new AddLocationFragment(this);
    //     dialog.setContentView(view);
    //     dialog.show();
}