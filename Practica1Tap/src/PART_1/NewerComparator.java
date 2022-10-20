package PART_1;

import java.util.Comparator;

public class NewerComparator implements Comparator<Message> {
    public int compare(Message m1, Message m2) {
        return m1.getDateTime().compareTo(m2.getDateTime());
    }
}