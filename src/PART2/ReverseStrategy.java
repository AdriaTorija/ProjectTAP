package PART2;


/**
 * Class that implements the Strategy Pattern
 */
public class ReverseStrategy implements Strategy {
    /**
     * Method that returns a String Reversed
     * @param m Message that will be encrypted
     * @return
     */
    @Override
    public String encrypt(String m) {
        return reverse(m);
    }

    /**
     * Method that returns a String Reversed
     * @param m Message that will be decrypted
     * @return
     */
    @Override
    public String decrypt(String m) {
        return reverse(m);
    }

    /**
     * Method that return a String that was reversed.
     * @param s String that will be reversed
     * @return
     */
    public static String reverse(String s){
        String reverse= "";
        for(int i= s.length()-1;i>=0;i--){
            reverse= reverse+ s.charAt(i);
        }
        return reverse;
    }
}
