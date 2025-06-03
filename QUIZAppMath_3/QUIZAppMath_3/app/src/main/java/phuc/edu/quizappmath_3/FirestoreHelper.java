package phuc.edu.quizappmath_3;

import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class FirestoreHelper {

    public interface QuestionCallback {
        void onCallback(List<Question> questions);
    }

    public void getQuestionsByLevel(String level, QuestionCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("questions")
                .whereEqualTo("level", level)
                .get()
                .addOnCompleteListener(task -> {
                    List<Question> questionList = new ArrayList<>();
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            Question question = doc.toObject(Question.class);
                            questionList.add(question);
                        }
                    } else {
                        Log.w("FirestoreHelper", "Lỗi đọc câu hỏi", task.getException());
                    }
                    callback.onCallback(questionList);
                });
    }
}
