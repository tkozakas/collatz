import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CollatzCalculator implements Runnable {
    private final long startRange;
    private final long endRange;
    private final String mode;
    private final int threadsCount;

    public CollatzCalculator(long startRange, long endRange, String mode, int threadsCount) {
        this.startRange = startRange;
        this.endRange = endRange;
        this.mode = mode;
        this.threadsCount = threadsCount;
    }

    @Override
    public void run() {
        System.out.println("Operating in " + mode.toUpperCase() + " mode.");
        System.out.println("Testing with " + threadsCount + " threads.");

        long startTime = System.nanoTime();

        runCalculations(startRange, endRange, threadsCount, mode);

        long endTime = System.nanoTime();
        long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Calculation with " + threadsCount + " threads took: " + duration + " ms\n");
    }

    public static void runCalculations(long startRange, long endRange, int threadsCount, String mode) {
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        long intervalLength = (endRange - startRange + 1) / threadsCount;
        long currentStart = startRange;

        for (int i = 0; i < threadsCount; i++) {
            long currentEnd = (i == threadsCount - 1) ? endRange : currentStart + intervalLength - 1;
            executor.execute(new CollatzTask(currentStart, currentEnd, mode));
            currentStart = currentEnd + 1;
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("Execution was interrupted.");
        }
    }
}
