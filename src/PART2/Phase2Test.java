package PART2;

import PART1.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for Phase2
 */
public class Phase2Test {
    MailStore store = new StoreInMemory();
    MailSystem system = new MailSystem(store);
    MailBox2 a= new MailBox2(store,new User("OuDret Spam","Ribe",2000));
    MailBox2 b=  new MailBox2(store,new User("Predatormator","Adria",2000));
    MailBox2 c=  new MailBox2(store,new User("Pato","Lauter",1992));

    StoreInFile store2 = new StoreInFile("TestJunit.txt");
    @Test
    /**
     * Test that looks if the spamList has increased to see if the Observer SpamUser is working. 2 spam messages if it increased by 2: Test Passed
     */
    public void updateFilteringSpamList(){
        int aux= b.getSpamList().size();
        try{
            b.attach(new SpamUserFilter());
            a.sendMail("Tap","Do something","Predatormator");
            a.sendMail("Tap","3","Predatormator");
            b.sendMail("Tap2","2","Predatormator");
            b.sendMail("TapScala","MailTesting","Predatormator");
            c.sendMail("Tap2","Recorda","Predatormator");
            b.sendMail("Tap3","Recorda 3","Predatormator");
            b.updateMail();
            System.out.println("-> TESTING SPAMSPAMLIST..");
            Assert.assertTrue(aux+2 == b.getSpamList().size());
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that looks if the messageList has decreased to see if the ObserverSpam is working. 2 spam messages if it decreased by 2: Test Passed
     */
    @Test
    public void updateFilteringSpamMessageList(){

        try{
            a.sendMail("Tap","Do something","Predatormator");
            a.sendMail("Tap","3","Predatormator");
            b.sendMail("Tap2","2","Predatormator");
            b.sendMail("TapScala","MailTesting","Predatormator");
            c.sendMail("Tap2","Recorda","Predatormator");
            b.sendMail("Tap3","Recorda 3","Predatormator");
            b.updateMail();
            int aux= b.getMessages().size();
            b.attach(new SpamUserFilter());
            b.updateMail();
            System.out.println("-> TESTING SPAMLIST..");
            Assert.assertTrue(aux-2 == b.getMessages().size());
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that looks if the spamList has increased to see if the ObserverToLong is working. 2 spam messages if it increased by 2: Test Passed
     */
    @Test
    public void updateFilteringSpamToLong(){
        int aux= b.getSpamList().size();
        try{
            b.attach(new TooLongFilter());
            a.sendMail("Tap","Do something","Predatormator");
            b.sendMail("Tap2","2","Predatormator");
            b.sendMail("TapScala","MailTesting its not hard enough just giving another spam","Predatormator");
            c.sendMail("Tap2","Recorda Its not spamming it just advicing new content, whatever","Predatormator");
            b.sendMail("Tap3","Recorda 3","Predatormator");
            b.updateMail();
            System.out.println("-> TESTING TOLONGSPAM..");
            Assert.assertTrue(aux+2 == b.getSpamList().size());
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that looks if the messageList has decreased to see if the ObserverToLong is working. 2 spam messages if it decreased by 2: Test Passed
     */
    @Test
    public void updateToLongList(){

        try{
            a.sendMail("Tap","Do something","Predatormator");
            b.sendMail("Tap2","2","Predatormator");
            b.sendMail("TapScala","MailTesting its not hard enough just giving another spam","Predatormator");
            c.sendMail("Tap2","Recorda Its not spamming it just advicing new content, whatever","Predatormator");
            b.sendMail("Tap3","Recorda 3","Predatormator");
            b.updateMail();
            int aux= b.getMessages().size();
            b.attach(new TooLongFilter());
            b.updateMail();
            System.out.println("-> TESTING TOLONGLIST..");
            Assert.assertTrue(aux-2 == b.getMessages().size());
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that tries booth filters. There are 3 spam messages. 2 Toloong and 1 SpamUser. If the size of the spam is 3 : Test Passed;
     */
    @Test
    public void bothFilteringObservers(){
        int aux= b.getSpamList().size();
        try{
            b.attach(new TooLongFilter());
            b.attach(new SpamUserFilter());
            a.sendMail("Tap","Do something","Predatormator");
            b.sendMail("Tap2","2","Predatormator");
            b.sendMail("TapScala","MailTesting its not hard enough just giving another spam","Predatormator");
            c.sendMail("Tap2","Recorda Its not spamming it just advicing new content, whatever","Predatormator");
            b.sendMail("Tap3","Recorda 3","Predatormator");
            b.updateMail();
            System.out.println("-> TESTING SPAM&TOLONGOBSERVER..");
            Assert.assertTrue(aux+3 == b.getSpamList().size());
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * To see if this test works you have to look the file TestJunit to see if its encoded as expected. Due to if you want to get the message you get it already decoded.
     * The result should be: skram tnedutS
     */

    @Test
    public void encodingReverse(){
        User user = new User("OuDret", "Ribe", 2000);
        Message m = new Message("OuDret", "Student marks", "A", "hola");
        try{
            StoreDecorator auxStore = new StoreDecorator((store2),new ReverseStrategy());
            int aux = auxStore.getMail(user).size();
            auxStore.sendMail(m);
            auxStore.getMail(user);
            System.out.println("-> TESTING REVERSESTRATEGY..");
            Assert.assertTrue(auxStore.getMail(user).size() == (aux));
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
    /**
     * To see if this test works you have to look the file TestJunit to see if its encoded as expected. Due to if you want to get the message you get it already decoded.
     * The result should be: 7dhHa8mWwkNaxUDKbnqIZw==
     */
    @Test
    public void encodingCipher(){
        User user = new User("OuDret", "Ribe", 2000);
        Message m = new Message("OuDret", "Student marks", "A", "hola");

        try{
            StoreDecorator auxStore = new StoreDecorator((store2),new CipherStrategy());
            int aux = auxStore.getMail(user).size();
            auxStore.sendMail(m);
            auxStore.getMail(user);
            System.out.println("-> TESTING CIPHERSTRATEGY..");
            Assert.assertTrue(auxStore.getMail(user).size() == (aux));
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * To see if this test works you have to look the file TestJunit to see if its encoded as expected. Due to if you want to get the message you get it already decoded.
     * The result should be: 9v8ZO/cqSiVH5xCBYsrzqQ==
     */
    @Test
    public void encodingCipherReverse(){
        User user = new User("OuDret", "Ribe", 2000);
        Message m = new Message("OuDret", "Student marks", "A", "hola");

        try{
            StoreDecorator auxStore = new StoreDecorator(new StoreDecorator((store2),new CipherStrategy()),new ReverseStrategy());
            int aux = auxStore.getMail(user).size();
            auxStore.sendMail(m);
            auxStore.getMail(user);
            System.out.println("-> TESTING REVERSECIPHERSTRATEGY..");
            Assert.assertTrue(auxStore.getMail(user).size() == (aux));
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
