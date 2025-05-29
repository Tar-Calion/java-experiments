package tar.calion.aoc.year2015.day9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import org.apache.commons.collections4.iterators.PermutationIterator;
import tar.calion.aoc.year2015.day9.Routes;

public class Day9 {

    private final Map<String, Map<String, Integer>> distances;
    private final Set<String> locations;

    public Day9(String routesInput) {
        distances = new HashMap<>();
        locations = new HashSet<>();
        parseRoutesInput(routesInput);
    }

    private void parseRoutesInput(String routesInput) {
        if (routesInput == null || routesInput.trim().isEmpty()) {
            return;
        }

        Pattern pattern = Pattern.compile("(\\w+) to (\\w+) = (\\d+)");
        String[] lines = routesInput.split("\\n");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line.trim());
            if (matcher.matches()) {
                String loc1 = matcher.group(1);
                String loc2 = matcher.group(2);
                int distanceValue = Integer.parseInt(matcher.group(3));

                locations.add(loc1);
                locations.add(loc2);

                // Store distances bidirectionally
                distances.computeIfAbsent(loc1, k -> new HashMap<>()).put(loc2, distanceValue);
                distances.computeIfAbsent(loc2, k -> new HashMap<>()).put(loc1, distanceValue);
            }
        }
    }

    public Routes calculateRoutes() {
        List<String> shortestRouteWaypoints = null;
        long minDistance = Long.MAX_VALUE;
        List<String> longestRouteWaypoints = null;
        long maxDistance = Long.MIN_VALUE;

        // Short-circuit if there are no locations to process.
        if (locations.isEmpty()) {
            return new Routes(new Route(Collections.emptyList(), 0), new Route(Collections.emptyList(), 0));
        }

        // Handle the single location case.
        if (locations.size() == 1) {
            return new Routes(new Route(new ArrayList<>(locations), 0), new Route(new ArrayList<>(locations), 0));
        }
        
        Collection<String> locationCollection = new ArrayList<>(locations);
        PermutationIterator<String> permIter = new PermutationIterator<>(locationCollection);

        while (permIter.hasNext()) {
            List<String> currentPermutation = permIter.next();
            long currentTotalDistance = 0;
            
            try {
                for (int i = 0; i < currentPermutation.size() - 1; i++) {
                    String fromLocation = currentPermutation.get(i);
                    String toLocation = currentPermutation.get(i + 1);
                    currentTotalDistance += distances.get(fromLocation).get(toLocation);
                }

                // This block is reached if all segments in the permutation exist.
                // Permutations will only be for 2 or more locations due to guard clauses.
                if (currentTotalDistance < minDistance) {
                    minDistance = currentTotalDistance;
                    shortestRouteWaypoints = new ArrayList<>(currentPermutation);
                }
                // Add logic for longest route
                if (currentTotalDistance > maxDistance) {
                    maxDistance = currentTotalDistance;
                    longestRouteWaypoints = new ArrayList<>(currentPermutation);
                }
            } catch (NullPointerException e) {
                // This permutation is invalid because a path segment is missing; ignore it.
            }
        }

        if (shortestRouteWaypoints == null) {
            // No valid route covering all locations was found (e.g., disconnected graph for all-city traversal,
            // or no locations provided initially). This also means no longest route.
            return new Routes(new Route(Collections.emptyList(), 0), new Route(Collections.emptyList(), 0));
        }
        
        // If shortestRouteWaypoints is not null, a valid path was found.
        // This implies longestRouteWaypoints will also be non-null if at least one path was processed.
        // If locations.size() >= 2, at least one permutation is processed.
        // If that one permutation is valid, both shortest and longest will be set.
        // If that one permutation is invalid, shortestRouteWaypoints would be null and handled above.
        return new Routes(new Route(shortestRouteWaypoints, minDistance), new Route(longestRouteWaypoints, maxDistance));
    }
}
