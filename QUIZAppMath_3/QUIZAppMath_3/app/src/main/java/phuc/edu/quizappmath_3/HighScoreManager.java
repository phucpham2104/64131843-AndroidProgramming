package phuc.edu.quizappmath_3;

import android.content.Context;
import android.content.SharedPreferences;

public class HighScoreManager {
    private static final String PREFS = "HighScorePrefs";
    private static final String KEY_HIGH_SCORE = "high_score";

    public static void saveHighScore(Context context, int score) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        int oldScore = prefs.getInt(KEY_HIGH_SCORE, 0);
        if (score > oldScore) {
            prefs.edit().putInt(KEY_HIGH_SCORE, score).apply();
        }
    }

    public static int getHighScore(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getInt(KEY_HIGH_SCORE, 0);
    }
}
