package PART1;


import java.util.*;


/**
 * MailStore implementation that stores memory to Ram
 */
public class StoreInMemory implements MailStore{
    List<Message> store;

    /**
     * Constructor that creates a new LinkedList
     */
    public StoreInMemory(){
        store= new LinkedList<>();
    }

    /**
     * Method that adds the message to the List of Messages
     * @param message
     */
    @Override
    public void sendMail(Message message) {

        store.add(message);
    }

    /**
     * Method that returns the messages of an User given by parameter from the List of Messages
     * @param user
     * @return
     */
    @Override
    public List<Message> getMail(User user) {
        LinkedList<Message> aux = new LinkedList<>();
        for (Message m: store){
            if(m.getTo().equals(user.getNick())){
                aux.add(m);
            }
        }
        return aux;
    }

    /**
     * Method that returns the number of Messages stored at the List.
     * @return
     */
    @Override
    public int getNMessages() {
        return store.size();
    }

    /**
     * Method that returns the List of Messages.
     * @return
     */
    @Override
    public List<Message> getMessages() {
        return store;
    }

}
