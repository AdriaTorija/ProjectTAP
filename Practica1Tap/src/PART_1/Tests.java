package PART_1;

import java.io.IOException;


public class Tests {
    public static void main (String [ ] args) throws IOException {
        System.out.println("1.Initialize the mailSystem and mailStore");
        MailStore store= new StoreInFile("missatges.txt");
        MailSystem system =new MailSystem(store);

        System.out.println("\n2. Create 3 users");
        MailBox a=system.createNewUser("nick1","name1",1998);
        MailBox b=system.createNewUser("nick2","name1",-5);
        MailBox c=system.createNewUser("nick3","name3",2003);
        System.out.println(a.getUser().toString());
        System.out.println(b.getUser().toString());
        System.out.println(c.getUser().toString());

        System.out.println("\n3.Sending messages");
        a.sendMail("Tap","El ribe no treballa, sol es rasca la panxa","nick1");
        a.sendMail("Tap","El ribe no treballa","nick2");
        a.sendMail("Tap","El ribe no treballa ni que l'amenacin","nick2");
        c.sendMail("TapPrac1","El ribe no treballa ni que el paguin","nick3");
        a.sendMail("Tac Fase1","El ribe no treballa mai","nick3");
        b.sendMail("Tac","El ribe no treballa, sol juga amb el gos","nick1");
        c.sendMail("Tap 22","El ribe no treballa, sol juga amb el gosset","nick1");
        System.out.println(system.getAllMessages());

        System.out.println("\n4. Update mailBoxes");
        a.updateMail();
        System.out.println(a.toString());
        b.updateMail();
        System.out.println(b.toString());
        c.updateMail();
        System.out.println(c.toString());

        System.out.println("\n5. List the mailBoxMessages");
        System.out.println(a.getSortedMails(new NewerComparator()));

        System.out.println("\n6 List the Messages by sender Username");
        System.out.println(a.filterUserMail(message -> message.getTo().contains("nick1")));
        System.out.println(b.filterUserMail(message -> message.getTo().contains("nick2")));
        System.out.println(c.filterUserMail(message -> message.getTo().contains("nick3")));

        System.out.println("\n7.1 Filter the messages Subject");
        System.out.println(system.filter(message -> message.getSubject().contains("Tap")));
        System.out.println(system.filter(message -> message.getSubject().contains("TapPrac1")));
        System.out.println("\n7.2 Filter the messages User");
        System.out.println(system.filter(message -> message.getFrom().equals("nick1")));
        System.out.println(system.filter(message -> message.getFrom().equals("nick2")));

        System.out.println("\n8 MailSystem Print");
        System.out.println(system.getAllMessages());

        System.out.println("\n9.1 Filter the messages Subject SingleWord");
        System.out.println(system.filter(message -> message.getSubject().equals("Tac")));
        System.out.println(system.filter(message -> message.getSubject().equals("Tap")));
        System.out.println("\n9.2 Filter the messages Sender was born after year 2000");
        //System.out.println(system.filter(message -> message.g));

        System.out.println("\n10 Count messages");
        System.out.println("There are:"+system.getNMessages()+" messages");

        System.out.println("\n11 Average number received per user");
        System.out.println(system.averageMessageUser());

        System.out.println("\n12 Group messages in a Map");
        System.out.println(system.messagesSubject());

        System.out.println("\n13 Count the words by a Name");
        System.out.println("Name2:"+system.nWordsMessagesUser("name2"));
        System.out.println("Name3:"+system.nWordsMessagesUser("name3"));

        System.out.println("\n14 Same name print");
        System.out.println("Name1:"+system.nWordsMessagesUser("name1"));

        System.out.println("\n15 Messages Users Born < 2000");
        System.out.println(system.messagesUsersYear(2000));

        System.out.println("\n16 ");
        system.setmStore(store=new StoreInFile("messages"));


    }
}
