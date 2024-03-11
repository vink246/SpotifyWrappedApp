package DarkActivities.LoginRelated;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;

import DarkActivities.Settings.SettingsDarkAltActivity4;

public class DeleteAccountDarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_account_dark);

        // Button to navigate to SettingsDarkAltActivity1
        Button settingsButton1 = findViewById(R.id.button23);
        settingsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkAltActivity1 when the button is clicked
                startActivity(new Intent(DeleteAccountDarkActivity.this, SettingsDarkAltActivity4.class));
            }
        });

        // Button to navigate to LoginActivity
        Button loginButton = findViewById(R.id.button26);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity when the button is clicked
                startActivity(new Intent(DeleteAccountDarkActivity.this, LoginDarkActivity.class));
            }
        });
    }
}
