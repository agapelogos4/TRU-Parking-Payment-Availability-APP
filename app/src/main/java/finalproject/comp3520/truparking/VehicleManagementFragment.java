// File: VehicleManagementFragment.java
// Author: Zechariah Montgomery
// Description: manages the display and deletion of license plates in the users settings
// Date: [09/07/2025]

package finalproject.comp3520.truparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class VehicleManagementFragment extends BottomSheetDialogFragment
{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        // Inflate your layout and return the root view
        return inflater.inflate(R.layout.fragment_vehicle_management, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        TextView noLicenseText = view.findViewById(R.id.noLicenseText);
        LinearLayout licenseRow1 = view.findViewById(R.id.licenseRow1);
        LinearLayout licenseRow2 = view.findViewById(R.id.licenseRow2);
        LinearLayout licenseRow3 = view.findViewById(R.id.licenseRow3);
        LinearLayout licenseRow4 = view.findViewById(R.id.licenseRow4);

        LinearLayout separator1 = view.findViewById(R.id.Separator1);
        LinearLayout separator2 = view.findViewById(R.id.Separator2);
        LinearLayout separator3 = view.findViewById(R.id.Separator3);

        licenseRow1.setVisibility(View.VISIBLE);
        licenseRow2.setVisibility(View.VISIBLE);
        licenseRow3.setVisibility(View.GONE);
        licenseRow4.setVisibility(View.GONE);

        //show/hide separators based on which license rows are visible
        if (licenseRow1.getVisibility() == View.VISIBLE && (licenseRow2.getVisibility() == View.VISIBLE || licenseRow3.getVisibility() == View.VISIBLE || licenseRow4.getVisibility() == View.VISIBLE))
        {
            separator1.setVisibility(View.VISIBLE);
        }
        else
        {
            separator1.setVisibility(View.GONE);
        }

        if (licenseRow2.getVisibility() == View.VISIBLE && (licenseRow3.getVisibility() == View.VISIBLE || licenseRow4.getVisibility() == View.VISIBLE))
        {
            separator2.setVisibility(View.VISIBLE);
        }
        else
        {
            separator2.setVisibility(View.GONE);
        }

        if (licenseRow3.getVisibility() == View.VISIBLE && licenseRow4.getVisibility() == View.VISIBLE)
        {
            separator3.setVisibility(View.VISIBLE);
        }
        else
        {
            separator3.setVisibility(View.GONE);
        }

        if (licenseRow1.getVisibility() == View.GONE && licenseRow2.getVisibility() == View.GONE && licenseRow3.getVisibility() == View.GONE && licenseRow4.getVisibility() == View.GONE)
        {
            noLicenseText.setVisibility(View.VISIBLE);
        }
        else
        {
            noLicenseText.setVisibility(View.GONE);
        }

        //if the delete button is pressed for a license plate then show the delete dialog
        view.findViewById(R.id.deleteButton1).setOnClickListener(v ->
        {
            TextView plateText = view.findViewById(R.id.licenseText1);
            showDeletePopup(view, licenseRow1, plateText.getText().toString());
        });

        view.findViewById(R.id.deleteButton2).setOnClickListener(v ->
        {
            TextView plateText = view.findViewById(R.id.licenseText2);
            showDeletePopup(view, licenseRow2, plateText.getText().toString());
        });

        view.findViewById(R.id.deleteButton3).setOnClickListener(v ->
        {
            TextView plateText = view.findViewById(R.id.licenseText3);
            showDeletePopup(view, licenseRow3, plateText.getText().toString());
        });

        view.findViewById(R.id.deleteButton4).setOnClickListener(v ->
        {
            TextView plateText = view.findViewById(R.id.licenseText4);
            showDeletePopup(view, licenseRow4, plateText.getText().toString());
        });
    }

    //displays a confirmation dialog before removing a license plate
    private void showDeletePopup(View rootView, LinearLayout row, String licensePlate)
    {
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete License Plate").setMessage("Are you sure you want to delete this license plate?\n\n" + licensePlate).setPositiveButton("Delete", (dialog, which) ->
                {
                    row.setVisibility(View.GONE); //set the rows visibility to gone so it does not appear
                    checkTheSplitter(rootView, row); //hide splitters if need be
                    checkIfNoPlatesRemain(rootView); //show no license plate message if need be
                }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).show();
    }

    //checks if all license rows are hidden shows "No license plates" message if needed
    private void checkIfNoPlatesRemain(View view)
    {
        TextView noLicenseText = view.findViewById(R.id.noLicenseText);

        LinearLayout licenseRow1 = view.findViewById(R.id.licenseRow1);
        LinearLayout licenseRow2 = view.findViewById(R.id.licenseRow2);
        LinearLayout licenseRow3 = view.findViewById(R.id.licenseRow3);
        LinearLayout licenseRow4 = view.findViewById(R.id.licenseRow4);

        //does it need to show the no license plate text
        if (licenseRow1.getVisibility() == View.GONE && licenseRow2.getVisibility() == View.GONE && licenseRow3.getVisibility() == View.GONE && licenseRow4.getVisibility() == View.GONE)
        {
            noLicenseText.setVisibility(View.VISIBLE);
        }
        else
        {
            noLicenseText.setVisibility(View.GONE);
        }
    }

    //updates the separators after a license plate is deleted
    private void checkTheSplitter(View view, LinearLayout row)
    {
        LinearLayout licenseRow1 = view.findViewById(R.id.licenseRow1);
        LinearLayout licenseRow2 = view.findViewById(R.id.licenseRow2);
        LinearLayout licenseRow3 = view.findViewById(R.id.licenseRow3);
        LinearLayout licenseRow4 = view.findViewById(R.id.licenseRow4);

        LinearLayout separator1 = view.findViewById(R.id.Separator1);
        LinearLayout separator2 = view.findViewById(R.id.Separator2);
        LinearLayout separator3 = view.findViewById(R.id.Separator3);

        //remove visibility of splitter if the rows above or below are gone
        if ((row == licenseRow1 && separator1.getVisibility() == View.VISIBLE) || (licenseRow2.getVisibility() == View.GONE && licenseRow3.getVisibility() == View.GONE && licenseRow4.getVisibility() == View.GONE))
        {
            separator1.setVisibility(View.GONE);
        }
        else if ((row == licenseRow2 && separator2.getVisibility() == View.VISIBLE) || (licenseRow3.getVisibility() == View.GONE && licenseRow4.getVisibility() == View.GONE))
        {
            separator2.setVisibility(View.GONE);
        }
        else if ((row == licenseRow3 && separator3.getVisibility() == View.VISIBLE) || (licenseRow4.getVisibility() == View.GONE))
        {
            separator3.setVisibility(View.GONE);
        }
    }

}