package LightActivities.LoginRelated;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spotifywrapped.R;

import LightActivities.Settings.SettingsLightAltActivity1;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Button to navigate to SettingsActivity
        Button settingsButton = findViewById(R.id.button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsActivity when the button is clicked
                startActivity(new Intent(LoginActivity.this, SettingsLightAltActivity1.class));
            }
        });

        // Button to navigate to CreateAccountActivity
        Button createAccountButton = findViewById(R.id.button2);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CreateAccountActivity when the button is clicked
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });

        // Button to navigate to UpdateAccountActivity
        Button updateAccountButton = findViewById(R.id.button3);
        updateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start UpdateAccountActivity when the button is clicked
                startActivity(new Intent(LoginActivity.this, UpdateAccountActivity.class));
            }
        });

        // Button to navigate to DeleteAccountActivity
        Button deleteAccountButton = findViewById(R.id.button4);
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DeleteAccountActivity when the button is clicked
                startActivity(new Intent(LoginActivity.this, DeleteAccountActivity.class));
            }
        });
    }
}
