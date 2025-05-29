package tar.calion.aoc.year2015.day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import tar.calion.aoc.year2015.day10.LookAndSay;

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
}
