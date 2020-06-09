package ru.sc222.smartringapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.ui.locations.LocationsViewModel;

public class LocationBgPickerDialog extends AlertDialog {

    LocationBgPickerDialog(Context c, final AddLocationViewModel addLocationViewModel) {
        super(c);
        setTitle("Profile");
        final View layout = ((Activity) c).getLayoutInflater().inflate(R.layout.location_bg_dialog, null);
        setTitle(R.string.location_bg_picker_title);
        RecyclerView recyclerView = layout.findViewById(R.id.recyclerViewLocationBg);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(c, numberOfColumns));
        final LocationBgRecyclerAdapter adapter = new LocationBgRecyclerAdapter(c, Location.backgroundIcons);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new LocationBgRecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                dismiss();
                addLocationViewModel.setSelectedBackground(position);
                 Log.e("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
            }
        });
        setView(layout);
    }

}
