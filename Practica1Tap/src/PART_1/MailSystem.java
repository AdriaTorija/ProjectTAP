package PART_1;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MailSystem {
    MailStore mStore;
    Hashtable<String,MailBox> mailBoxs;

    public MailSystem(MailStore store){
        this.mStore= store;
        mailBoxs= new Hashtable<>();
    }

    public MailBox createNewUser(String nick, String name, int year){
        User user = new User(nick,name,year);
        MailBox mailBox = new MailBox(mStore,user);
        mailBoxs.put(user.getNick(),mailBox);
        return mailBox;
    }

    public List<Message> getAllMessages() throws IOException {
        return mStore.getMessages();
    }

    public List<User> getAllUsers(){
        List<User> users = new LinkedList<>();
        Enumeration<String> e = mailBoxs.keys();
        while(e.hasMoreElements()){
            users.add(mailBoxs.get(e.nextElement()).getUser());
        }
        return users;
    }
    public List <Message> filter(Predicate<Message> f) throws IOException{
        return getAllMessages().stream().filter(f).collect(Collectors.toList());
    }
    public int getNMessages() throws IOException{
        return mStore.getNMessages();
    }

    public float averageMessageUser()throws IOException{
        return (float)getNMessages()/mailBoxs.size();
    }


    public List<Message> messagesSameSubject(String subject)throws  IOException{
        return getAllMessages().stream().filter(message -> message.getSubject().equals(subject)).collect(Collectors.toList());
    }
    public Map<String, List<Message>> messagesSubject()throws  IOException{
        return getAllMessages().stream().collect(Collectors.groupingBy(Message::getSubject));
    }

    public int nWordsMessagesUser(String name){
        return mailBoxs.values().stream().filter(s -> s.getUser().getName().equals(name)).flatMap(x -> x.getMessages().stream()).mapToInt(n -> n.getText().length()).sum();
    }

    public List<Message> messagesUsersYear(int year){
       return mailBoxs.values().stream().filter(s-> s.getUser().getBirthYear() < year).flatMap(x -> x.getMessages().stream()).collect(Collectors.toList());
    }

    public void setmStore(MailStore mStore) {
        this.mStore = mStore;
    }

    public void setMailBoxs(Hashtable<String, MailBox> mailBoxs) {
        this.mailBoxs = mailBoxs;
    }

    public MailStore getmStore() {
        return mStore;
    }

    public Hashtable<String, MailBox> getMailBoxs() {
        return mailBoxs;
    }
}

