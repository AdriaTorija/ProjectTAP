package PART_2;

import PART_1.*;

import java.io.IOException;
import java.util.List;

public class StoreDecorator extends StoreInFile {
    StoreInFile store;
    Strategy strategy;
    public StoreDecorator(StoreInFile store,Strategy strategy) {
        super("");
        this.store=store;
        this.strategy=strategy;
    }

    @Override
    public void sendMail(Message message) throws IOException {
        message.setText(strategy.encrypt(message.getText()));
        store.sendMail(message);
    }

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

    @Override
    public List<Message> getMessages() throws IOException {
        List<Message> messages;
        messages= store.getMessages();
        for(Message m : messages){
            m.setText(strategy.decrypt(m.getText()));
        }
        return messages;
    }

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
