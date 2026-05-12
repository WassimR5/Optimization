package TSP.model;

import java.util.*;

/**
 * Represents a TSP solution (ordered tour of cities).
 *
 * <p>Provides utilities to compute the total tour distance and to copy the tour.
 * The distance uses Euclidean metric and its calculation must not be modified.
 */
public class TspSolve {

    public List<City> tour;

    public TspSolve(List<City> tour) {
        this.tour = new ArrayList<>(tour);
    }

    /**
     * Compute total length of the tour (sum of Euclidean distances between consecutive cities,
     * including the closing edge from last back to first).
     *
     * @return total tour distance
     */
    public double getDistance() {
        double total = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            total += tour.get(i).distanceTo(tour.get(i + 1));
        }
        total += tour.get(tour.size() - 1).distanceTo(tour.get(0));
        return total;
    }

    public TspSolve copy() {
        return new TspSolve(new ArrayList<>(tour));
    }
}