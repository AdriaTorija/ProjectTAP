package PART2;

import PART1.*;

import java.io.IOException;
import java.util.List;

/**
 * Decorator of Store in File that lets us encrypt and decrypt the messages of the file.
 */
public class StoreDecorator extends StoreInFile {
    private StoreInFile store;
    private Strategy strategy;

    /**
     * Constructor that needs an StoreInFile class for saving the Messages at a file an also and Strategy for encrypting and decrypting this file.
     * @param store
     * @param strategy
     */
    public StoreDecorator(StoreInFile store,Strategy strategy) {
        super("");
        this.store=store;
        this.strategy=strategy;
    }

    /**
     * Sends the message with the text encrypted
     * @param message
     * @throws IOException
     */
    @Override
    public void sendMail(Message message) throws IOException {
        message.setText(strategy.encrypt(message.getText()));
        store.sendMail(message);
    }

    /**
     * Gets the message from a user decrypting his text
     * @param user
     * @return
     * @throws IOException
     */
    @Override
    public List<Message> getMail(User user) throws IOException {
        List<Message> messages;
        messages= store.getMail(user);
        for(Message m : messages){
            m.setText(strategy.decrypt(m.getText()));
        }
        return messages;
    }

    @Override
    public int getNMessages() throws IOException {
        return store.getNMessages();
    }

    /**
     * Gets all the messages of the mailStore decrypting the text of each one.
     * @return
     * @throws IOException
     */
    @Override
    public List<Message> getMessages() throws IOException {
        List<Message> messages;
        messages= store.getMessages();
        for(Message m : messages){
            m.setText(strategy.decrypt(m.getText()));
        }
        return messages;
    }
    //Getters & setters
    public StoreInFile getStore() {
        return store;
    }

    public void setStore(StoreInFile store) {
        this.store = store;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
