package finalproject.comp3520.truparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button lotNButton = findViewById(R.id.LotNButton);
        ImageButton loginButton = findViewById(R.id.loginButton);


        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.top_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        lotNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, PaymentScreen.class);
                startActivity(intent);
                /*PassSelectionBottomSheet sheet = new PassSelectionBottomSheet();
                sheet.show(getSupportFragmentManager(), "PassSelectionBottomSheet");*/
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, LoginSignUpScreen.class);
                startActivity(intent);
            }
        });

    }
}