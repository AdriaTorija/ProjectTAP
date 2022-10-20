package PART1;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Mailbox Class that has a Store, User and its list of messages
 */
public class MailBox {
    MailStore store;
    User user;
    List<Message> messages;

    /**
     * Creates a mailbox for an User, it needs a store, and the user. It has a List of messages.
     * @param store
     * @param user
     */
    public MailBox(MailStore store, User user){
        this.store=store;
        this.user=user;
        messages = new LinkedList<>();
    }

    /**
     * Method that clears the list of messages and it gets all the messages of the user at the mailStore
     * @throws IOException
     */
    public void updateMail()throws IOException{
        messages.clear();
        messages.addAll(store.getMail(user));
    }

    /**
     * Method that returns the list of Messages
     * @return
     */
    public List<Message> listMail(){
        return messages;
    }

    /**
     * Method that given a subject, text, and a nick of destination, creates a message and sends it to the mailStore
     * @param Subject
     * @param text
     * @param destination
     * @throws IOException
     */
    public void sendMail(String Subject, String text, String destination)throws IOException{
        Message message = new Message(Subject,text,user.getNick(),destination);
        store.sendMail(message);
    }

    /**
     * Method that returns a sorted list by a given comparator.
     * @param c
     * @return List of Messages
     */
    public  List<Message> getSortedMails(Comparator <Message> c){
        return messages.stream().sorted(c).collect(Collectors.toList());
        //messages.sort(c);
        //return messages;
    }

    /**
     * Method that returns a list of messages filtered by a given Predicate
     * @param f
     * @return List of Messages
     */
    public List<Message> filterUserMail(Predicate<Message> f){
        return messages.stream().filter(f).collect(Collectors.toList());
    }


    //Getters & setters

    public MailStore getStore() {
        return store;
    }


    public User getUser() {
        return user;
    }


    public List<Message> getMessages() {
        return messages;
    }

    public void setStore(MailStore store) {
        this.store = store;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Method to return a String of the content of the mailbox.
     * @return
     */
    @Override
    public String toString() {
        return "MailBox{" +
                "store=" + store +
                ", user=" + user +
                ", messages=" + messages +
                '}';
    }


}
