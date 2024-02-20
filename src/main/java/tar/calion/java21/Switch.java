package tar.calion.java21;

import java.util.Arrays;

public class Switch {

    private Exception getException() {
        return null;
    }

    public static void main(String[] args) {
        Switch s = new Switch();

        Exception e = s.getException();

        switch (e) {
            case null -> System.out.println("null");
            case NullPointerException npe when npe.getStackTrace().length > 0 -> {
                System.out.println(Arrays.toString(npe.getStackTrace()));
            }
            case NullPointerException npe -> {
                System.out.println(npe.getMessage());
            }
            case IllegalArgumentException iae when iae.getStackTrace().length > 0 ->
                    System.out.println(Arrays.toString(iae.getStackTrace()));
            case Exception ex -> System.out.println(ex.getMessage());
        }

//        switch (e) {
//            case NullPointerException npe -> {
//                System.out.println(npe.getMessage());
//            }
//            case IllegalArgumentException iae -> System.out.println("IAE");
//            case Exception ex -> System.out.println(ex.getMessage());
//        };

        Integer num = 0;
        String output = switch (num) {
            case -1, 1 -> "special case";
            case Integer i when i > 0 -> "positive integer";
            case 0 -> {
                System.out.println("zero");
                yield "zero";
            }
            case Integer i -> "other integer";
        };

        long l = 0;
        String result = switch ((int) l) {
            case 0 -> "zero";
            default -> "other";
        };

        Object o = "string";

        String out = switch (o) {
            case null -> "null";
            case String str -> str;
            case Integer i when i > 0 -> "positive integer " + i;
            case Integer i -> "other integer";
            default -> "unknown";
        };
    }

    static double getDoubleValueUsingGuardedPatterns(Object o) {
        return switch (o) {
            case String s when s.length() > 0 -> Double.parseDouble(s);
            default -> 0d;
        };
    }
}
