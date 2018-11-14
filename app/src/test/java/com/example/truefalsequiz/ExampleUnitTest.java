package com.example.truefalsequiz;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }

    @Test
    public void nextQuestionIsCorrect(){
        Question q1 = new Question("q1", true);
        Question q2 = new Question("q2", true);
        Question q3 = new Question("q3", true);

        List<Question> questions = new ArrayList<>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);

        Quiz quiz = new Quiz(0, 0, questions);

        Question next = questions.get(quiz.getNextQuestion());

        assertEquals(q1.toString(), next.toString());
    }
}