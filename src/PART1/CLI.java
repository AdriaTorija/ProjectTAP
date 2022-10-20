package PART1;

import java.util.*;

/**
 * Client
 */
public class CLI {
    private static final Scanner keyboard = new Scanner(System.in);
    private static MailStore store= new StoreInMemory();
    private static MailSystem system =new MailSystem(store);
    public static void main(String[] args) {

        int menuPrincipal = 0;
        while (menuPrincipal != 4) {
            System.out.println(menuPrincipal = Menu());
            switch (menuPrincipal) {
                case 1:
                    createUser();
                    break;
                case 2:
                    filterMessages();
                    break;
                case 3:
                   if(logIn()==-1) System.out.println("Wrong Nick");;
                    break;
                case 4:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Wrong Option");
                    break;
            }
        }
    }
    public static int Menu() {
        System.out.println("1. Add User");
        System.out.println("2. Filter");
        System.out.println("3. Login");
        System.out.println("4. Exit");
        return keyboard.nextInt();
    }

    public static int userMenu() {
        System.out.println("1. Send Message");
        System.out.println("2. Update Messages");
        System.out.println("3. List Messages");
        System.out.println("4. Sort Messages");
        System.out.println("5. Filter Messages");
        System.out.println("6. Log Out");
        return keyboard.nextInt();
    }
    public static void createUser(){
        keyboard.nextLine();
        System.out.println("User Nick");
        String nick= keyboard.nextLine();
        System.out.println("User Name");
        String name= keyboard.nextLine();
        System.out.println("Birth date: ");
        int birth = keyboard.nextInt();
        system.createNewUser(nick,name,birth);
    }
    public static void filterMessages(){
        keyboard.nextLine();

        System.out.println("1. Filter by word\n2. Filter number of words in a message");
        int op=0;
        keyboard.nextLine();
        op=keyboard.nextInt();

        if(op==1 || op==2){
            try {
                if (op == 1) {
                    keyboard.nextLine();
                    System.out.println("Write a word for filtering");
                    String paraula = keyboard.nextLine();

                    system.globalFilter(message -> message.getSubject().contains(paraula) || message.getText().contains(paraula)).forEach(System.out::println);
                } else if (op == 2) {
                    keyboard.nextLine();

                    System.out.println("Write a number for filterings");
                    int num = keyboard.nextInt();
                    system.globalFilter(message -> message.getText().split("\\s+|,").length < num).forEach(System.out::println);
                }
            }catch (Exception e){
                System.out.println("Failed at filtrering");
            }
        }
        else{
            System.out.println("Wrong option");
        }
    }

    public static int logIn(){
        int menuUser = 0;
        keyboard.nextLine();
        System.out.println("User Nick?");
        String nickLogIn= keyboard.nextLine();
        MailBox aux= system.getMailBoxs().get(nickLogIn);
        if (aux==null){
            return -1;
        }
        while(menuUser!=6) {
            menuUser = userMenu();
            switch(menuUser){
                case 1:
                    sendMessage(aux);
                    break;
                case 2:
                    update(aux);
                    break;
                case 3:
                    listMessages(aux);
                    break;
                case 4:
                    sortMessages(aux);
                    break;
                case 5:
                    filterMessagesMailBox(aux);
                    break;
                case 6:
                    System.out.println("Logging out");
                    break;
                default:
                    System.out.println("Wrong option");
            }
        }
        return 0;
    }
    public static void sendMessage(MailBox aux){
        keyboard.nextLine();
        System.out.println("Subject?");
        String subject= keyboard.nextLine();
        System.out.println("Text");
        String text=keyboard.nextLine();
        System.out.println("To");
        String to=keyboard.nextLine();
        try{
            aux.sendMail(subject,text,to);
        }
        catch (Exception e){
            System.out.println("Failed at sendig a message");
        }
    }
    public static void update(MailBox aux){
        try{
            aux.updateMail();
        }
        catch (Exception e){
            System.out.println("Failed at sendig a message");
        }

    }
    public static void listMessages(MailBox aux){
            aux.getMessages().forEach(m -> System.out.println(m.toString()));
    }
    public static void sortMessages(MailBox aux){
        aux.setMessages(aux.getSortedMails(new NewerComparator()));
    }
    public static void filterMessagesMailBox(MailBox aux){

        System.out.println("1. Filter by word\n2. Filter number of words in a message");
        int op=0;
        keyboard.nextLine();
        op=keyboard.nextInt();

        if(op==1 || op==2){
            if(op==1){
                keyboard.nextLine();
                System.out.println("Escriu la paraula");
                String paraula = keyboard.nextLine();
                aux.filterUserMail((message -> message.getSubject().contains(paraula) || message.getText().contains(paraula))).forEach(System.out::println);
            }
            else if(op==2){
                keyboard.nextLine();
                System.out.println("Escriu numero de paraules");
                int num= keyboard.nextInt();
                aux.filterUserMail(message -> message.getText().split("\\s+|,").length < num).forEach(System.out::println);
            }
        }
        else{
            System.out.println("Wrong option");
        }


    }
}



