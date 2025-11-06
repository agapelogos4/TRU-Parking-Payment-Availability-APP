package finalproject.comp3520.truparking;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PassSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pass_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RadioButton dayPassRadioButton = findViewById(R.id.dayPassRadioButton);
        RadioButton multidayPassRadioButton = findViewById(R.id.multiDayPassRadioButton);
        LinearLayout dayPassCard = findViewById(R.id.DayPassCard);
        LinearLayout multiDayPassCard = findViewById(R.id.MultiDayPassCard);

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

        dayPassRadioButton.setOnClickListener(v -> {multidayPassRadioButton.setChecked(false);});
        multidayPassRadioButton.setOnClickListener(v -> {dayPassRadioButton.setChecked(false);});
    }
}