package PART_2;


import PART_1.Message;

public class ReverseStrategy implements Strategy {
    @Override
    public String encrypt(String m) {
        return reverse(m);
    }

    @Override
    public String decrypt(String m) {
        return reverse(m);
    }

    public static String reverse(String s){
        String reverse= "";
        for(int i= s.length()-1;i>=0;i--){
            reverse= reverse+ s.charAt(i);
        }
        return reverse;
    }
}
