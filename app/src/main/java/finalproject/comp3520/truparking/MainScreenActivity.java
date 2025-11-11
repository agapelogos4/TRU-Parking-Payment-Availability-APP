package finalproject.comp3520.truparking;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

public class MainScreenActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_activity);

        Toolbar toolbar = findViewById(R.id.top_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        //this will show the mapFragment as default in the fragment_container
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
        }

        ImageButton settingsButton = toolbar.findViewById(R.id.settingsCog);
        ImageButton loginLogoutButton = toolbar.findViewById(R.id.loginLogoutButton);
        ImageButton listOfPassesButton = toolbar.findViewById(R.id.listOfPassesButton);
        ImageButton linkToTruPageButton = toolbar.findViewById(R.id.linkToTruPageButton);

        //settings button
        settingsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Settings()).addToBackStack(null).commit();
                }
                catch (IllegalStateException e)
                {
                    Log.e("MainScreenActivity", "Failed to open Settings fragment", e);
                    Toast.makeText(MainScreenActivity.this, "Unable to open settings.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //login button
        loginLogoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).addToBackStack(null).commit();
                }
                catch (IllegalStateException e)
                {
                    Log.e("MainScreenActivity", "Failed to open Login fragment", e);
                    Toast.makeText(MainScreenActivity.this, "Unable to open login screen.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //view of all prices
        listOfPassesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //this will show maybe a bottom sheet of all available passes and lot prices accross TRU so the user does not need to select each one by one from the map view
            }
        });

        //pull up TRU page link
        linkToTruPageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tru.ca/"));

                //try to see if we can access https://www.tru.ca/
                if (intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(intent);
                }
                else //if we cant access https://www.tru.ca/ try this instead
                {

                    Intent fallbackIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=Thompson+Rivers+University"));

                    //try google https://www.google.com/search?q=Thompson+Rivers+University
                    if (fallbackIntent.resolveActivity(getPackageManager()) != null)
                    {
                        startActivity(fallbackIntent);
                    }
                    else //they might not have a search engine
                    {

                        Toast.makeText(MainScreenActivity.this, "No web browser or search app available.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
