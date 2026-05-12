package TSP;

import java.util.*;
import TSP.model.City;
import TSP.model.TspSolve;
import TSP.Algo.Greedy;
import TSP.Algo.LocalSearch;
import TSP.Algo.SimulatedAnnealing;
import TSP.readtspfile.ReadFile;

/**
 * TSP experiment runner.
 *
 * <p>This class provides a simple command-line entry point for running and comparing
 * three TSP solution approaches: nearest-neighbor Greedy, Local Search (2-opt-like swaps),
 * and Simulated Annealing. It is intended for demonstration and benchmarking.
 *
 * <p>Details:
 * - Reads a TSPLIB .tsp file by delegating to ReadFile.read.
 * - Executes Greedy.run, LocalSearch.run, and SimulatedAnnealing.run.
 * - Prints the resulting tour distances for comparison.
 *
 * <p>Important: This class does not change algorithm logic; Javadoc/comments only were updated.
 */
public class TspMain {

    /**
     * Main entry point.
     *
     * @param args command-line arguments (ignored)
     * @throws Exception if file reading fails
     */
    public static void main(String[] args) throws Exception {
        final String path = "berlin52.tsp";
        final int saInitialTemp = 10000;
        final double saCoolingRate = 0.995;
        final int saMaxIterations = 100000;

        List<City> cities = ReadFile.read(path);
        System.out.println("Cities: " + cities.size());

        System.out.println("\n=== GREEDY ===");
        TspSolve greedy = Greedy.run(cities);
        System.out.println("Distance = " + greedy.getDistance());

        System.out.println("\n=== LOCAL SEARCH ===");
        TspSolve local = LocalSearch.run(greedy, true);
        System.out.println("Distance = " + local.getDistance());

        System.out.println("\n=== SIMULATED ANNEALING ===");
        TspSolve sa = SimulatedAnnealing.run(greedy, saInitialTemp, saCoolingRate, saMaxIterations);
        System.out.println("Distance = " + sa.getDistance());
    }
}
