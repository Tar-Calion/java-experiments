package tar.calion.aoc.year2015;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    @Test
    void testCalculateShortestRoute_Original() {
        String routesInput = """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
                """;
        Day9 day9 = new Day9(routesInput);
        Route shortestRoute = day9.calculateShortestRoute();

        assertNotNull(shortestRoute, "Shortest route should not be null");
        assertNotNull(shortestRoute.waypoints(), "Waypoints list should not be null");
        assertFalse(shortestRoute.waypoints().isEmpty(), "Waypoints list should not be empty");
        assertEquals(605L, shortestRoute.distance(), "Distance should be 605 for the given input");
        
        List<String> expectedWaypoints1 = Arrays.asList("London", "Dublin", "Belfast");
        List<String> expectedWaypoints2 = Arrays.asList("Belfast", "Dublin", "London");

        assertTrue(expectedWaypoints1.equals(shortestRoute.waypoints()) || expectedWaypoints2.equals(shortestRoute.waypoints()),
                "Waypoints should be either [London, Dublin, Belfast] or [Belfast, Dublin, London]. Actual: " + shortestRoute.waypoints());
    }

    @Test
    void testEmptyInputString() {
        Day9 day9 = new Day9("");
        Route route = day9.calculateShortestRoute();
        assertNotNull(route, "Route should not be null for empty input");
        assertTrue(route.waypoints().isEmpty(), "Waypoints should be empty for empty input");
        assertEquals(0L, route.distance(), "Distance should be 0 for empty input");
    }

    @Test
    void testNullInputString() {
        Day9 day9 = new Day9(null);
        Route route = day9.calculateShortestRoute();
        assertNotNull(route, "Route should not be null for null input");
        assertTrue(route.waypoints().isEmpty(), "Waypoints should be empty for null input");
        assertEquals(0L, route.distance(), "Distance should be 0 for null input");
    }

    @Test
    void testOneLocation() {
        Day9 day9 = new Day9("Alpha to Alpha = 0");
        Route route = day9.calculateShortestRoute();
        assertNotNull(route, "Route should not be null for one location");
        assertEquals(1, route.waypoints().size(), "Should have one waypoint");
        assertEquals("Alpha", route.waypoints().get(0), "Waypoint should be Alpha");
        assertEquals(0L, route.distance(), "Distance should be 0 for one location");
    }

    @Test
    void testTwoLocationsSingleRoute() {
        Day9 day9 = new Day9("Alpha to Beta = 100");
        Route route = day9.calculateShortestRoute();
        assertNotNull(route, "Route should not be null");
        assertEquals(2, route.waypoints().size(), "Should have two waypoints");
        assertEquals(100L, route.distance(), "Distance should be 100");

        List<String> expected1 = Arrays.asList("Alpha", "Beta");
        List<String> expected2 = Arrays.asList("Beta", "Alpha");
        assertTrue(expected1.equals(route.waypoints()) || expected2.equals(route.waypoints()),
                "Waypoints should be [Alpha, Beta] or [Beta, Alpha]. Actual: " + route.waypoints());
    }

    @Test
    void testDisconnectedLocations() {
        String routesInput = """
                Alpha to Beta = 100
                Gamma to Delta = 200
                """;
        Day9 day9 = new Day9(routesInput);
        Route route = day9.calculateShortestRoute();
        assertNotNull(route, "Route should not be null");
        assertTrue(route.waypoints().isEmpty(), "Waypoints should be empty for disconnected graph not allowing full traversal");
        assertEquals(0L, route.distance(), "Distance should be 0 for no valid full route");
    }

    @Test
    void testThreeLocations_OnePairDisconnected() {
        String routesInput = """
                London to Dublin = 100
                London to Paris = 200
                """;
        Day9 day9 = new Day9(routesInput);
        Route shortestRoute = day9.calculateShortestRoute();
        
        assertNotNull(shortestRoute, "Shortest route should not be null for this connected case.");
        assertEquals(300L, shortestRoute.distance(), "Distance should be 300 for Dublin-London-Paris.");
        assertFalse(shortestRoute.waypoints().isEmpty(), "Waypoints should not be empty.");
        assertEquals(3, shortestRoute.waypoints().size(), "All 3 unique locations must be in the path.");

        List<String> expectedWaypoints1 = Arrays.asList("Dublin", "London", "Paris");
        List<String> expectedWaypoints2 = Arrays.asList("Paris", "London", "Dublin");

        assertTrue(expectedWaypoints1.equals(shortestRoute.waypoints()) || expectedWaypoints2.equals(shortestRoute.waypoints()),
                "Waypoints should be one of the expected paths. Actual: " + shortestRoute.waypoints());
    }

    @Test
    void testCalculateShortestRoute_ComplexGraph_Provided() {
        String routesInput = """
                Tristram to AlphaCentauri = 34
                Tristram to Snowdin = 100
                Tristram to Tambi = 63
                Tristram to Faerun = 108
                Tristram to Norrath = 111
                Tristram to Straylight = 89
                Tristram to Arbre = 132
                AlphaCentauri to Snowdin = 4
                AlphaCentauri to Tambi = 79
                AlphaCentauri to Faerun = 44
                AlphaCentauri to Norrath = 147
                AlphaCentauri to Straylight = 133
                AlphaCentauri to Arbre = 74
                Snowdin to Tambi = 105
                Snowdin to Faerun = 95
                Snowdin to Norrath = 48
                Snowdin to Straylight = 88
                Snowdin to Arbre = 7
                Tambi to Faerun = 68
                Tambi to Norrath = 134
                Tambi to Straylight = 107
                Tambi to Arbre = 40
                Faerun to Norrath = 11
                Faerun to Straylight = 66
                Faerun to Arbre = 144
                Norrath to Straylight = 115
                Norrath to Arbre = 135
                Straylight to Arbre = 127
                """;
        Day9 day9 = new Day9(routesInput);
        Route shortestRoute = day9.calculateShortestRoute();

        assertNotNull(shortestRoute, "Shortest route should not be null for complex graph");
        assertEquals(251L, shortestRoute.distance(), "Distance for complex graph");

        List<String> expectedWaypoints = Arrays.asList(
            "Norrath", "Faerun", "Straylight", "Tristram", 
            "AlphaCentauri", "Snowdin", "Arbre", "Tambi"
        );
        // If the implementation is deterministic, this direct check is fine.
        // If multiple paths could give the same shortest distance with different orders,
        // a more complex check (e.g., checking against a set of possible waypoint lists) would be needed.
        // For now, we assume the identified shortest path order is what the current code produces.
        assertEquals(expectedWaypoints, shortestRoute.waypoints(), "Waypoints for complex graph. Actual: " + shortestRoute.waypoints());
    }
}
