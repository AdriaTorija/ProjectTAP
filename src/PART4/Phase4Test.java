package PART4;

import PART1.Message;
import PART1.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * jUnit Phase4 tests
 */
public class Phase4Test {

    StoreUsingProxy system =new StoreUsingProxy();

    /**
     * Test to check if Mail System is well created so we can operate with it
     */
    @Test
    public void Phase4Test(){
        try{
            Message message = new Message("afdhst", "fasd`phjjkhs`gj", "sapdfhgjfm", "asdggfj");
            system.createNewUser("nick","name",23452345);
            List<User> users = system.getAllUsers();
            Assert.assertTrue(users.get(0).getNick().equals("nick"));


        }catch(Exception e){
            System.out.println("Error");

        }
    }
}
