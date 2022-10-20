package PART1;

import java.util.Comparator;

/**
 * An implementation of comparator that chooses the one who was created first
 */
public class NewerComparator implements Comparator<Message> {
    /**
     * Compares m1 to m2 to see which one was created first.
     * @param m1
     * @param m2
     * @return
     */
    public int compare(Message m1, Message m2) {
        return m1.getDateTime().compareTo(m2.getDateTime());
    }
}