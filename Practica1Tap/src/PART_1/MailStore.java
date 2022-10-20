package PART_1;

import java.io.IOException;
import java.util.*;

public interface MailStore {
   void sendMail(Message message) throws IOException;
   List<Message> getMail(User user)throws IOException;
   int getNMessages()throws IOException;
   List<Message>getMessages()throws IOException;
}
