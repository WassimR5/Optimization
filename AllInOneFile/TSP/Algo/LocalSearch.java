package TSP.Algo;

import java.util.*;

import TSP.model.City;
import TSP.model.TspSolve;

/**
 * Local search for 2-opt swaps in TSP.
 *
 * <p>Attempts to improve the tour by swapping pairs of cities until no improvement is found.
 * Supports both first-improvement and best-improvement strategies depending on the flag.
 */
public class LocalSearch {

    /**
     * Local search 2-opt for TSP.
     *
     * @param initialSolution initial solution to improve (copied internally)
     * @param bestImprovement true to use best-improvement strategy; false for first-improvement
     * @return improved TspSolve
     */
    public static TspSolve run(
            TspSolve initialSolution,
            boolean bestImprovement
    ) {
        // we start from the initial solution and we try to improve it by swapping two cities
        TspSolve current = initialSolution.copy();

        boolean improved = true;
        // we loop until we cannot improve the solution 
        while (improved) {

            improved = false;

            TspSolve bestNeighbor = current;
            double bestDistance = current.getDistance();
            // we loop through all the pairs of cities and we swap them to get a neighbor solution
            for (int i = 1; i < current.tour.size() - 1; i++) {

                for (int j = i + 1; j < current.tour.size(); j++) {

                    TspSolve neighbor = swap(current, i, j);

                    double neighborDistance = neighbor.getDistance();

                    if (neighborDistance < bestDistance) {

                        if (!bestImprovement) {
                            current = neighbor;
                            improved = true;
                            break;
                        }

                        bestNeighbor = neighbor;
                        bestDistance = neighborDistance;
                        improved = true;
                    }
                }

                if (improved && !bestImprovement) {
                    break;
                }
            }

            if (bestImprovement && improved) {
                current = bestNeighbor;
            }
        }

        return current;
    }

    private static TspSolve swap(
            TspSolve solution,
            int i,
            int j
    ) {

        List<City> newTour = new ArrayList<>(solution.tour);

        Collections.swap(newTour, i, j);

        return new TspSolve(newTour);
    }
}
