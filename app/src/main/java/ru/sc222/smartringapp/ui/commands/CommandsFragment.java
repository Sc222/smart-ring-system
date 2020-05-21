package ru.sc222.smartringapp.ui.commands;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import ru.sc222.smartringapp.R;
import ru.sc222.smartringapp.db.Action;
import ru.sc222.smartringapp.db.AppDatabase;

public class CommandsFragment extends Fragment {

    private CommandsViewModel commandsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        commandsViewModel =
                ViewModelProviders.of(this).get(CommandsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_commands, container, false);
        final TextView textView = root.findViewById(R.id.text_commands);
        commandsViewModel.getActions().observe(getViewLifecycleOwner(), new Observer<List<Action>>() {
            @Override
            public void onChanged(@Nullable List<Action> actions) {
                if(actions.size()>0)
                    textView.setText(actions.get(0).actionName);
            }
        });


        CommandsLoader commandsLoader= new CommandsLoader(commandsViewModel, AppDatabase.getInstance(getContext()));
        commandsLoader.execute();
        return root;
    }
}