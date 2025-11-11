// File: VehicleManagementFragment.java
// Author: Zechariah Montgomery
// Description: manages the display and deletion of license plates in the users settings
// Date: [09/07/2025]

package finalproject.comp3520.truparking;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class VehicleManagementFragment extends BottomSheetDialogFragment
{
    private LicensePlateManager plateManager;
    private ImageButton addPlateButton;
    private TextView[] plateTexts;
    private LinearLayout[] plateRows;
    private ImageButton[] editButtons;
    private ImageButton[] deleteButtons;

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

        plateManager = new LicensePlateManager(requireContext());
        addPlateButton = view.findViewById(R.id.addPlateButton);

        plateRows = new LinearLayout[]{view.findViewById(R.id.licenseRow1), view.findViewById(R.id.licenseRow2), view.findViewById(R.id.licenseRow3), view.findViewById(R.id.licenseRow4)};
        plateTexts = new TextView[]{view.findViewById(R.id.licenseText1), view.findViewById(R.id.licenseText2), view.findViewById(R.id.licenseText3), view.findViewById(R.id.licenseText4)};
        editButtons = new ImageButton[]{view.findViewById(R.id.editButton1), view.findViewById(R.id.editButton2), view.findViewById(R.id.editButton3), view.findViewById(R.id.editButton4)};
        deleteButtons = new ImageButton[]{view.findViewById(R.id.deleteButton1), view.findViewById(R.id.deleteButton2), view.findViewById(R.id.deleteButton3), view.findViewById(R.id.deleteButton4)};

        //by default we set the license plate rows to invisible we will load plates to bring them back
        for (LinearLayout row : plateRows)
            row.setVisibility(View.GONE);

        loadPlates(view);
        checkTheSplitter(view);

        //if the user wants to add a license plate
        addPlateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showAddDialog(view);
            }
        });
    }

    //this method will ask the user if they want to create a new license plate via a pop up
    private void showAddDialog(View view)
    {
        EditText input = new EditText(requireContext());
        input.setHint("Enter new license plate");

        new MaterialAlertDialogBuilder(requireContext()).setTitle("Add License Plate").setView(input).setPositiveButton("Add", (dialog, which) ->
        {
            String newPlate = input.getText().toString().trim(); //remove any white space the user added to each end

            if (newPlate.isEmpty()) //if the users input is empty then show nothing
            {
                Toast.makeText(requireContext(), "License plate cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (newPlate.contains(",")) //if the user tries to add a comma it wont let them because LicensePlateManger separates them via commas in sharedprefrences
            {
                Toast.makeText(requireContext(), "Commas are not allowed in license plates", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean added = plateManager.addPlate(newPlate); //if the license plate already exists
            if (!added)
            {
                Toast.makeText(requireContext(), "List full or plate already exists", Toast.LENGTH_SHORT).show();
            }

            loadPlates(view); //load the plates into view
            checkTheSplitter(view); //add splitters if need be
        }).setNegativeButton("Cancel", null).show();
    }

    //this is a method for if the user wants to edit a license plate it will show a pop up
    private void showEditDialog(TextView plateText)
    {
        final String oldPlate = plateText.getText().toString();

        final EditText input = new EditText(requireContext()); //this is the users input
        input.setText(oldPlate); //the old plate so we know which one we need to delete later if the user confirms the change
        input.setHint("Edit license plate");

        new MaterialAlertDialogBuilder(requireContext()).setTitle("Edit License Plate").setView(input).setPositiveButton("Save", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                String newPlate = input.getText().toString().trim();

                if (newPlate.isEmpty()) //if the users input is empty then show nothing
                {
                    Toast.makeText(requireContext(), "License Plate cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (newPlate.contains(",")) //if the user tries to add a comma it wont let them because LicensePlateManger separates them via commas in sharedprefrences
                {
                    Toast.makeText(requireContext(), "Commas are not allowed in license plates", Toast.LENGTH_SHORT).show();
                    return;
                }

                plateManager.removePlate(oldPlate); //remove the old plate
                plateManager.addPlate(newPlate); //add the just edited plate
                loadPlates(requireView()); //load all plates into view
                checkTheSplitter(requireView()); //add splitters if need be
            }
        }).setNegativeButton("Cancel", null).show();
    }

    private void loadPlates(View view)
    {
        ArrayList<String> plates = plateManager.getPlates(); //plates from the sharedprefrences

        for (int i = 0; i < 4; i++) //loop through each plate row from UI
        {
            if (i < plates.size()) //loop through each plate from sharedprefrences
            {
                //this grabes the text from sharedprefrences or the list and then sets the text of the UI and sets it to visable
                String plate = plates.get(i);
                plateTexts[i].setText(plate);
                plateRows[i].setVisibility(View.VISIBLE);

                final int index = i; //need this for the click of the edit button
                final LinearLayout row = plateRows[i]; //need this for the click of the delete button

                editButtons[i].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        showEditDialog(plateTexts[index]); //allows user to edit the license plate they clicked on
                    }
                });

                deleteButtons[i].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        showDeletePopup(view, row, plate); //allows user to delete the license plate they clicked on
                    }
                });
            }
            else
                plateRows[i].setVisibility(View.GONE); //if there are no more license plates remove the plate row from view
        }

        //if the user has 4 license plates already stored they cannot add another.
        if (plates.size() < 4)
            addPlateButton.setVisibility(View.VISIBLE);
        else
            addPlateButton.setVisibility(View.GONE);

        checkIfNoPlatesRemain(view); //this will check and see if we need to have the no license plates currently stored text
        checkTheSplitter(view); //this will add or remove splitters or separators basically LinearLayout lines
    }

    //displays a confirmation dialog before removing a license plate
    private void showDeletePopup(View rootView, LinearLayout row, String licensePlate)
    {
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete License Plate").setMessage("Are you sure you want to delete this license plate?\n\n" + licensePlate).setPositiveButton("Delete", (dialog, which) ->
                {
                    plateManager.removePlate(licensePlate);
                    row.setVisibility(View.GONE); //set the rows visibility to gone so it does not appear
                    checkIfNoPlatesRemain(rootView); //show no license plate message if need be
                    loadPlates(rootView);
                    checkTheSplitter(rootView); //see if we need to remove a splitter
                }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).show();
    }

    //checks if all license rows are hidden shows "No license plates" message if needed
    private void checkIfNoPlatesRemain(View view)
    {
        TextView noLicenseText = view.findViewById(R.id.noLicenseText);
        boolean noVisibleRows = true;

        //does it need to show the no license plate text
       for (LinearLayout row : plateRows)
       {
           if(row.getVisibility() == View.VISIBLE)
           {
               noVisibleRows = false;
               break;
           }
       }

       if (noVisibleRows)
           noLicenseText.setVisibility(View.VISIBLE);
       else
           noLicenseText.setVisibility(View.GONE);
    }

    //updates the separators after a license plate is deleted
    private void checkTheSplitter(View view)
    {
        LinearLayout separator1 = view.findViewById(R.id.Separator1);
        LinearLayout separator2 = view.findViewById(R.id.Separator2);
        LinearLayout separator3 = view.findViewById(R.id.Separator3);

        LinearLayout licenseRow1 = plateRows[0];
        LinearLayout licenseRow2 = plateRows[1];
        LinearLayout licenseRow3 = plateRows[2];
        LinearLayout licenseRow4 = plateRows[3];

        //if two rows are visible show a splitter if not hide the splitter
        if (licenseRow1.getVisibility() == View.VISIBLE && licenseRow2.getVisibility() == View.VISIBLE)
            separator1.setVisibility(View.VISIBLE);
        else
            separator1.setVisibility(View.GONE);

        if (licenseRow2.getVisibility() == View.VISIBLE && licenseRow3.getVisibility() == View.VISIBLE)
            separator2.setVisibility(View.VISIBLE);
        else
            separator2.setVisibility(View.GONE);

        if (licenseRow3.getVisibility() == View.VISIBLE && licenseRow4.getVisibility() == View.VISIBLE)
            separator3.setVisibility(View.VISIBLE);
        else
            separator3.setVisibility(View.GONE);
    }

}