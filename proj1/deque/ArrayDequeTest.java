package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;
public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> lld1 = new ArrayDeque<>();

        assertTrue("Shoule be empty after a newly initialized", lld1.isEmpty());

        lld1.addFirst("front");
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contains 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    public void addRemoveTest() {
        ArrayDeque adtest = new ArrayDeque<>();

        adtest.addFirst(10);
        adtest.removeFirst();

        assertTrue("should be empty after remove", adtest.isEmpty());
    }

    @Test
    public void removeEmptyTest() {
        ArrayDeque<Integer> adtest = new ArrayDeque<>();

        adtest.addFirst(3);

        adtest.removeFirst();
        adtest.removeLast();
        adtest.removeFirst();
        adtest.removeLast();

        int size = adtest.size();
        assertEquals("ArrayDeque's size should be 0", 0, size);
    }

    @Test
    public void multipleParamTest() {
        ArrayDeque<String> adtest1 = new ArrayDeque<>();
        ArrayDeque<Double> adtest2 = new ArrayDeque<>();
        ArrayDeque<Boolean> adtest3 = new ArrayDeque<>();

        adtest1.addFirst("string");
        adtest2.addLast(3.14159);
        adtest3.addLast(true);

        String one = adtest1.removeFirst();
        Double two = adtest2.removeFirst();
        Boolean three = adtest3.removeLast();

        assertEquals("string", one);
        assertEquals((Double)3.14159, two);
        assertEquals(true, three);
    }

    @Test
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> adt = new ArrayDeque<>();
        assertNull("should return null when removeFirst is called based on empty Deque", adt.removeFirst());
        assertNull("should return null when removeLast is called based on empty Deque", adt.removeLast());
    }

    @Test
    public void bigDequeTest() {
        ArrayDeque<Integer> adt = new ArrayDeque<>();

        int N = 1000000;
        for (int i = 0; i < N; i++) {
            adt.addLast(i);
        }

        for (int i = 0; i < (N/2); i++) {
            assertEquals("should have the same value", i, (int)adt.removeFirst());
        }

        for (int i = N-1; i > (N/2); i--) {
            assertEquals("should have the same value", i, (int)adt.removeLast());
        }
    }

    @Test
    public void nonEmptyInstantiationTest() {
        ArrayDeque<Integer> adt = new ArrayDeque<>(1);

        assertFalse("should not be empty", adt.isEmpty());
        assertEquals("should have size 1", 1, adt.size());
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> adt = new ArrayDeque<>();

        for (int i = 0; i < 1000; i++) {
            adt.addLast(i);
        }

        for (int i = 0; i < 1000; i++) {
            assertEquals("should have the same value", i, (int)adt.get(i));
        }

        assertNull("should be null when index out of bound", adt.get(1000));
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> adt = new ArrayDeque<>();

        for (int i = 0; i < 1000; i++) {
            adt.addLast(i);
        }

        int i = 0;
        for (int item : adt) {
            assertEquals("should have the same value", i, item);
            i += 1;
        }
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> adt1 = new ArrayDeque<>();
        ArrayDeque<Integer> adt2 = new ArrayDeque<>();

        adt1.addLast(0);
        adt2.addLast(0);
        assertEquals(adt1, adt2);

        adt1.addLast(1);
        assertNotEquals(adt1, adt2);

        adt2.addLast(2);
        assertNotEquals(adt1, adt2);
    }

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> adt = new ArrayDeque<>();

        int N = 1000000;
        for (int i = 0; i < N; i++) {
            int operatorNumber = StdRandom.uniform(0, 6);
            if (operatorNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                adt.addFirst(randVal);
            } else if (operatorNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                adt.addLast(randVal);
            } else if (adt.size() == 0) {
                assertTrue(adt.isEmpty());
            } else if (operatorNumber == 2) {
                assertTrue(adt.size() > 0);
            } else if (operatorNumber == 3) {
                adt.removeFirst();
            } else if (operatorNumber == 4) {
                adt.removeLast();
            } else if (operatorNumber == 5) {
                int randIndex = StdRandom.uniform(0, adt.size());
                adt.get(randIndex);
            }
        }
    }
}
