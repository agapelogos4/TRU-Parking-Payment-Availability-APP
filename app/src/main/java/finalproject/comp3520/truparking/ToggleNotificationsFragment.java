// File: ToggleNotificationsFragment.java
// Author: Zechariah Montgomery
// Description: A bottom sheet fragment that allows the user to toggle notifications on/off.
// Date: [09/08/2025]

package finalproject.comp3520.truparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ToggleNotificationsFragment extends BottomSheetDialogFragment
{

    //this is still a work in progress
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_toggle_notifications, container, false);

        ToggleButton toggleButton = view.findViewById(R.id.toggle1);
        toggleButton.setOnClickListener(v ->
        {
            Toast.makeText(getActivity(), "Toggle clicked!", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        return view;
    }
}
