package PART_2;

import PART_1.Message;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SpamUserFilter extends MailObserver{


    @Override
    public List<Message> updateMail(MailBox2 mailbox)  {

        List<Message> spam;
        spam=mailbox.getMessages().stream().filter(message -> message.getFrom().contains(("Spam"))).collect(Collectors.toList());
        mailbox.setMessages(mailbox.getMessages().stream().filter((message -> !message.getFrom().contains("Spam"))).collect(Collectors.toList()));

        return spam;
    }
}
