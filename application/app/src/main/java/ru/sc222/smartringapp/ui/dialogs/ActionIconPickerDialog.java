package ru.sc222.smartringapp.ui.dialogs;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.viewmodels.AddOrEditCommandViewModel;

public class ActionIconPickerDialog extends AlertDialog {

    private Context c;
    private AddOrEditCommandViewModel addOrEditCommandViewModel;

    public ActionIconPickerDialog(Context c, final AddOrEditCommandViewModel addOrEditCommandViewModel) {
        super(c);
        this.c = c;
        this.addOrEditCommandViewModel = addOrEditCommandViewModel;
    }

    @Override
    public void show() {
        final View layout = ((Activity) c).getLayoutInflater().inflate(R.layout.recycler_view_dialog, null);
        setTitle(R.string.command_icon_picker_title);
        RecyclerView recyclerView = layout.findViewById(R.id.recyclerViewDialog);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(c, numberOfColumns));
        final ImageGridRecyclerAdapter adapter = new ImageGridRecyclerAdapter(c, Action.icons);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new ImageGridRecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                dismiss();
                addOrEditCommandViewModel.setSelectedIcon(position);
                Log.e("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
            }
        });
        setView(layout);
        super.show();
    }
}
