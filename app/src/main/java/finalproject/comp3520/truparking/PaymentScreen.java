package finalproject.comp3520.truparking;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class PaymentScreen extends AppCompatActivity
{
    private String currentSelectedPlate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Spinner durationSpinner = findViewById(R.id.durationSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Hour_Choices_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(adapter);

        Spinner licensePlateSpinner = findViewById(R.id.licensePlateSpinner);


        licensePlateSpinner.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP)
            {
                showPlateDialog();
            }
            return  true;
        });


        ArrayAdapter<String> plateAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new ArrayList<>(Collections.singletonList("Select A Plate"))

        );

        plateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        licensePlateSpinner.setAdapter(plateAdapter);
    }

    private void showPlateDialog()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_plate_picker);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        NumberPicker platePicker = dialog.findViewById(R.id.platePicker);
        //TextView plateText = dialog.findViewById(R.id.plateText);
        Button confirmButton = dialog.findViewById(R.id.confirmButton);

        String[] savedPlates = {"ABC123", "JXD820", "M3R1DA", "EV69PLT"};

        platePicker.setMinValue(0);
        platePicker.setMaxValue(savedPlates.length - 1);
        platePicker.setDisplayedValues(savedPlates);
        platePicker.setWrapSelectorWheel(true);

        int startIndex = 0;

        if (currentSelectedPlate != null)
        {
            for (int i = 0; i < savedPlates.length; i++)
            {
                if (savedPlates[i].equals(currentSelectedPlate))
                {
                    startIndex = i;
                    break;
                }
            }
        }
        platePicker.setValue(startIndex);

        final int[] selectedIndex = {0};
        platePicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            selectedIndex[0] = newVal;
            //plateText.setText(savedPlates[newVal]);
        });

        confirmButton.setOnClickListener(v -> {
            String selectedPlate = savedPlates[platePicker.getValue()];
            updateSpinnerSelection(selectedPlate);

            currentSelectedPlate = selectedPlate;

            dialog.dismiss();
        });

        dialog.show();
    }

    private void  updateSpinnerSelection(String selectedPlate) {
        Spinner plateSpinner = findViewById(R.id.licensePlateSpinner);
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) plateSpinner.getAdapter();

        adapter.clear();
        adapter.add(selectedPlate);
        adapter.notifyDataSetChanged();

        plateSpinner.setSelection(0);
    }
}