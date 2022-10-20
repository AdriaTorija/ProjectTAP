package PART_1;

import PART_3.Phase3test;
import PART_4.Phase4Test;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;

/**
 * jUnit Test main that runs all @tests
 */
public class MailTestRunner {

    public static void main(String[] args) {

        Result result;

        result = JUnitCore.runClasses(Phase3test.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("test 3 validation " + result.wasSuccessful() + "\n\n");


        result = JUnitCore.runClasses(Phase4Test.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("test 4 validation " + result.wasSuccessful() + "\n\n");

    }

}