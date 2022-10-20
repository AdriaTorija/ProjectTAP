package PART2;

import javax.crypto.Cipher;
import java.util.*;

/**
 * Class that implements the interface Strategy
 */
public class CipherStrategy implements Strategy {
    private String key;
    private java.security.Key aesKey;
    private Cipher cipher;

    /**
     * Constructor to set the parameters for encrypting and decrypting
     * @throws Exception
     */
    public CipherStrategy() throws Exception{
        key= "IWantToPassTAP12";
        aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
       cipher = Cipher.getInstance("AES");
    }

    /**
     * Method that encrypts a given String.
     * @param m
     * @return
     */
    @Override
    public String encrypt(String m) {
        byte[] encrypted = new byte[0];
        try {
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encrypted = cipher.doFinal(m.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * Method that decrypts a given String.
     * @param m
     * @return
     */
    @Override
    public String decrypt(String m) {
        byte[] encrypted = Base64.getDecoder().decode(m.getBytes());
        String decrypted = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decrypted = new String(cipher.doFinal(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }
}
