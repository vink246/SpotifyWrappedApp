package LightActivities.LoginRelated;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;

import LightActivities.Settings.SettingsLightAltActivity3;

public class UpdateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_account);

        // Button to navigate to SettingsLightAltActivity1
        Button settingsButton1 = findViewById(R.id.button27);
        settingsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsLightAltActivity1 when the button is clicked
                startActivity(new Intent(UpdateAccountActivity.this, SettingsLightAltActivity3.class));
            }
        });

        // Button to navigate to LoginActivity
        Button loginButton = findViewById(R.id.button29);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity when the button is clicked
                startActivity(new Intent(UpdateAccountActivity.this, LoginActivity.class));
            }
        });
    }
}
