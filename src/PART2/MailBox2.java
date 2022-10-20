package PART2;

import PART1.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Extension of MailBox that will be able to save observers and also a list of Spam
 */
public class MailBox2 extends MailBox {
    private List<Message> spamList;
    private List<MailObserver> observers;

    /**
     * Constructor of Mailbox that also creates the list of Observers and the List of the Spam Messages.
     * @param store
     * @param user
     */
    public MailBox2(MailStore store, User user) {
        super(store, user);
        observers = new LinkedList<>();
        spamList = new LinkedList<>();
    }

    /**
     * Method that updates mail and also advice all the observers.
     * @throws IOException
     */
    @Override
    public void updateMail() throws IOException {
        super.updateMail();
        for (MailObserver observer : observers) {
            spamList.addAll(observer.updateMail(this));
        }
    }

    /**
     * Method to add and observer to the MailBox.
     * @param observer
     */
    public void attach(MailObserver observer) {
        observers.add(observer);
    }

    public List<Message> getSpamList() {
        return spamList;
    }

    public List<MailObserver> getObservers() {
        return observers;
    }

    public void setSpamList(List<Message> spamList) {
        this.spamList = spamList;
    }

    public void setObservers(List<MailObserver> observers) {
        this.observers = observers;
    }
}

