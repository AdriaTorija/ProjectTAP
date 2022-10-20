package PART_1;


import java.util.Objects;

public class User {
    private String nick;
    private String name;
    private int birthYear;


    public User(String nick, String name, int birthYear) {
        this.nick = nick;
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return birthYear == user.birthYear &&
                nick.equals(user.nick) &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick, name, birthYear);
    }

    @Override
    public String toString() {
        return "User{" +
                "nick='" + nick + '\'' +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
