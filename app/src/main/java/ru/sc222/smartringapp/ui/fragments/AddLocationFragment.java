package ru.sc222.smartringapp.ui.fragments;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class AddLocationFragment extends BottomSheetDialogFragment {


    /*BottomSheetBehavior bottomSheetBehavior;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        //inflating layout
        View view = View.inflate(getContext(), R.layout.add_location_fragment, null);

        //binding views to data binding.

        //setting layout with bottom sheet
        bottomSheet.setContentView(view);

        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));


        //setting Peek at the 16:9 ratio keyline of its parent.
        bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
        bottomSheetBehavior.setHideable(false);
      //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        //setting max height of bottom sheet
       // bi.extraSpace.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels) / 2);
        view.setMinimumHeight((int) (Resources.getSystem().getDisplayMetrics().heightPixels));


       /* bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (BottomSheetBehavior.STATE_EXPANDED == i) {
                    showView(bi.appBarLayout, getActionBarSize());
                    hideAppBar(bi.profileLayout);

                }
                if (BottomSheetBehavior.STATE_COLLAPSED == i) {
                    hideAppBar(bi.appBarLayout);
                    showView(bi.profileLayout, getActionBarSize());
                }

                if (BottomSheetBehavior.STATE_HIDDEN == i) {
                    dismiss();
                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        }

        //aap bar cancel button clicked
        bi.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        //aap bar edit button clicked
        bi.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Edit button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //aap bar more button clicked
        bi.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "More button clicked", Toast.LENGTH_SHORT).show();
            }
        });


        //hiding app bar at the start
        hideAppBar(bi.appBarLayout);
        return bottomSheet;
    }

    @Override
    public void onStart() {
        super.onStart();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }*/
}
