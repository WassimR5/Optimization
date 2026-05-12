package knapSack.Algo;



import java.util.ArrayList;
import knapSack.Utils.Helper;
import knapSack.models.Item;
import knapSack.models.KnapSack;

/**
 * Local Search driver for Knapsack: performs iterative neighborhood search using 2-2 exchanges.
 * Mutates the provided KnapSack instance.
 */
public class LocalSearch {
    /**
     * Run local search on knapSack to improve its items.
     * Mutates provided knapSack instance; returns void.
     */
    public static void run(KnapSack knapSack, ArrayList<Item> items, int iterations) {
        run(knapSack, items, iterations, true);
    }

    /**
     * Run local search on knapSack to improve its items.
     * Mutates provided knapSack instance; returns void.
     *
     * @param knapSack knapsack to optimize (mutated)
     * @param items all candidate items
     * @param iterations maximum iterations
     * @param bestImprovement if true use best-improvement strategy, otherwise first-improvement
     */
    public static void run(KnapSack knapSack, ArrayList<Item> items, int iterations, boolean bestImprovement) {
        // Use greedy to build an initial feasible solution stored in knapSack
        GreedyAlgo.run(items, knapSack, true);
        ArrayList<Item> initialSolution = knapSack.getItems();
        if (initialSolution.isEmpty()) return;

        ArrayList<Item> bestSolution = initialSolution;
        // update knapsack with initial solution
        knapSack.updateItemsList(bestSolution);

        boolean improvement = true;
        while (improvement && iterations > 0) {
            improvement = false;
            // all feasible neighboring solutions of bestSolution using 2-2ExchangeNeighborhood
            ArrayList<ArrayList<Item>> neighborSets = Helper.generate2ExchangeNeighborSets(knapSack, items);
            // get the highest total value solution in neighborSets
            ArrayList<Item> s = Helper.getBestFromNeighborhood(neighborSets);
            // if s > bestSolution
            if (Helper.calculateValue(s) > Helper.calculateValue(bestSolution)) {
                bestSolution = s;
                knapSack.updateItemsList(bestSolution);
                improvement = true;
            }
            iterations--;
        }
    }

     

}



