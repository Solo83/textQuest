package entities;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Question> questions;


    public Game() {
        this.questions = new ArrayList<>();
        initGame();
    }

    public void initGame() {
        List<Answer> answersList1 = new ArrayList<>();
        Question question1 = new Question("Ты потерял память! Принять вызов НЛО?",answersList1);
        answersList1.add(new Answer("Принять вызов"));
        answersList1.add(new Answer("Отклонить вызов"));


        Question question2 = new Question("Ты отклонил вызов! Поражение!",new ArrayList<>(0));

        List<Answer> answersList3 = new ArrayList<>();
        Question question3 = new Question("Ты принял вызов! Поднимаешься на мостик к капитану?",answersList3);
        answersList3.add(new Answer("Подняться на мостик"));
        answersList3.add(new Answer("Отказаться подняться на мостик"));

        Question question4 = new Question("Ты не пошел на переговоры! Поражение!",new ArrayList<>(0));

        List<Answer> answersList5 = new ArrayList<>();
        Question question5 = new Question("Ты поднялся на мостик. Ты кто?",answersList5);
        answersList5.add(new Answer("Рассказать правду о себе"));
        answersList5.add(new Answer("Солгать о себе"));

        Question question6 = new Question("Твою ложь разоблачили. Поражение!",new ArrayList<>(0));
        Question question7 = new Question("Тебя вернули домой. победа!",new ArrayList<>(0));

        this.questions.add(question1);
        this.questions.add(question2);
        this.questions.add(question3);
        this.questions.add(question4);
        this.questions.add(question5);
        this.questions.add(question6);
        this.questions.add(question7);


    }

    public List<Question> getQuestions() {
        return questions;
    }

}
