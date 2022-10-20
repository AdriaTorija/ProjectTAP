package PART_1;


import java.util.*;

public class StoreInMemory implements MailStore{
    List<Message> store;

    public StoreInMemory(){
        store= new LinkedList<>();
    }
    @Override
    public void sendMail(Message message) {

        store.add(message);
    }

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

    @Override
    public int getNMessages() {
        return store.size();
    }

    @Override
    public List<Message> getMessages() {
        return store;
    }

}
