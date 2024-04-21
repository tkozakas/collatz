public class Main {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java CollatzCalculator <startRange> <endRange> <mode> <threadsCount>");
            System.out.println("<mode> should be 'debug' or 'fast'");
            return;
        }

        long startRange = Long.parseLong(args[0]);
        long endRange = Long.parseLong(args[1]);
        String mode = args[2];
        int threadsCount = Integer.parseInt(args[3]);

        CollatzCalculator calculator = new CollatzCalculator(startRange, endRange, mode, threadsCount);
        calculator.run();
    }
}
