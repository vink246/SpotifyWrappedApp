package LightActivities.LoginRelated;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;

import LightActivities.Settings.SettingsLightAltActivity4;

public class DeleteAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_account);

        // Button to navigate to SettingsLightAltActivity1
        Button settingsButton1 = findViewById(R.id.button21);
        settingsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsLightAltActivity1 when the button is clicked
                startActivity(new Intent(DeleteAccountActivity.this, SettingsLightAltActivity4.class));
            }
        });

        // Button to navigate to LoginActivity
        Button loginButton = findViewById(R.id.button24);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity when the button is clicked
                startActivity(new Intent(DeleteAccountActivity.this, LoginActivity.class));
            }
        });
    }
}
