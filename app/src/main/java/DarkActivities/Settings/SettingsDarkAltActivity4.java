package DarkActivities.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import DarkActivities.LoginRelated.DeleteAccountDarkActivity;
import com.example.spotifywrapped.R;

import LightActivities.Settings.SettingsLightAltActivity4;

public class SettingsDarkAltActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsdarkalt4);

        // Button to navigate to SettingsLightActivity4
        Button settingsLightButton = findViewById(R.id.button43);
        settingsLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsLightActivity4 when the button is clicked
                startActivity(new Intent(SettingsDarkAltActivity4.this, SettingsLightAltActivity4.class));
            }
        });

        // Button to navigate to DeleteAccountDarkActivity
        Button deleteAccountDarkButton = findViewById(R.id.button45);
        deleteAccountDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DeleteAccountDarkActivity when the button is clicked
                startActivity(new Intent(SettingsDarkAltActivity4.this, DeleteAccountDarkActivity.class));
            }
        });
    }
}
