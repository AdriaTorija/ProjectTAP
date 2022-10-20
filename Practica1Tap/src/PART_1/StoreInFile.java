package PART_1;


import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class StoreInFile implements MailStore{
    String fileName;

    public StoreInFile(String name){
        this.fileName=name;
    }

    public StoreInFile(){
        this.fileName="fitxer.txt";
    }
    @Override
    public void sendMail(Message message)throws IOException {
        FileWriter fw= new FileWriter(fileName,true);
        BufferedWriter bw= new BufferedWriter(fw);

        bw.write(message.getFrom()+";"+message.getSubject()+";"+message.getText()+";"+message.getTo()+";"+message.getDateTime()+"\n");
        bw.close();
        fw.close();
    }

    @Override
    public List<Message> getMail(User user) throws  IOException{
        FileReader fr= new FileReader(fileName);
        BufferedReader br= new BufferedReader(fr);

        LinkedList<Message> messages = new LinkedList<>();

        String line;
        while((line=br.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,";");
            while(st.hasMoreTokens()){
                String nick= st.nextToken();
                if(nick.equals(user.getNick())){
                    Message aux = new Message(st.nextToken(),st.nextToken(),nick,st.nextToken(), LocalDateTime.parse(st.nextToken()));
                    messages.add(aux);
                }
                else{
                    break;
                }
            }
        }
        br.close();
        fr.close();
        return messages;

    }

    @Override
    public int getNMessages()throws  IOException {
        FileReader fr= new FileReader(fileName);
        BufferedReader br= new BufferedReader(fr);

        int n= (int)br.lines().count();
        fr.close();
        br.close();
        return n;
    }

    @Override
    public List<Message> getMessages()throws  IOException {
        FileReader fr= new FileReader(fileName);
        BufferedReader br= new BufferedReader(fr);

        LinkedList<Message> messages = new LinkedList<>();

        String line;
        while((line=br.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,";");
            while(st.hasMoreTokens()){
                    String nick=st.nextToken();
                     Message aux = new Message(st.nextToken(),st.nextToken(),nick,st.nextToken(), LocalDateTime.parse(st.nextToken()));
                    messages.add(aux);
            }
        }
        br.close();
        fr.close();
        return messages;

    }


}