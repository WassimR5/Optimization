package knapSack.Utils;



import java.util.ArrayList;
import java.util.List;

import knapSack.models.Item;
import knapSack.models.KnapSack;

/**
 * Helper utilities for knapsack algorithms.
 *
 * <p>Provides seed solution generation, neighbor generation for 1-exchange and 2-exchange,
 * and small helpers to evaluate candidate item lists.
 *
 * <p>Note: Neighbor generation semantics are used by local search and simulated annealing
 * and must not be altered lightly.
 */
public class Helper {
    /**
     * Build an initial feasible solution by iterating candidate items and adding them
     * while capacity remains. This is a simple greedy filler (preserves input ordering).
     *
     * @param knapSack target knapsack (capacity used)
     * @param items candidate items
     * @return list of items forming a feasible solution
     */
    public static ArrayList<Item> initialFeasibleSolution(KnapSack knapSack, ArrayList<Item> items) {
        ArrayList<Item> feasibleSolution = new ArrayList<>();
        double currentWeight = 0;
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (currentWeight + item.getWeight() <= knapSack.getCapacity()) {
                feasibleSolution.add(item);
                currentWeight += item.getWeight();
            }
        }
        return feasibleSolution;
    }


/**
     * Generate all feasible neighbor solutions by removing two items that are currently
     * in the knapsack and adding two distinct items currently outside the knapsack.
     *
     * <p>Each neighbor is a new ArrayList representing the post-exchange item set.
     * The method checks capacity feasibility and returns only valid neighbors.
     *
     * @param currentSolution current knapsack (provides items, totalWeight, capacity)
     * @param allItems all available items (for outside candidates)
     * @return list of feasible neighbor item lists (may be empty)
     */
    public static ArrayList<ArrayList<Item>> generate2ExchangeNeighborSets(KnapSack currentSolution, List<Item> allItems) {
    ArrayList<ArrayList<Item>> neighborSets = new ArrayList<>();
    
    ArrayList<Item> inKnapsack = currentSolution.getItems();
    ArrayList<Item> outKnapsack = new ArrayList<>(allItems);
    outKnapsack.removeAll(inKnapsack);

    double currentWeight = currentSolution.getTotalWeight();
    double capacity = currentSolution.getCapacity();


    // we should have at least 2 items inside and 2 items outside to swap
    if (inKnapsack.size() < 2 || outKnapsack.size() < 2) {
        return neighborSets; 
    }

    // Iterate through pairs to remove (i and j)
    for (int i = 0; i < inKnapsack.size(); i++) {
        for (int j = i + 1; j < inKnapsack.size(); j++) {
            Item r1 = inKnapsack.get(i);
            Item r2 = inKnapsack.get(j);

            // Iterate through pairs to add (k and l)
            for (int k = 0; k < outKnapsack.size(); k++) {
                for (int l = k + 1; l < outKnapsack.size(); l++) {
                    Item a1 = outKnapsack.get(k);
                    Item a2 = outKnapsack.get(l);

                    // Calculate weight delta
                    double weightAfter = currentWeight - r1.getWeight() - r2.getWeight() 
                                         + a1.getWeight() + a2.getWeight();

                    if (weightAfter <= capacity) {
                        ArrayList<Item> neighbor = new ArrayList<>(inKnapsack);
                        neighbor.remove(r1);
                        neighbor.remove(r2);
                        neighbor.add(a1);
                        neighbor.add(a2);
                        neighborSets.add(neighbor);
                    }
                }
            }
        }
    }

    return neighborSets;
}


/**
     * Select the neighbor set with maximum total value. Returns an empty list if no neighbors.
     *
     * @param neighborSets candidate neighbor item lists
     * @return neighbor with highest total value or empty list
     */
public static ArrayList<Item> getBestFromNeighborhood(ArrayList<ArrayList<Item>> neighborSets) {
    // 1. Guard clause for empty input
    if (neighborSets == null || neighborSets.isEmpty()) {
        return new ArrayList<Item>(); // Return empty list rather than null to avoid crashes
    }

    ArrayList<Item> bestSet = neighborSets.get(0);
    double maxValue = calculateValue(bestSet);

    // 2. Compare every candidate solution in the list
    for (int i = 1; i < neighborSets.size(); i++) {
        ArrayList<Item> currentSet = neighborSets.get(i);
        double currentTotalValue = calculateValue(currentSet);

        // 3. Keep the one with the highest value
        if (currentTotalValue > maxValue) {
            maxValue = currentTotalValue;
            bestSet = currentSet;
        }
    }

    return bestSet;
}

/**
 * Compute total value of a candidate item list.
 *
 * @param items candidate items
 * @return sum of item values
 */
public static double calculateValue(ArrayList<Item> items) {
    double total = 0;
    for (Item item : items) {
        total += item.getValue();
    }
    return total;
}

/**
     * Generate neighbors by replacing one item inside the knapsack with one outside.
     *
     * @param currentSolution current knapsack
     * @param allItems all available items
     * @return list of feasible neighbors via single exchanges
     */
    public static ArrayList<ArrayList<Item>> generateOneExchangeNeighborSets(
        KnapSack currentSolution, List<Item> allItems) {

    ArrayList<ArrayList<Item>> neighborSets = new ArrayList<>();

    ArrayList<Item> inKnapsack  = currentSolution.getItems();
    ArrayList<Item> outKnapsack = new ArrayList<>(allItems);
    outKnapsack.removeAll(inKnapsack);

    double currentWeight = currentSolution.getTotalWeight();
    double capacity      = currentSolution.getCapacity();

    // Need at least 1 in and 1 out to swap
    if (inKnapsack.isEmpty() || outKnapsack.isEmpty()) {
        return neighborSets;
    }

    // Remove 1 item (r) and add 1 item (a)
    for (int i = 0; i < inKnapsack.size(); i++) {
        Item r = inKnapsack.get(i);

        for (int k = 0; k < outKnapsack.size(); k++) {
            Item a = outKnapsack.get(k);

            double weightAfter = currentWeight - r.getWeight() + a.getWeight();

            if (weightAfter <= capacity) {
                ArrayList<Item> neighbor = new ArrayList<>(inKnapsack);
                neighbor.remove(r);
                neighbor.add(a);
                neighborSets.add(neighbor);
            }
        }
    }

    return neighborSets;
}

}


