package PART3;

import PART1.*;

import java.io.IOException;

/**
 * Testing all methods from part 3
 *
 */
public class Test3RedisMailStore {
    /**
     * Phase1 tests using Factories and RedisMailStore
     * @param args
     * @throws IOException
     */
    public static void main (String [ ] args) throws IOException {
            System.out.println("1.Initialize the mailSystem and mailStore");

            FactoryMailStore pf= new RedisFactory();
            //FactoryMailStore pf= new StoreInMemoryFactory();
            //FactoryMailStore pf= new StoreInFileFactory();
            MailStore store=pf.createProduct();

            MailSystem system = new MailSystem(store);


            System.out.println("\n2. Create 3 users");
            MailBox a=system.createNewUser("nick1","name1",1998);
            MailBox b=system.createNewUser("nick2","name1",-5);
            MailBox c=system.createNewUser("nick3","name3",2003);
            System.out.println(a.getUser().toString());
            System.out.println(b.getUser().toString());
            System.out.println(c.getUser().toString());

            System.out.println("\n3.Sending messages");
            a.sendMail("Tap","Hem d'estudiar tots els patterns per el examen","nick1");
            a.sendMail("Tap","Estudiat el observer","nick2");
            a.sendMail("Tap","La practica de tap esta bastant guay","nick2");
            c.sendMail("TapPrac1","Recorda que si fem scala puja nota","nick3");
            a.sendMail("Tac Fase1","Hem de treballar","nick3");
            b.sendMail("Tac","Recorda que el examen de Tap es el dia 21 de gener","nick1");
            c.sendMail("Tap 22","Portem bastant be l'examen","nick1");
            system.getAllMessages().forEach(message -> System.out.println(message.toString()));

            System.out.println("\n4. Update mailBoxes");
            a.updateMail();
            System.out.println("1st mailBox:");
            a.getMessages().forEach(message -> System.out.println(message.toString()));
            b.updateMail();
            System.out.println("\n2nd mailBox:");
            b.getMessages().forEach(message -> System.out.println(message.toString()));
            System.out.println("\n3rd mailBox:");
            c.updateMail();
            c.getMessages().forEach(message -> System.out.println(message.toString()));

            System.out.println("\n5. List the mailBoxMessages");
            a.getSortedMails(new NewerComparator()).forEach(System.out::println);

            System.out.println("\n6 List the Messages by sender Username");
            System.out.println("1st mailBox:");
            a.filterUserMail(message -> message.getTo().contains("nick1")).forEach(System.out::println);
            System.out.println("\n2nd mailBox:");
            b.filterUserMail(message -> message.getTo().contains("nick2")).forEach(System.out::println);
            System.out.println("\n3rd mailBox:");
            c.filterUserMail(message -> message.getTo().contains("nick3")).forEach(System.out::println);

            System.out.println("\n7.1 Filter the messages Subject");
            system.globalFilter(message -> message.getSubject().contains("Tap")).forEach(message -> System.out.println(message.toString()));
            system.globalFilter(message -> message.getSubject().contains("TapPrac1")).forEach(message -> System.out.println(message.toString()));
            System.out.println("\n7.2 Filter the messages User");
            system.globalFilter(message -> message.getFrom().equals("nick1")).forEach(message -> System.out.println(message.toString()));
            System.out.println(system.globalFilter(message -> message.getFrom().equals("nick2")));

            System.out.println("\n8 MailSystem Print");
            system.getAllMessages().forEach(message -> System.out.println(message.toString()));

            System.out.println("\n9.1 Filter the messages Subject SingleWord");
            system.globalFilter(message -> message.getSubject().split("\\s+|,").length <2).forEach(message -> System.out.println(message.toString()));
            System.out.println("\n9.2 Filter the messages Sender was born after year 2000");
            system.globalFilter(message -> {
                    MailBox aux= system.getMailBoxs().get(message.getFrom());
                    return aux.getUser().getBirthYear() > 2000;
            }).forEach(System.out::println);

            System.out.println("\n10 Count messages");
            System.out.println("There are:"+system.nMessages()+" messages");

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
            system.messagesUsersYear(2000).forEach(message -> System.out.println(message.toString()));

            System.out.println("\n16 ");
            system.setmStore(store=new StoreInFile("messages"));


    }
}


