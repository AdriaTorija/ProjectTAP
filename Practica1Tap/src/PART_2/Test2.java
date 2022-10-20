package PART_2;

import PART_1.*;

import java.io.IOException;

public class Test2 {


    public static void main (String [ ] args) throws Exception {
   /*
        System.out.println("1.Initialize the mailSystem and mailStore");
        MailStore store= new StoreInMemory();
        MailSystem system =new MailSystem(store);

        System.out.println("\n2. Create 3 users");
        MailBox2 a= new MailBox2(store,new User("nick1","name1",1998));
        MailBox2 b= new MailBox2(store,new User("nick2","name1",-5));
        MailBox2 c= new MailBox2(store,new User("nick3 Spam","name3",2003));
        MailBox2 d= new MailBox2(store,new User("Nick","name3",2003));
        System.out.println(a.getUser().toString());
        System.out.println(b.getUser().toString());
        System.out.println(c.getUser().toString());

        System.out.println("\n3.Sending messages");
        a.sendMail("Tap","El ribe no treballa","nick1");
        a.sendMail("Tap Spam","El ribe no treballa","nick2");
        a.sendMail("Tap","El ribe no treballa ni que l'amenacin","nick2");
        c.sendMail("TapPrac1","El ribe no treballa ni que el paguin","nick3");
        a.sendMail("Tac Fase1","El ribe no treballa mai","nick3");
        b.sendMail("Tac ","El ribe","nick1");
        c.sendMail("Tap 22","El ribe no","nick1");
        d.sendMail("Tap 22","El ribe no treballa, sol juga amb el gossetsssssssssssssssssssssssssssssssssssssssssssssssssssssssssss","nick1");
        System.out.println(system.getAllMessages());

        a.attach(new TooLongFilter());
        a.attach(new SpamUserFilter());

        //b.attach(new TooLongFilter());
        //b.attach(new SpamUserFilter());

        //c.attach(new TooLongFilter());
        //c.attach(new SpamUserFilter());

        System.out.println("\n4. Update mailBoxes");
        a.updateMail();

        System.out.println("Missatges Spam A"+a.getSpamList().toString());
        System.out.println("Mailbox A: "+a.toString());

        /*b.updateMail();
        System.out.println("Missatges a Spam B"+b.getSpamList());
        System.out.println(b.toString());

        c.updateMail();
        System.out.println("Missatges a Spam C"+c.getSpamList());
        System.out.println(c.toString());

         */

        System.out.println("1.Initialize the mailSystem and mailStore");
        StoreInFile store= new StoreInFile("Reverse.txt");
        MailStore store1 = new StoreDecorator(new StoreDecorator(store,new CipherStrategy()),new ReverseStrategy());
        MailSystem system =new MailSystem(store1);

        System.out.println("\n2. Create 3 users");
        MailBox2 a= new MailBox2(store1,new User("nick1","name1",1998));
        a.setStore(store1);
        System.out.println(a.getUser().toString());


        System.out.println("\n3.Sending messages");
        a.sendMail("Tap","Student marks","nick1");
        System.out.println(system.getAllMessages());




    }
}
