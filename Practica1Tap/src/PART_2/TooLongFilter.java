package PART_2;

import PART_1.Message;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TooLongFilter extends MailObserver {

    @Override
    public List<Message> updateMail(MailBox2 mailbox) throws IOException {
        List <Message> spam;
        //System.out.println("SPAAAAM toLong");
        spam= mailbox.getMessages().stream().filter(message -> message.getText().length()>20).collect(Collectors.toList());
        mailbox.setMessages(mailbox.getMessages().stream().filter(message -> message.getText().length()<=20).collect(Collectors.toList()));
        //System.out.println("Missatges a la mailBox no LONG "+mailbox.getMessages().toString());
        return spam;
    }
}
