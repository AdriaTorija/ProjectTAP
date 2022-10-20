package PART1;

//import PART_3.Phase3test;
import PART3.Phase3test;
import PART4.Phase4Test;
import PART2.Phase2Test;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;

/**
 * jUnit Test main that runs all @tests
 */
public class MailTestRunner {

    public static void main(String[] args) {

        Result result;
        result = JUnitCore.runClasses(Phase1Test.class);
        for (Failure failure : result.getFailures()) {
           System.out.println(failure.toString());
         }
        System.out.println("test 1 validation " + result.wasSuccessful() + "\n\n");

        result = JUnitCore.runClasses(Phase2Test.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("test 2 validation " + result.wasSuccessful() + "\n\n");
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