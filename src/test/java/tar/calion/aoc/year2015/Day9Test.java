package tar.calion.aoc.year2015;

import org.junit.jupiter.api.Test;
import tar.calion.aoc.year2015.Route;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    @Test
    void solve() {
        // TODO: Add test cases
    }

    @Test
    void testCalculateShortestRoute() {
        String routesInput = """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
                """;
        Day9 day9 = new Day9(routesInput);
        Route shortestRoute = day9.calculateShortestRoute();

        // At this stage, the calculateShortestRoute method returns null.
        // These assertions will fail (likely with a NullPointerException)
        // until the method is implemented correctly.
        List<String> expectedWaypoints = Arrays.asList("London", "Dublin", "Belfast");
        assertEquals(expectedWaypoints, shortestRoute.waypoints());
        assertEquals(605L, shortestRoute.distance());
    }
}
