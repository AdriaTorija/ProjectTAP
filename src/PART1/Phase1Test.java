package PART1;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Phase1Test {
    MailStore store = new StoreInMemory();
    MailSystem system = new MailSystem(store);
    MailBox a = system.createNewUser("OuDret","Ribe",2000);
    MailBox b= system.createNewUser("Predatormator","Adria",2000);
    MailBox c= system.createNewUser("Pato","Lauter",1992);
    //Test of MailBoxs

    /**
     * Test that sends a mail from an user to another and updates the mailBox of the receiver. If it has increased by 1 : Test Passed
     */
    @Test
    public void sendAndUpdateMailBox(){
        int before = a.getMessages().size();
        try{
            b.sendMail("Tap", "Test1", "OuDret");
            a.updateMail();
            System.out.println("-> TESTING SEND AND UPDATE...");
            Assert.assertTrue((before+1) == a.getMessages().size());
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that sorts the mailbox by his date Creation. If its Sorted correctly : Test passed
     */
    @Test
    public void SortMailBox(){
        try{
            b.sendMail("Tap","3","OuDret");
            b.sendMail("Tap","2","OuDret");
            b.sendMail("Tap","1","OuDret");
            List<Message> aux = new LinkedList<>();
            System.out.println("-> TESTING SORT MAILBOX...");
            Assert.assertTrue(a.getSortedMails(new NewerComparator()).equals(a.getMessages().stream().sorted(new NewerComparator()).collect(Collectors.toList())));
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that filters  the messages by a predict. Tested by subject. If it is the same List : Test Passed
     */
    @Test
    public void FilterUserMailBox(){
        try{
            b.sendMail("Tap","3","OuDret");
            b.sendMail("Tap2","2","OuDret");
            b.sendMail("TapScala","1","OuDret");

            List<Message> aux = new LinkedList<>();
            System.out.println("-> TESTING FILTER MAILBOX...");
           Assert.assertTrue(a.filterUserMail(message -> message.getSubject().contains("Tap")).equals(b.getMessages().stream().filter(message -> message.getSubject().contains("Tap")).collect(Collectors.toList())));
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Test of MailSystem

    /**
     * Test of Creating a New User at the system. Tested by comparing with an User with same nick name and birthYear. If its the same: Test passed.
     */
    @Test
    public void newUserSystem(){
        try{
            system.createNewUser("Rick","Mickey",2003);
            User aux = new User("Rick","Mickey",2003);
            System.out.println("-> TESTING NEW USER SYSTEM...");
            Assert.assertTrue(system.getMailBoxs().get("Rick").getUser().equals(aux));
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that compares the size of the listOfUsers by adding an User. If the first size +1 equals the second getAllUsers.size() after adding an User: Test passed
     */
    @Test
    public void getAllUsers(){

        try{
            int aux= system.getAllUsers().size();
            system.createNewUser("Peter","Pan",1953);
            System.out.println("-> TESTING GETALLUSERS SYSTEM...");
            Assert.assertTrue(system.getAllUsers().size() == aux+1);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that compares the size all alMessages by adding a message. If the first size +1 equals the second getAllMessages.size() after adding a message: Test passed
     */
    @Test
    public void getAllMessages(){
        try{
            int aux= system.getAllMessages().size();
            b.sendMail("Tap","Do something","Pato");
            System.out.println("-> TESTING GETALLMESSAGES SYSTEM...");
            Assert.assertTrue(system.getAllMessages().size() == aux+1);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that compares the globalFilter if the lists are the same: Test Passed
     */
    @Test
    public void globalFilter(){
        try{
            int aux= system.getAllMessages().size();
            b.sendMail("Tap","Do something","Pato");
            c.sendMail("Tap","3","OuDret");
            b.sendMail("Tap2","2","OuDret");
            c.sendMail("TapScala","1","OuDret");
            System.out.println("-> TESTING GLOBALFILTER SYSTEM...");

            Assert.assertTrue(system.globalFilter(message -> message.getSubject() .equals("Tap")).equals(system.getAllMessages().stream().filter(m -> m.getSubject().equals("Tap")).collect(Collectors.toList())));
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that compares the number of Messages by adding a message. If the first size +1 equals the second nMessages after adding a message: Test passed
     */
    @Test
    public void nMessages(){
        try{
            int aux= system.nMessages();
            b.sendMail("Tap","Treball","Oudret");
            System.out.println("-> TESTING NMESSAGES SYSTEM...");
            Assert.assertTrue(system.nMessages()==aux+1);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that compares the average made by the system with the average if its equal : Test passed
     */
    @Test
    public void averageMessagesUsers(){
        try{
            b.sendMail("Tap","Do something","Pato");
            c.sendMail("Tap","3","OuDret");
            b.sendMail("Tap2","2","OuDret");
            c.sendMail("TapScala","1","OuDret");
            a.updateMail();
            c.updateMail();
            System.out.println("-> TESTING AVERAGEMESSAGESUSER SYSTEM...");
            Assert.assertTrue(system.averageMessageUser() == (float)system.nMessages()/system.getAllUsers().size());
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that gets the messsages grouped by a subject. If messagesSubject() is equal to expected group : Test passed
     */
    @Test
    public void messagesSubject(){
        try{
            b.sendMail("Tap","Do something","Pato");
            c.sendMail("Tap","3","OuDret");
            b.sendMail("Tap2","2","OuDret");
            c.sendMail("TapScala","1","OuDret");
            a.updateMail();
            c.updateMail();
            System.out.println("-> TESTING MESSAGESSUBJECT SYSTEM...");
            Assert.assertTrue(system.messagesSubject().equals(system.getAllMessages().stream().collect(Collectors.groupingBy(Message::getSubject))));
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that compares the numberOfWords that has an User by adding a message with specific words. If the first size +thesize of the new Text of the message equals the second nWordsMessagesUsers() after adding a message: Test passed
     */
    @Test
    public void nWordsMessagesUser(){
        int aux= system.nWordsMessagesUser("Ribe");
        try{
            b.sendMail("Tap","1234","OuDret");
            a.updateMail();
            System.out.println("-> TESTING NWORDSMESSAGEUSER SYSTEM...");
            Assert.assertTrue(system.nWordsMessagesUser("Ribe")== aux+4);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Test that compares the number of Messages by adding 2 messages. If the first size +2 equals the second nMessages after adding a message: Test passed
     */
    @Test
    public void messagesUserYear(){
        try{
            int aux= system.messagesUsersYear(2000).size();
            b.sendMail("Tap","Do something","Pato");
            c.sendMail("Tap","3","Pato");
            b.sendMail("Tap2","2","OuDret");
            c.sendMail("TapScala","1","OuDret");
            a.updateMail();
            c.updateMail();
            System.out.println("-> TESTING MESSAGESUSERYEAR SYSTEM...");
            Assert.assertTrue(system.messagesUsersYear(2000).size() == aux+2);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
