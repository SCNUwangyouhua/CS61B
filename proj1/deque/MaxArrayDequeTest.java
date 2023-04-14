package deque;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Random;
public class MaxArrayDequeTest {
    private class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    @Test
    public void testMax() {
        MaxArrayDeque<Integer> madt = new MaxArrayDeque<>(new IntComparator());

        for (int i = 0; i < 1000; i++) {
            madt.addLast(i);
        }

        assertEquals("Max integer should be 999", (int)madt.max(), 999);
    }

    @Test
    public void RandomizedTestWithMax() {
        MaxArrayDeque<Integer> madt = new MaxArrayDeque<>(new IntComparator());
        int maxVal = -1;
        for (int i = 0; i < 100000; i++) {
            int randVal = StdRandom.uniform(0, 100000000);
            madt.addLast(randVal);
            if (randVal > maxVal) {
                maxVal = randVal;
            }
        }

        assertEquals("Should have the same value", maxVal, (int)madt.max());
    }

}
