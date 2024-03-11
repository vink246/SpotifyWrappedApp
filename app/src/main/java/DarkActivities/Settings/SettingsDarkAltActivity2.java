package DarkActivities.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import DarkActivities.LoginRelated.CreateAccountDarkActivity;
import com.example.spotifywrapped.R;

import LightActivities.Settings.SettingsLightAltActivity2;

public class SettingsDarkAltActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsdarkalt2);

        // Button to navigate to SettingsLightAltActivity2
        Button settingsLightButton = findViewById(R.id.button39);
        settingsLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsLightAltActivity2 when the button is clicked
                startActivity(new Intent(SettingsDarkAltActivity2.this, SettingsLightAltActivity2.class));
            }
        });

        // Button to navigate to CreateAccountDarkActivity
        Button createAccountDarkButton = findViewById(R.id.button40);
        createAccountDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CreateAccountDarkActivity when the button is clicked
                startActivity(new Intent(SettingsDarkAltActivity2.this, CreateAccountDarkActivity.class));
            }
        });
    }
}
