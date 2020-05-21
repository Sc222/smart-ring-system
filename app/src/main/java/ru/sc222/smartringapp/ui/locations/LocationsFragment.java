package ru.sc222.smartringapp.ui.locations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.sc222.smartringapp.AddLocationActivity;
import ru.sc222.smartringapp.AddLocationFragment;
import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.SettingsActivity;

public class LocationsFragment extends Fragment {

    private LocationsViewModel locationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        locationsViewModel =
                ViewModelProviders.of(this).get(LocationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_locations, container, false);
        FloatingActionButton fabAdd = root.findViewById(R.id.fab_add);
        Log.e("fragment","RELOAD DATA");
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddLocationActivity.class);
                startActivity(intent);
               // AddLocationFragment bottomSheetFragment = new AddLocationFragment();
               // bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());

            }
        });
        //final TextView textView = root.findViewById(R.id.text_locations);
        //locationsViewModel.getText().observe(this, new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});
        return root;
    }

   // View view = getLayoutInflater().inflate(R.layout.add_location_fragment, null);

   // AddLocationFragment dialog = new AddLocationFragment(this);
   //     dialog.setContentView(view);
   //     dialog.show();
}