package phuc.edu.quizappmath_3;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String PREFS = "QuizAppPrefs";
    private static final String KEY_NAME = "user_name";

    public static void saveUserName(Context ctx, String name) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public static String getUserName(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY_NAME, "Học sinh");
    }
}
