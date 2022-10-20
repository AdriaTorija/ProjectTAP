package PART2;

import java.io.IOException;
import PART1.*;
import java.util.*;

/**
 * Abstract class for the implementation of the Observer Pattern.
 */
public abstract class MailObserver {
    public abstract List<Message> updateMail(MailBox2 mailbox)throws IOException;
}
