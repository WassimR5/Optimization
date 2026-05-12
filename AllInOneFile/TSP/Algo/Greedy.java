package TSP.Algo;

import java.util.*;

import TSP.model.City;
import TSP.model.TspSolve;

/**
 * Greedy nearest-neighbor heuristic for TSP. Starts from the first city
 * and repeatedly visits the nearest unvisited city.
 */
public class Greedy {

    /**
     * Run nearest-neighbor greedy heuristic starting from the first city.
     *
     * @param cities list of cities in arbitrary order
     * @return constructed tour (TspSolve)
     */
    public static TspSolve run(List<City> cities) {
        // Start from the first city and repeatedly visit the nearest unvisited city
        List<City> unvisited = new ArrayList<>(cities);
        List<City> tour = new ArrayList<>();

        City current = unvisited.remove(0);
        tour.add(current);

        while (!unvisited.isEmpty()) {

            City nearest = null;
            double minDistance = Double.MAX_VALUE;

            for (City city : unvisited) {

                double distance = current.distanceTo(city);

                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = city;
                }
            }

            tour.add(nearest);
            unvisited.remove(nearest);

            current = nearest;
        }

        return new TspSolve(tour);
    }
}
