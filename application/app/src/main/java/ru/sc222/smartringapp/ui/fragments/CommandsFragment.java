package ru.sc222.smartringapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;
import ru.sc222.smartringapp.db.tasks.CommandsDbLoader;
import ru.sc222.smartringapp.ui.activities.AddCommandActivity;
import ru.sc222.smartringapp.viewmodels.CommandsViewModel;

public class CommandsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CommandsViewModel commandsViewModel = ViewModelProviders.of(this).get(CommandsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_commands, container, false);

        //todo replace with recyclerlayout in the future
        final LinearLayoutCompat cardContainer = root.findViewById(R.id.card_container);
        commandsViewModel.getActions().observe(getViewLifecycleOwner(), actions -> {
            assert actions != null;
            for (Action action : actions) {
                View card = LayoutInflater.from(getContext()).inflate(R.layout.card_command, cardContainer, false);
                AppCompatImageView imageViewIcon = card.findViewById(R.id.icon);
                AppCompatTextView textViewCategory = card.findViewById(R.id.text_category);
                AppCompatTextView textViewDescription = card.findViewById(R.id.text_description);
                imageViewIcon.setImageResource(Action.icons[action.icon]);
                textViewCategory.setText(action.actionCategory);
                textViewDescription.setText(action.actionDescription);

                if (action.actionType.equals(Action.TYPE_HOME_CONTROL)) {
                    textViewCategory.setTextColor(getResources().getColor(R.color.colorPrimary));
                    imageViewIcon.setColorFilter(getResources().getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);

                } else if (action.actionType.equals(Action.TYPE_ALERT)) {
                    textViewCategory.setTextColor(getResources().getColor(R.color.colorWarning));
                    imageViewIcon.setColorFilter(getResources().getColor(R.color.colorWarning), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                cardContainer.addView(card);
            }
        });

        FloatingActionButton fabAdd = root.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddCommandActivity.class);
            startActivity(intent);
        });

        CommandsDbLoader commandsLoader = new CommandsDbLoader(commandsViewModel, AppDatabase.getInstance(getContext()));
        commandsLoader.execute();
        return root;
    }
}