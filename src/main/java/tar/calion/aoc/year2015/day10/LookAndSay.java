package tar.calion.aoc.year2015.day10;

public class LookAndSay {

    public static String lookAndSay(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                count++;
            } else {
                result.append(count);
                result.append(s.charAt(i));
                count = 1;
            }
        }
        return result.toString();
    }
}
