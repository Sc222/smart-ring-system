package ru.sc222.smartringapp.ui.commands;

import android.os.Bundle;
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

        //todo replace with recyclerlayout in the future
        final LinearLayoutCompat cardContainer = root.findViewById(R.id.card_container);
        commandsViewModel.getActions().observe(getViewLifecycleOwner(), new Observer<List<Action>>() {
            @Override
            public void onChanged(@Nullable List<Action> actions) {
                for (Action action : actions) {
                    View card = LayoutInflater.from(getContext()).inflate(R.layout.card_command, cardContainer, false);
                    AppCompatImageView imageView=card.findViewById(R.id.icon);
                    AppCompatTextView textViewCategory=card.findViewById(R.id.text_category);
                    AppCompatTextView textViewDescription=card.findViewById(R.id.text_description);
                    imageView.setImageResource(action.icon);
                    textViewCategory.setText(action.actionCategory);
                    textViewDescription.setText(action.actionDescription);
                    cardContainer.addView(card);
                    //todo code here
                }
                //if(actions.size()>0)
                //    textView.setText(actions.get(0).actionName);
            }
        });


        CommandsDbLoader commandsLoader = new CommandsDbLoader(commandsViewModel, AppDatabase.getInstance(getContext()));
        commandsLoader.execute();
        return root;
    }
}