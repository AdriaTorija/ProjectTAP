package PART_2;

import PART_1.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class MailBox2 extends MailBox {
    List<Message> spamList;
    List<MailObserver> observers;

    public MailBox2(MailStore store, User user) {
        super(store, user);
        observers = new LinkedList<>();
        spamList = new LinkedList<>();
    }

    @Override
    public void updateMail() throws IOException {
        super.updateMail();
        for (MailObserver observer : observers) {
            spamList.addAll(observer.updateMail(this));
        }
    }
    public void attach(MailObserver observer) {
        observers.add(observer);
    }

}

