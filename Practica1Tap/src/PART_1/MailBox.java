package PART_1;

import PART_2.SpamUserFilter;
import PART_2.TooLongFilter;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MailBox {
    MailStore store;
    User user;
    List<Message> messages;

    public MailBox(MailStore store, User user){
        this.store=store;
        this.user=user;
        messages = new LinkedList<>();
    }
    public void updateMail()throws IOException{
        messages.clear();
        messages.addAll(store.getMail(user));
    }

    public List<Message> listMail(){
        return messages;
    }

    public void sendMail(String Subject, String text, String destination)throws IOException{
        Message message = new Message(Subject,text,user.getNick(),destination);
        store.sendMail(message);
    }

    public  List<Message> getSortedMails(Comparator <Message> c){
        return messages.stream().sorted(c).collect(Collectors.toList());
        //messages.sort(c);
        //return messages;
    }


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

    @Override
    public String toString() {
        return "MailBox{" +
                "store=" + store +
                ", user=" + user +
                ", messages=" + messages +
                '}';
    }


}
