package PART_2;

import java.io.IOException;
import PART_1.*;
import java.util.*;

public abstract class MailObserver {
    public abstract List<Message> updateMail(MailBox2 mailbox)throws IOException;
}
