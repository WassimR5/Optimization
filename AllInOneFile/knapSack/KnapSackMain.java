package knapSack;



import java.util.ArrayList;
import knapSack.Algo.GreedyAlgo;
import knapSack.Algo.LocalSearch;
import knapSack.Algo.SimulatedAnnealing;
import knapSack.Utils.ItemProvider;
import knapSack.Utils.KnapSackProvider;
import knapSack.models.Item;
import knapSack.models.KnapSack;


/**
 * Main runner for Knapsack experiments.
 *
 * <p>Generates a reproducible set of items and runs Greedy, Local Search, and Simulated Annealing
 * to demonstrate and compare their results. Algorithmic behavior is unchanged.
 */
public class KnapSackMain {
    /**
     * Entry point for knapsack experiment runner.
     * Generates items, runs algorithms (Greedy, Local Search, Simulated Annealing) and prints results.
     * Algorithms/physics unchanged.
     */
    public static void main(String[] args) {
      // Number of items to generate for the knapsack problem
      int numberOfItems = 700;
      // Maximum possible weight for an item
      double maxWeight = 100.0;
      // Maximum possible value for an item
      double maxValue = 150.0;

      // Generate random items for the knapsack
      ArrayList<Item> items = ItemProvider.createItem(maxWeight, maxValue, numberOfItems);
    // create Knapsack with Capacity  = 5*n/4 : n = number of items
      // Create a knapsack with capacity = 5 * n / 4
      KnapSack knapSack = KnapSackProvider.createKnapSack((5 * numberOfItems) / 4.0);
      // Run the Greedy algorithm (false = not randomized)
      GreedyAlgo.run(items, knapSack, false);
      // Output results for Greedy solution
      printKnapSackResult("Greedy Solution", knapSack);


       KnapSack knapSackLocal = KnapSackProvider.createKnapSack((5 * numberOfItems) / 4.0);
       LocalSearch.run(knapSackLocal, items, 30);
       printKnapSackResult("Local Search Solution", knapSackLocal);

        KnapSack knapSackAnneal = KnapSackProvider.createKnapSack((5 * numberOfItems) / 4.0);
        KnapSack annealResult = SimulatedAnnealing.solve(items, knapSackAnneal, 10000, 0.995, 100000);
        printKnapSackResult("Simulated Annealing Solution", annealResult);
    }

    /**
     * Print a summary of the given knapsack solution.
     * Does not mutate the knapsack.
     */
    private static void printKnapSackResult(String title, KnapSack sack) {
        System.out.println("\n" + title + ":");
        System.out.println("============");
        System.out.println("Knapsack Capacity: " + sack.getCapacity());
        System.out.printf("Total weight in knapsack: %.3f\n", sack.getTotalWeight());
        System.out.println("Number of items: " + sack.getNumberOfItems());
        System.out.printf("Total value in knapsack: %.3f\n", sack.getTotalValue());
        System.out.println("============");
    }
}

