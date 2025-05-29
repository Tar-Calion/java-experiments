package tar.calion.aoc.year2015.day9;

import tar.calion.aoc.year2015.day9.Day9;
import tar.calion.aoc.year2015.day9.Route;
import tar.calion.aoc.year2015.day9.Routes;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    @Test
    void testCalculateRoutes_AdventOfCodeExample() {
        String routesInput = """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
                """;
        Day9 day9 = new Day9(routesInput);
        Routes result = day9.calculateRoutes();
        Route shortestRoute = result.shortestRoute();
        Route longestRoute = result.longestRoute();

        assertNotNull(shortestRoute, "Shortest route should not be null");
        assertNotNull(shortestRoute.waypoints(), "Shortest route waypoints list should not be null");
        assertFalse(shortestRoute.waypoints().isEmpty(), "Shortest route waypoints list should not be empty");
        assertEquals(605L, shortestRoute.distance(), "Shortest distance should be 605 for the given input");
        
        List<String> expectedShortestWaypoints1 = Arrays.asList("London", "Dublin", "Belfast");
        List<String> expectedShortestWaypoints2 = Arrays.asList("Belfast", "Dublin", "London");
        assertTrue(expectedShortestWaypoints1.equals(shortestRoute.waypoints()) || expectedShortestWaypoints2.equals(shortestRoute.waypoints()),
                "Shortest waypoints should be either [London, Dublin, Belfast] or [Belfast, Dublin, London]. Actual: " + shortestRoute.waypoints());

        assertNotNull(longestRoute, "Longest route should not be null");
        assertNotNull(longestRoute.waypoints(), "Longest route waypoints list should not be null");
        assertFalse(longestRoute.waypoints().isEmpty(), "Longest route waypoints list should not be empty");
        assertEquals(982L, longestRoute.distance(), "Longest distance should be 982 for the given input");

        List<String> expectedLongestWaypoints1 = Arrays.asList("Dublin", "London", "Belfast");
        List<String> expectedLongestWaypoints2 = Arrays.asList("Belfast", "London", "Dublin");
        assertTrue(expectedLongestWaypoints1.equals(longestRoute.waypoints()) || expectedLongestWaypoints2.equals(longestRoute.waypoints()),
                "Longest waypoints should be either [Dublin, London, Belfast] or [Belfast, London, Dublin]. Actual: " + longestRoute.waypoints());
    }

    @Test
    void testEmptyInputString() {
        Day9 day9 = new Day9("");
        Routes result = day9.calculateRoutes();
        Route shortestRoute = result.shortestRoute();
        Route longestRoute = result.longestRoute();

        assertNotNull(shortestRoute, "Shortest route should not be null for empty input");
        assertTrue(shortestRoute.waypoints().isEmpty(), "Shortest route waypoints should be empty for empty input");
        assertEquals(0L, shortestRoute.distance(), "Shortest distance should be 0 for empty input");

        assertNotNull(longestRoute, "Longest route should not be null for empty input");
        assertTrue(longestRoute.waypoints().isEmpty(), "Longest route waypoints should be empty for empty input");
        assertEquals(0L, longestRoute.distance(), "Longest distance should be 0 for empty input");
    }

    @Test
    void testNullInputString() {
        Day9 day9 = new Day9(null);
        Routes result = day9.calculateRoutes();
        Route shortestRoute = result.shortestRoute();
        Route longestRoute = result.longestRoute();

        assertNotNull(shortestRoute, "Shortest route should not be null for null input");
        assertTrue(shortestRoute.waypoints().isEmpty(), "Shortest route waypoints should be empty for null input");
        assertEquals(0L, shortestRoute.distance(), "Shortest distance should be 0 for null input");

        assertNotNull(longestRoute, "Longest route should not be null for null input");
        assertTrue(longestRoute.waypoints().isEmpty(), "Longest route waypoints should be empty for null input");
        assertEquals(0L, longestRoute.distance(), "Longest distance should be 0 for null input");
    }

    @Test
    void testOneLocation() {
        Day9 day9 = new Day9("Alpha to Alpha = 0"); // Parser adds "Alpha", calculateRoutes handles single location
        Routes result = day9.calculateRoutes();
        Route shortestRoute = result.shortestRoute();
        Route longestRoute = result.longestRoute();

        assertNotNull(shortestRoute, "Shortest route should not be null for one location");
        assertEquals(1, shortestRoute.waypoints().size(), "Shortest route should have one waypoint");
        assertEquals("Alpha", shortestRoute.waypoints().get(0), "Shortest route waypoint should be Alpha");
        assertEquals(0L, shortestRoute.distance(), "Shortest distance should be 0 for one location");

        assertNotNull(longestRoute, "Longest route should not be null for one location");
        assertEquals(1, longestRoute.waypoints().size(), "Longest route should have one waypoint");
        assertEquals("Alpha", longestRoute.waypoints().get(0), "Longest route waypoint should be Alpha");
        assertEquals(0L, longestRoute.distance(), "Longest distance should be 0 for one location");
    }

    @Test
    void testTwoLocationsSingleRoute() {
        Day9 day9 = new Day9("Alpha to Beta = 100");
        Routes result = day9.calculateRoutes();
        Route shortestRoute = result.shortestRoute();
        Route longestRoute = result.longestRoute();

        assertNotNull(shortestRoute, "Shortest route should not be null");
        assertEquals(2, shortestRoute.waypoints().size(), "Shortest route should have two waypoints");
        assertEquals(100L, shortestRoute.distance(), "Shortest distance should be 100");
        List<String> expectedWaypoints = Arrays.asList("Alpha", "Beta");
        List<String> expectedWaypointsReverse = Arrays.asList("Beta", "Alpha");
        assertTrue(expectedWaypoints.equals(shortestRoute.waypoints()) || expectedWaypointsReverse.equals(shortestRoute.waypoints()),
                "Shortest waypoints should be [Alpha, Beta] or [Beta, Alpha]. Actual: " + shortestRoute.waypoints());

        assertNotNull(longestRoute, "Longest route should not be null");
        assertEquals(2, longestRoute.waypoints().size(), "Longest route should have two waypoints");
        assertEquals(100L, longestRoute.distance(), "Longest distance should be 100");
        assertTrue(expectedWaypoints.equals(longestRoute.waypoints()) || expectedWaypointsReverse.equals(longestRoute.waypoints()),
                "Longest waypoints should be [Alpha, Beta] or [Beta, Alpha]. Actual: " + longestRoute.waypoints());
    }

    @Test
    void testDisconnectedLocations() {
        String routesInput = """
                Alpha to Beta = 100
                Gamma to Delta = 200
                """;
        Day9 day9 = new Day9(routesInput);
        Routes result = day9.calculateRoutes();
        Route shortestRoute = result.shortestRoute();
        Route longestRoute = result.longestRoute();

        assertNotNull(shortestRoute, "Shortest route should not be null");
        assertTrue(shortestRoute.waypoints().isEmpty(), "Shortest route waypoints should be empty for disconnected graph");
        assertEquals(0L, shortestRoute.distance(), "Shortest distance should be 0 for no valid full route");

        assertNotNull(longestRoute, "Longest route should not be null");
        assertTrue(longestRoute.waypoints().isEmpty(), "Longest route waypoints should be empty for disconnected graph");
        assertEquals(0L, longestRoute.distance(), "Longest distance should be 0 for no valid full route");
    }

    @Test
    void testThreeLocations_OnePairDisconnected() {
        // This test implies that all locations given must be part of the path.
        // Dublin - London - Paris. Distance: D-L (100) + L-P (200) = 300.
        // This is the only possible path visiting all 3. So shortest = longest.
        String routesInput = """
                London to Dublin = 100
                London to Paris = 200
                """; // Implicitly, Dublin to Paris is not defined directly.
        Day9 day9 = new Day9(routesInput);
        Routes result = day9.calculateRoutes();
        Route shortestRoute = result.shortestRoute();
        Route longestRoute = result.longestRoute();
        
        assertNotNull(shortestRoute, "Shortest route should not be null for this connected case.");
        assertEquals(300L, shortestRoute.distance(), "Shortest distance should be 300 for Dublin-London-Paris.");
        assertFalse(shortestRoute.waypoints().isEmpty(), "Shortest waypoints should not be empty.");
        assertEquals(3, shortestRoute.waypoints().size(), "Shortest: All 3 unique locations must be in the path.");
        List<String> expectedWaypoints1 = Arrays.asList("Dublin", "London", "Paris");
        List<String> expectedWaypoints2 = Arrays.asList("Paris", "London", "Dublin");
        assertTrue(expectedWaypoints1.equals(shortestRoute.waypoints()) || expectedWaypoints2.equals(shortestRoute.waypoints()),
                "Shortest waypoints should be one of the expected paths. Actual: " + shortestRoute.waypoints());

        assertNotNull(longestRoute, "Longest route should not be null for this connected case.");
        assertEquals(300L, longestRoute.distance(), "Longest distance should be 300 for Dublin-London-Paris.");
        assertFalse(longestRoute.waypoints().isEmpty(), "Longest waypoints should not be empty.");
        assertEquals(3, longestRoute.waypoints().size(), "Longest: All 3 unique locations must be in the path.");
        assertTrue(expectedWaypoints1.equals(longestRoute.waypoints()) || expectedWaypoints2.equals(longestRoute.waypoints()),
                "Longest waypoints should be one of the expected paths. Actual: " + longestRoute.waypoints());
    }

    @Test
    void testCalculateRoutes_ComplexGraph_Provided() {
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
        Routes result = day9.calculateRoutes();
        Route shortestRoute = result.shortestRoute();
        Route longestRoute = result.longestRoute();

        assertNotNull(shortestRoute, "Shortest route should not be null for complex graph");
        assertEquals(251L, shortestRoute.distance(), "Shortest distance for complex graph");

        List<String> expectedShortestWaypoints = Arrays.asList(
            "Norrath", "Faerun", "Straylight", "Tristram", 
            "AlphaCentauri", "Snowdin", "Arbre", "Tambi"
        );
        // Assuming the implementation is deterministic for the shortest path.
        assertEquals(expectedShortestWaypoints, shortestRoute.waypoints(), "Shortest waypoints for complex graph. Actual: " + shortestRoute.waypoints());
        
        assertNotNull(longestRoute, "Longest route should not be null for complex graph");
        assertNotNull(longestRoute.waypoints(), "Longest route waypoints should not be null");
        assertFalse(longestRoute.waypoints().isEmpty(), "Longest route waypoints should not be empty");
        assertEquals(8, longestRoute.waypoints().size(), "Longest route should visit all locations");
        // The value 898 is from https://github.com/mariolutc/advent-of-code-2015/blob/master/day9/day9.org
        assertEquals(898L, longestRoute.distance(), "Longest distance for complex graph. Expected based on external calculation.");
        // We won't assert the exact longest path waypoints due to potential multiple paths with the same max distance
        // and the complexity of listing them all or ensuring deterministic output for this specific path.
        // However, we ensure it's a valid path covering all cities.
        assertEquals(shortestRoute.waypoints().size(), longestRoute.waypoints().size(), "Longest route should visit the same number of locations as shortest.");
        assertTrue(longestRoute.distance() >= shortestRoute.distance(), "Longest distance should be greater than or equal to shortest distance.");
    }
}
