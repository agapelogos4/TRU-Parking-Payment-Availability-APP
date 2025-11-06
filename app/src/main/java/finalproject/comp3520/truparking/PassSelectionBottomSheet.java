package finalproject.comp3520.truparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    @Override
    public int getTheme()
    {
        return R.style.PassBottomSheetDialogTheme;
    }

}
