package finalproject.comp3520.truparking;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivityTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences preferences = getSharedPreferences("parking_preferences", MODE_PRIVATE);
        boolean isDarkMode = preferences.getBoolean("dark_mode", false);

        if (isDarkMode)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_test);

        addTestTransactions();
        addTestPass();
        addTestLicensePlates();

        //show the Settings fragment when the app starts
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Settings()).commit();


        getWindow().setStatusBarColor(getResources().getColor(R.color.TRUDark, getTheme()));

        LanuageManager.applySavedLanguage(this);

    }

    //remove the id part so the constructor creates a unique id I only do this because OnCreate is repeated due to toggle button and language selector
    private void addTestTransactions()
    {
        TransactionManager transactionManager = new TransactionManager(this);

        Transaction transaction1 = new Transaction("test1", "2025-11-07", 200.0, "2 hours", "A", "ABC-1234", "Bill Smith", "1234");
        Transaction transaction2 = new Transaction("test2", "2025-11-07", 15.0, "1 hour", "B", "DEF-5678", "Will Rob", "5678");

        transactionManager.addTransaction(transaction1);
        transactionManager.addTransaction(transaction2);
    }

    //remove the id part so the constructor creates a unique id I only do this because OnCreate is repeated due to toggle button and language selector
    private void addTestPass()
    {
        PassManager passManager = new PassManager(this);

        Pass pass1 = new Pass("test1", "ABC123", "2025-12-01", "N", true);
        Pass pass2 = new Pass("test2", "DEF456", "2025-11-01", "N", false);

        passManager.addPass(pass1);
        passManager.addPass(pass2);
    }

    private void addTestLicensePlates()
    {
        LicensePlateManager plateManager = new LicensePlateManager(this);

        plateManager.addPlate("ABC123");
    }
}