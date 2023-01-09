package model;

import entities.Game;
import entities.Question;
import entities.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Model {

    private static Model instance;

    private final List<Question> gameQuestion = new Game().getQuestions();

    private final Set<User> users = new HashSet<>();

    public static Model getInstance() {
        if(instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private Model() {
    }

    public void add(User user) {
        users.add(user);
    }

    public List<Question> getGameQuestion() {
        return this.gameQuestion;
    }

    public User getUser (String name) {
        return users.stream().filter(x -> x.getName().equals(name)).findAny().orElse(null);
    }


}

