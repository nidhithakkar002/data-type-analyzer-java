import java.util.Scanner;

public class DataTypeAnalyzer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a value: ");
        String input = sc.next();

        String type = detectType(input);
        System.out.println("\nDetected Type: " + type);

        analyze(input, type);
        
        sc.close();
    }

    // ---------------- TYPE DETECTION ----------------
    public static String detectType(String input) {

        // Check for decimal
        if (input.contains(".")) {
            try {
                Double.parseDouble(input);
                return "double";
            } catch (NumberFormatException e) {
                return "Unknown";
            }
        }

        // Try integer
        try {
            Integer.parseInt(input);
            return "int";
        } catch (NumberFormatException e1) {

            // Try long
            try {
                Long.parseLong(input);
                return "long";
            } catch (NumberFormatException e2) {
                return "Unknown";
            }
        }
    }

    // ---------------- MAIN ANALYSIS ----------------
    public static void analyze(String input, String type) {

        System.out.println("\n--- Conversions ---");

        switch (type) {

            case "int":
                int intVal = Integer.parseInt(input);

                convertFromInt(intVal);
                showBinary(intVal);
                break;

            case "long":
                long longVal = Long.parseLong(input);

                convertFromLong(longVal);
                break;

            case "double":
                double doubleVal = Double.parseDouble(input);

                convertFromDouble(doubleVal);
                break;

            default:
                System.out.println("Unsupported or invalid input.");
        }
    }

    // ---------------- INT CONVERSIONS ----------------
    public static void convertFromInt(int value) {

        byte b = (byte) value;
        short s = (short) value;
        long l = (long) value;

        System.out.println("byte: " + b + checkOverflow(value, b));
        System.out.println("short: " + s + checkOverflow(value, s));
        System.out.println("long: " + l);
    }

    // ---------------- LONG CONVERSIONS ----------------
    public static void convertFromLong(long value) {

        int i = (int) value;
        byte b = (byte) value;

        System.out.println("int: " + i + checkOverflow(value, i));
        System.out.println("byte: " + b + checkOverflow(value, b));
    }

    // ---------------- DOUBLE CONVERSIONS ----------------
    public static void convertFromDouble(double value) {

        int i = (int) value;
        long l = (long) value;

        System.out.println("int: " + i + " (fraction lost)");
        System.out.println("long: " + l + " (fraction lost)");
    }

    // ---------------- OVERFLOW CHECK ----------------
    public static String checkOverflow(long original, long converted) {

        if (original != converted) {
            return " (overflow occurred)";
        }
        return "";
    }

    // ---------------- BINARY VISUALIZER ----------------
    public static void showBinary(int value) {

        String binary = String.format("%32s", Integer.toBinaryString(value))
                .replace(' ', '0');

        System.out.println("\nBinary (int): " + binary);
    }
}