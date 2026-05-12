package TSP.Algo;

import java.util.*;

import TSP.model.City;
import TSP.model.TspSolve;



/**
 * Simulated annealing solver for TSP.
 *
 * <p>This implementation repeatedly proposes random 2-swap neighbors and accepts
 * uphill moves with probability exp(-delta/temperature). Temperature is cooled
 * multiplicatively by the provided cooling rate.
 *
 * <p>All numerical behavior (delta computation and acceptance rule) is preserved.
 */
public class SimulatedAnnealing {

    /**
     * Run simulated annealing starting from the given initial solution.
     *
     * @param initialSolution starting tour (copied internally)
     * @param temperature initial temperature
     * @param coolingRate multiplicative cooling factor (0 < coolingRate < 1)
     * @param iterations number of iterations
     * @return best tour found during the run
     */
    public static TspSolve run(
            TspSolve initialSolution,
            double temperature,
            double coolingRate,
            int iterations
    ) {
        // we start from the initial solution and we try to improve it by swapping two cities
        Random random = new Random();

        TspSolve current = initialSolution.copy();
        TspSolve best = current.copy();
        // we loop for a given number of iterations and 
        // we try to find a better solution by swapping two cities
        for (int iter = 0; iter < iterations; iter++) {

            int i = random.nextInt(current.tour.size());
            int j = random.nextInt(current.tour.size());

            TspSolve neighbor = swap(current, i, j);

            double currentDistance = current.getDistance();
            double neighborDistance = neighbor.getDistance();

            double delta = neighborDistance - currentDistance;

            if (delta < 0 ||
                    Math.random() < Math.exp(-delta / temperature)) {

                current = neighbor;
            }

            if (current.getDistance() < best.getDistance()) {
                best = current.copy();
            }

            temperature *= coolingRate;

            if (temperature < 0.0001) {
                break;
            }
        }

        return best;
    }

    private static TspSolve swap(
            TspSolve solution,
            int i,
            int j
    ) {
        List<City> newTour = new ArrayList<>(solution.tour);
        
        // we create a new tour by swapping the cities at positions i and j
        Collections.swap(newTour, i, j);

        return new TspSolve(newTour);
    }
}
