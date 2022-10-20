package PART_3;

import PART_1.Message;
import PART_1.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * jUnit phase3 tests
 */
public class Phase3test {

    RedisMailStore redis= RedisMailStore.getInstance();
    Message message = new Message("afdhst", "fasd`phjjkhs`gj", "sapdfhgjfm", "asdggfj");
    Message message1 = new Message("afdhst", "fasd`phjjkhs`gj", "sapdfhgjfm", "asgfj");
    User user = new User("asdggfj", "fdsohjpsfpdh",4305);


    /**
     * Test to check if we can Send and get mails using sendMail() and getMail()
     */
    @Test
    public void RediSendandGetMailTest(){
        try{
            redis.sendMail(message);
            List<Message> messages=redis.getMail(user);
            Assert.assertTrue(messages.get(0).getTo().equals(message.getTo()));
        }catch(Exception e){
            System.out.println("Error at ");
        }
    }

    /**
     * Test to see if we can get Messages using getMessageTest()
     * need to Fflush in server before run
     */
    @Test
    public void getMessageTest(){
        try{
            redis.sendMail(message1);
            List<Message> messages =redis.getMessages();
            Assert.assertTrue((messages.get(0).getTo().equals(message1.getTo())) && redis.getMessages().get(1).getTo().equals(message1.getTo()));
        }catch(Exception e){
            System.out.println("Error");
        }

    }

    /**
     * Test to check if getNmessages() returns all messages in RedisMailStore
     */
    @Test
    public void numMessagesTest(){
        try{
            int num = redis.getNMessages();
            redis.sendMail(message);
            redis.sendMail(message1);
            Assert.assertTrue(redis.getNMessages()==(num+2));
        }catch(Exception e){
            System.out.println("Error");

        }
    }
}
