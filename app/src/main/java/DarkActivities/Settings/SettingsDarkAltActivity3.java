package DarkActivities.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import DarkActivities.LoginRelated.UpdateAccountDarkActivity;

import LightActivities.Settings.SettingsLightAltActivity3;

public class SettingsDarkAltActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsdarkalt3);

        // Button to navigate to SettingsLightAltActivity3
        Button settingsLightButton = findViewById(R.id.button41);
        settingsLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsLightAltActivity3 when the button is clicked
                startActivity(new Intent(SettingsDarkAltActivity3.this, SettingsLightAltActivity3.class));
            }
        });

        // Button to navigate to UpdateAccountDarkActivity
        Button updateAccountDarkButton = findViewById(R.id.button42);
        updateAccountDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start UpdateAccountDarkActivity when the button is clicked
                startActivity(new Intent(SettingsDarkAltActivity3.this, UpdateAccountDarkActivity.class));
            }
        });
    }
}
