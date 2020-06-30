package ru.sc222.smartringapp.ui.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.Location;
import ru.sc222.smartringapp.ui.adapters.ImageGridRecyclerAdapter;
import ru.sc222.smartringapp.viewmodels.AddOrEditLocationViewModel;

public class LocationBgPickerDialog extends AlertDialog {

    private Context c;
    private AddOrEditLocationViewModel addOrEditLocationViewModel;

    public LocationBgPickerDialog(Context c, final AddOrEditLocationViewModel addOrEditLocationViewModel) {
        super(c);
        this.c = c;
        this.addOrEditLocationViewModel = addOrEditLocationViewModel;
    }

    @Override
    public void show() {
        final View layout = ((Activity) c).getLayoutInflater().inflate(R.layout.recycler_view_dialog, null);
        setTitle(R.string.location_bg_picker_title);
        RecyclerView recyclerView = layout.findViewById(R.id.recyclerViewDialog);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(c, numberOfColumns));
        final ImageGridRecyclerAdapter adapter = new ImageGridRecyclerAdapter(c, Location.backgroundIcons);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener((view, position) -> {
            dismiss();
            addOrEditLocationViewModel.setSelectedBackground(position);
        });
        setView(layout);
        super.show();
    }
}
