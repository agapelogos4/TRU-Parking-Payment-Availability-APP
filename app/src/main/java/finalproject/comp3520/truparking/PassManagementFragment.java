// File: PassManagementFragment.java
// Author: Zechariah Montgomery
// Description: Displays a bottom sheet dialog containing all saved parking passes.
// Date: [09/08/2025]

package finalproject.comp3520.truparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

/**
 * PassManagementFragment shows the user's saved parking passes in a bottom sheet layout.
 * It handles:
 *  - loading saved pass data from PassManager
 *  - updating TextView visibility if no passes exist in the recyclerview
 *  - initializing recyclerview with PassAdapter
 */
public class PassManagementFragment extends BottomSheetDialogFragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_pass_management, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.passesRecyclerView);
        LinearLayout legend = view.findViewById(R.id.passLegendLayout);//this is used as a key to show what the colors represent, weather its a multiday pass or a single day pass
        TextView noPassMessage = view.findViewById(R.id.noPassesMessage);

        //grab the saved passes from sharedpreferences through the passManager class.
        PassManager passManager = new PassManager(getContext());
        List<Pass> passList = passManager.getAllPasses();

        if (passList.isEmpty()) //if the recyclerview is empty then show the no pass message
        {
            recyclerView.setVisibility(View.GONE);
            noPassMessage.setVisibility(View.VISIBLE);
            legend.setVisibility(View.GONE);
        }
        else //if the recyclerview is not empty then hide the no pass message
        {
            recyclerView.setVisibility(View.VISIBLE);
            noPassMessage.setVisibility(View.GONE);
            legend.setVisibility(View.VISIBLE);
        }

        //initiate the recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new PassAdapter(getContext(), passList));

        return view;
    }
}