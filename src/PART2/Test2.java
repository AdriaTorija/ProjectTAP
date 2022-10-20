package PART2;

import PART1.*;

/**
 * Testing all methods from part 2
 *
 */
public class Test2 {

    public static void main (String [ ] args) throws Exception {

        System.out.println("1.Initialize the mailSystem and mailStore");
        MailStore store= new StoreInMemory();
        MailSystem system =new MailSystem(store);

        System.out.println("\n2. Create 4 users");
        MailBox2 a= new MailBox2(store,new User("nick1","name1",1998));
        MailBox2 b= new MailBox2(store,new User("nick2","name1",-5));
        MailBox2 c= new MailBox2(store,new User("nick3 Spam","name3",2003));
        MailBox2 d= new MailBox2(store,new User("Nick","name3",2003));


        System.out.println("\n3.Sending messages");
        a.sendMail("Tap","Hem d'estudiar tots els patterns per el examen","nick1");
        a.sendMail("Tap","Estudiat el observer","nick2");
        a.sendMail("Tap","La practica de tap esta bastant guay","nick2");
        c.sendMail("TapPrac1","Recorda que si fem scala puja nota","nick3");
        a.sendMail("Tac Fase1","Hem de treballar","nick3");
        b.sendMail("Tac","Recorda que el examen de Tap es el dia 21 de gener","nick1");
        c.sendMail("Tap 22","Portem bastant be l'examen","nick1");
        d.sendMail("Tap 22","El ribe te un gosset, normalment s'estampa pero es bonic","nick1");
        system.getAllMessages().forEach(message -> System.out.println(message.toString()));
        System.out.println("Filtering Messages");
        a.attach(new TooLongFilter());
        a.attach(new SpamUserFilter());

        b.attach(new TooLongFilter());
        b.attach(new SpamUserFilter());

        c.attach(new TooLongFilter());
        c.attach(new SpamUserFilter());

        System.out.println("\n4. Update mailBoxes");
        a.updateMail();
        System.out.println("Missatges Spam A");
        a.getSpamList().forEach(message -> System.out.println(message.toString()));

        b.updateMail();
        System.out.println("Missatges a Spam B"+b.getSpamList());
        b.getSpamList().forEach(message -> System.out.println(message.toString()));

        c.updateMail();
        System.out.println("Missatges a Spam C");
        c.getSpamList().forEach(message -> System.out.println(message.toString()));
        System.out.println(c.toString());

        System.out.println("Testing Files");
        System.out.println("2. Reverse File");
        StoreInFile store1= new StoreInFile("Reverse.txt");
        MailStore store2 = new StoreDecorator(store1,new ReverseStrategy());
        MailSystem system2 =new MailSystem(store2);

        MailBox2 x= new MailBox2(store1,new User("nick1","name1",1998));
        x.setStore(store2);
        System.out.println(x.getUser().toString());

        System.out.println("\n3.Sending messages");
        x.sendMail("Tap","Student marks","nick1");
        System.out.println(system2.getAllMessages());

        System.out.println("2. Cypher File");
        store1= new StoreInFile("Cypher.txt");
        store2 = new StoreDecorator(store1,new CipherStrategy());
        system2 =new MailSystem(store2);

        x= new MailBox2(store1,new User("nick1","name1",1998));
        x.setStore(store2);
        System.out.println(x.getUser().toString());

        System.out.println("\n3.Sending messages");
        x.sendMail("Tap","Student marks","nick1");
        System.out.println(system2.getAllMessages());


        System.out.println("2. Reverse  & Cipher File");
        store1= new StoreInFile("ReverseCipher.txt");
        store2 = new StoreDecorator(new StoreDecorator(store1,new CipherStrategy()),new ReverseStrategy());
        system2 =new MailSystem(store2);

        x= new MailBox2(store1,new User("nick1","name1",1998));
        x.setStore(store2);
        System.out.println(x.getUser().toString());

        System.out.println("\n3.Sending messages");
        x.sendMail("Tap","Student marks","nick1");
        system2.getAllMessages().forEach(message -> System.out.println(message.toString()));
    }
}
