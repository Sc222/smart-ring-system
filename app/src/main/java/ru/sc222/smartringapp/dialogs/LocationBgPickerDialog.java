package ru.sc222.smartringapp.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.sc222.smartringapp.AddOrEditLocationViewModel;
import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.Location;

public class LocationBgPickerDialog extends AlertDialog {

    private Context c;
    private AddOrEditLocationViewModel addOrEditLocationViewModel;

    public LocationBgPickerDialog(Context c, final AddOrEditLocationViewModel addOrEditLocationViewModel) {
        super(c);
        this.c=c;
        this.addOrEditLocationViewModel=addOrEditLocationViewModel;
    }

    @Override
    public void show() {
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
                addOrEditLocationViewModel.setSelectedBackground(position);
                Log.e("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
            }
        });
        setView(layout);
        super.show();
    }
}
