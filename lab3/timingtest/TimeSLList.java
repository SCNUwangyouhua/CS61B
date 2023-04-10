package timingtest;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import org.checkerframework.checker.units.qual.A;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        //System.out.println(11451);
        SLList<Integer> testSLList = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for (int i = 1000; i <= 128000; i *= 2){
            //System.out.println("no at i = " + i);
            Ns.addLast(i);
            int opCount = 0;
            for (int j = 0; j < i; ++j){
                testSLList.addLast(j);
            }
            double startTime = System.currentTimeMillis();
            for (int k = 1; k <= 10000; ++k){
                testSLList.getLast();
                opCount += 1;
            }
            double endTime = System.currentTimeMillis();
            double time = (endTime - startTime) / 1000;
            opCounts.addLast(opCount);
            times.addLast(time);
        }
        //System.out.println(114514);
        printTimingTable(Ns, times, opCounts);
    }

}
