package PART2;

import PART1.Message;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Spam filter that inherites from MailObserver
 */

public class SpamUserFilter extends MailObserver{

    /**
     * Method that returns the list of message which Sender had Spam at his name, and also takes that Spam messages from the mailBox that was updated.
     * @param mailbox
     * @return
     */
    @Override
    public List<Message> updateMail(MailBox2 mailbox)  {

        List<Message> spam;
        spam=mailbox.getMessages().stream().filter(message -> message.getFrom().contains(("Spam"))).collect(Collectors.toList());
        mailbox.setMessages(mailbox.getMessages().stream().filter((message -> !message.getFrom().contains("Spam"))).collect(Collectors.toList()));

        return spam;
    }
}
