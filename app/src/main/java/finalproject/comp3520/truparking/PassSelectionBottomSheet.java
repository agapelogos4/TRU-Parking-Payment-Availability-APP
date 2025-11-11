package finalproject.comp3520.truparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PassSelectionBottomSheet extends BottomSheetDialogFragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle SavedInstanceState)
    {
        return inflater.inflate(R.layout.activity_pass_selection, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        View bottomSheet = getDialog().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (bottomSheet != null)
        {
            bottomSheet.getLayoutParams().height = (int) (getResources().getDisplayMetrics().heightPixels * 0.7);
            bottomSheet.requestLayout();
        }
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RadioButton dayPassRadioButton = view.findViewById(R.id.dayPassRadioButton);
        RadioButton multidayPassRadioButton = view.findViewById(R.id.multiDayPassRadioButton);
        LinearLayout dayPassCard = view.findViewById(R.id.DayPassCard);
        LinearLayout multiDayPassCard = view.findViewById(R.id.MultiDayPassCard);

        dayPassCard.setOnClickListener( v -> {
            dayPassCard.setSelected(true);
            multiDayPassCard.setSelected(false);
            dayPassRadioButton.setChecked(true);
            multidayPassRadioButton.setChecked(false);
        });

        multiDayPassCard.setOnClickListener(v -> {
            dayPassCard.setSelected(false);
            multiDayPassCard.setSelected(true);
            dayPassRadioButton.setChecked(false);
            multidayPassRadioButton.setChecked(true);
        });

        dayPassRadioButton.setOnClickListener(v -> {
            dayPassCard.setSelected(true);
            multiDayPassCard.setSelected(false);
            dayPassRadioButton.setChecked(true);
            multidayPassRadioButton.setChecked(false);
        });

        multidayPassRadioButton.setOnClickListener(v -> {
            dayPassCard.setSelected(false);
            multiDayPassCard.setSelected(true);
            dayPassRadioButton.setChecked(false);
            multidayPassRadioButton.setChecked(true);
        });
    }

    @Override
    public int getTheme()
    {
        return R.style.PassBottomSheetDialogTheme;
    }

}
