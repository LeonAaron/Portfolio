package GiftOfFortune;

import java.util.ArrayList;
import java.util.Random;

public class Question {
    //Attribites of a question
    protected String hint;
    protected String answer;
    protected String category;

    // Static ArrayList containing all question objects and possible categories
    protected static ArrayList<Question> allQuestions = new ArrayList<>();
    protected static ArrayList<String> allCategories = new ArrayList<>();

    //External functionality
    protected static Random random = new Random();

    public Question(String hint, String answer, String category) {
        this.hint = hint;
        this.answer = answer;
        this.category = category;

        allQuestions.add(this);

        //Adds category to allCategoies if new category
        if (Question.allCategories.contains(this.category) == false) {
            Question.allCategories.add(this.category);
        }
            
    }


    public static String generateRandomCategory() {
        String result = "";
        int randomIndex = random.nextInt(Question.allCategories.size());
        result = allCategories.get(randomIndex);
        return result;
    }
}
