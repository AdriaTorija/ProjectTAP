package PART2;

/**
 * Strategy pattern, interface
 */
public interface Strategy {
    String encrypt(String m);
    String decrypt (String m);
}
