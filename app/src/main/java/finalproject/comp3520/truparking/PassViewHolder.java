// File: PassViewHolder.java
// Author: Zechariah Montgomery
// Description: Holds references to the front end for each pass item in the RecyclerView.
// Date: [09/08/2025]

package finalproject.comp3520.truparking;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder for a single parking pass item.
 * stores references to all front end elements in the pass item layout
 * to improve performance by avoiding repeated findViewById calls.
 */
public class PassViewHolder extends RecyclerView.ViewHolder
{
    TextView licensePlate, expirationDate, lot;
    LinearLayout passType;
    ImageButton editButton;

    public PassViewHolder(@NonNull View itemView)
    {
        super(itemView);
        licensePlate = itemView.findViewById(R.id.licensePlateText);
        expirationDate = itemView.findViewById(R.id.passExpirationText);
        lot = itemView.findViewById(R.id.passLotText);
        passType = itemView.findViewById(R.id.passColorIndicator);
        editButton = itemView.findViewById(R.id.editLicensePlateButton);
    }
}
