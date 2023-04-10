package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Created by hug.
 */
public class TestBuggyAList {

  // YOUR TESTS HERE
    public static void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeBL = BL.size();
                System.out.println("L.size: " + size + " BL.size = " + sizeBL);
                assertEquals(size, sizeBL);
            } else if (operationNumber == 2 && L.size() > 0 && BL.size() > 0){//getLast
                int LastVal = L.getLast();
                int BLastVal = BL.getLast();
                System.out.println("L lastVal = " + LastVal + " BL lastVal = " + BLastVal);
                assertEquals(LastVal, BLastVal);
            } else if (operationNumber == 3 && L.size() > 0 && BL.size() > 0){//removeLast
                int ret = L.removeLast();
                int ret2 = BL.removeLast();
                System.out.println("called to removeLast");
                assertEquals(ret, ret2);
            }
        }
    }

    public static void main(String[] args) {
        randomizedTest();
    }

}
