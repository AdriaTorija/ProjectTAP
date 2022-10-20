package PART3;

import PART1.MailStore;
import PART1.Message;
import PART1.User;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Mail Store implementation using Redis server
 */
public class RedisMailStore implements MailStore {

    private static RedisMailStore instance = null;
    private Jedis jedis;
    private int numMessages;
    private ArrayList<String> keys;

    /**
     * Private bcs it's a singleton, we connect to the server through localhost
     * using a Jedis type object
     */
    private RedisMailStore(){
        jedis = new Jedis("localhost");
        keys = new ArrayList<String>();
    }

    /**
     * method that alows using RedisMailStore as Singleton
     * return its instance
     */
    public static RedisMailStore getInstance() {
        if (instance == null) {
            instance = new RedisMailStore();
        }
        return instance;
    }

    /**
     *receiveMessages gets all messages that a user has been received
     * and return as a list of strings
     */
    public List<String> receiveMessages(String user){
        return jedis.lrange( user,0,150);
    }

    /**
     * this method sends a mail to the server with the message that receives from param
     * it also increases num of messages. Returns null.
     * @param message
     * @throws IOException
     */
    @Override
    public void sendMail(Message message) throws IOException {
        if (keys.contains(message.getTo()) == false) {
            keys.add(message.getTo());
        }
        jedis.lpush(message.getTo(), message.toString());
        numMessages++;

    }

    /**
     * this function returns a list of messages that an user has received.
     * It uses receiveMessages() and convert the returned list of Strings to
     * messages
     * @param user
     * @return list of Messages
     * @throws IOException
     */
    @Override
    public List<Message> getMail(User user) throws IOException {
        Message message;
        String[] parts;
        LinkedList<Message> messages = new LinkedList<>();
        List<String> mails = this.receiveMessages(user.getNick());
        for (String s: mails){
            parts = s.split(";");
            if (parts.length ==5){
                messages.add( new Message( parts[0],parts[1], parts[2], parts[3], LocalDateTime.parse(parts[4])));
            }
        }
        return messages;

    }

    /**
     * it just returns number of messages in RedisMailStore
     * @return it just returns number of messages
     * @throws IOException
     */
    @Override
    public int getNMessages() throws IOException {
        return numMessages;
    }

    /**
     * It's used to return all messages in MailStore
     * @return List of Messages
     * @throws IOException
     */
    @Override
    public List<Message> getMessages() throws IOException {
        String[] parts;
        List<String> mails;
        LinkedList<Message> messages = new LinkedList<>();
        for (String s : keys){
            mails = this.receiveMessages(s);
            for (String a: mails){
                parts = a.split(";");
                if (parts.length ==5){
                    messages.add( new Message( parts[0],parts[1], parts[2], parts[3], LocalDateTime.parse(parts[4])));
                }
            }
        }
        return messages;
    }
}
