package PART_2;

import PART_1.*;
import java.util.*;
public interface Strategy {
    String encrypt(String m);
    String decrypt (String m);
}
