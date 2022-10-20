package PART1;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * MailSystem implementation thathas its own MailStore
 */
public class MailSystem {
    MailStore mStore;
    Hashtable<String,MailBox> mailBoxs;

    /**
     * Creates a Hashtable of mailbox and sets the Store to the MailSystem.
     * @param store
     */
    public MailSystem(MailStore store){
        this.mStore= store;
        mailBoxs= new Hashtable<>();
    }

    /**
     * Given a Nick, a name and a year creates a new user at the System adding this one to the HashTable of MailBoxs and returning his mailBox.
     * @param nick
     * @param name
     * @param year
     * @return
     */
    public MailBox createNewUser(String nick, String name, int year){
        User user = new User(nick,name,year);
        MailBox mailBox = new MailBox(mStore,user);
        mailBoxs.put(user.getNick(),mailBox);
        return mailBox;
    }

    /**
     * Method that returns a List of all the messages saved at the mailStore.
     * @return
     * @throws IOException
     */
    public List<Message> getAllMessages() throws IOException {
        return mStore.getMessages();
    }

    /**
     * Method that returns a List of all the Users registered at the MailSystem
     * @return
     */

    public List<User> getAllUsers(){
        List<User> users = new LinkedList<>();
        Enumeration<String> e = mailBoxs.keys();
        while(e.hasMoreElements()){
            users.add(mailBoxs.get(e.nextElement()).getUser());
        }
        return users;
    }

    /**
     * Method that returns a List of Messages filtered by a given Predicate.
     * @param f
     * @return
     * @throws IOException
     */
    public List <Message> globalFilter(Predicate<Message> f) throws IOException{
        return getAllMessages().stream().filter(f).collect(Collectors.toList());
    }

    /**
     * Method that returns the number of messages saved at the mailStore.
     * @return
     * @throws IOException
     */
    public int nMessages() throws IOException{
        return mStore.getNMessages();
    }

    /**
     * Method that returns the averageMessages of the Users of the System.
     * @return
     * @throws IOException
     */
    public float averageMessageUser()throws IOException{
        return (float) nMessages()/mailBoxs.size();
    }

    /**
     * Method that returns a List of Messages that have the same subject. Just a filter.
     * @param subject
     * @return
     * @throws IOException
     */
    public List<Message> messagesSameSubject(String subject)throws  IOException{
        return getAllMessages().stream().filter(message -> message.getSubject().equals(subject)).collect(Collectors.toList());
    }

    /**
     * Method that returns a Map of the messages regrouped by Subject.
     * @return
     * @throws IOException
     */
    public Map<String, List<Message>> messagesSubject()throws  IOException{
        return getAllMessages().stream().collect(Collectors.groupingBy(Message::getSubject));
    }

    /**
     * Method that returns a number of Words written by an User with an specific name.
     * @param name
     * @return
     */
    public int nWordsMessagesUser(String name){
        return mailBoxs.values().stream().filter(s -> s.getUser().getName().equals(name)).flatMap(x -> x.getMessages().stream()).mapToInt(n -> n.getText().length()).sum();
    }

    /**
     * Method that returns a ListOfMessages from users who's birthday is less than the year given by parameter.
     * @param year
     * @return
     */
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

