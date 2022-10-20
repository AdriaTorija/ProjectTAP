package PART1;


import java.util.Objects;

/**
 * User implementation and its methods
 */
public class User {
    private String nick;
    private String name;
    private int birthYear;

    /**
     * Constructor that creates an User by a given nick, name, and birthyear.
     * @param nick
     * @param name
     * @param birthYear
     */
    public User(String nick, String name, int birthYear) {
        this.nick = nick;
        this.name = name;
        this.birthYear = birthYear;
    }

    //Getters & Setters

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
                Objects.equals(nick, user.nick) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick, name, birthYear);
    }

    /**
     * Method that returns a String of all the contents at User
     * @return
     */

    @Override
    public String toString() {
        return "User{" +
                "nick='" + nick + '\'' +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }

}
