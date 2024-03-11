package DarkActivities.LoginRelated;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;

import DarkActivities.Settings.SettingsDarkAltActivity2;

public class CreateAccountDarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_dark);

        // Button to navigate to SettingsDarkAltActivity1
        Button settingsButton1 = findViewById(R.id.button18);
        settingsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkAltActivity1 when the button is clicked
                startActivity(new Intent(CreateAccountDarkActivity.this, SettingsDarkAltActivity2.class));
            }
        });

        // Button to navigate to LoginActivity
        Button loginButton = findViewById(R.id.button20);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity when the button is clicked
                startActivity(new Intent(CreateAccountDarkActivity.this, LoginDarkActivity.class));
            }
        });
    }
}
