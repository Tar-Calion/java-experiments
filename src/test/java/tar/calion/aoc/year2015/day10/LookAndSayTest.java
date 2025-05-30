package tar.calion.aoc.year2015.day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import tar.calion.aoc.year2015.day10.LookAndSay;
import org.junit.jupiter.api.Test;

public class LookAndSayTest {

    @ParameterizedTest
    @CsvSource({
            "'', ''",
            "'1', '11'",
            "'11', '21'",
            "'2', '12'",
            "'22', '22'",
            "'3', '13'",
            "'33', '23'",
            "'111', '31'",
            "'222', '32'",
            "'333', '33'",
            "'12', '1112'",
            "'123', '111213'",
            "'1122', '2122'",
            "'112233', '212223'",
            "'3113322113', '132123222113'"
    })
    void testLookAndSay(String input, String expected) {
        assertEquals(expected, LookAndSay.lookAndSay(input));
    }

    @Test
    void testLookAndSayFortyTimes() {
        String sequence = "3113322113";
        for (int i = 0; i < 40; i++) {
            sequence = LookAndSay.lookAndSay(sequence);
        }
        assertEquals(329356, sequence.length());
    }

    @Test
    void testLookAndSayFiftyTimes() {
        String sequence = "3113322113";
        for (int i = 0; i < 50; i++) {
            sequence = LookAndSay.lookAndSay(sequence);
        }
        assertEquals(4666278, sequence.length());
    }
}
