import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CollatzTask implements Runnable {
    private final long startRange;
    private final long endRange;
    private final String mode;
    private static final Map<Long, Integer> cache = new ConcurrentHashMap<>();

    public CollatzTask(long startRange, long endRange, String mode) {
        this.startRange = startRange;
        this.endRange = endRange;
        this.mode = mode;
    }

    @Override
    public void run() {
        if (mode.equals("debug")) {
            System.out.println("Starting task in debug mode for range: " + startRange + " to " + endRange);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        long startTime = System.currentTimeMillis();
        long maxLengthNumber = 0;
        int maxLength = 0;

        for (long i = startRange; i <= endRange; i++) {
            int length = calculateCollatzLength(i);
            if (length > maxLength) {
                maxLength = length;
                maxLengthNumber = i;
            }
        }

        long endTime = System.currentTimeMillis(); // End timing
        long taskDuration = endTime - startTime; // Calculate duration

        if ("debug".equals(mode)) {
            System.out.println("Task for range: " + startRange + " to " + endRange + " finished. Max length: " +
                    maxLength + " for number: " + maxLengthNumber + ". Time taken: " + taskDuration + " ms");
        }
    }

    private int calculateCollatzLength(long n) {
        if (n == 1) return 1;
        if (cache.containsKey(n)) return cache.get(n);

        long next = n % 2 == 0 ? n / 2 : 3 * n + 1;
        int length = 1 + calculateCollatzLength(next);

        cache.put(n, length);
        return length;
    }
}
