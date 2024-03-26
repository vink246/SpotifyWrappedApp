package Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class DarkModePreferenceManager {

    private static final String PREF_NAME = "darkModePref";
    private static final String DARK_MODE_KEY = "isDarkMode";

    // Set the dark mode preference
    public static void setDarkMode(Context context, boolean isDarkMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DARK_MODE_KEY, isDarkMode);
        editor.apply();
    }

    // Get the dark mode preference
    public static boolean isDarkMode(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(DARK_MODE_KEY, false);
    }
}
