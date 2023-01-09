package entities;

import java.util.Objects;

public class User {
    private final String name;

    private int gameCounter;

    public int getGameCounter() {
        return gameCounter;
    }

    public void increaseGameCounter() {
        this.gameCounter++;
    }

    public User(String name) {
        this.name = name;
        this.gameCounter = 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


