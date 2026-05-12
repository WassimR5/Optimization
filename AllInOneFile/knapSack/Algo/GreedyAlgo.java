package knapSack.Algo;

import java.util.ArrayList;
import java.util.Random;


import knapSack.models.Item;
import knapSack.models.KnapSack;

/**
 * Greedy algorithm for the Knapsack problem (value-to-weight heuristic).
 * This class mutates the provided KnapSack instance by adding items.
 *
 * <p>The deterministic mode fills the knapsack by taking items in descending value/weight order
 * while respecting capacity. The randomized mode samples among top candidates to introduce diversity.
 */
public class GreedyAlgo {

    private static final int DEFAULT_PICK_LIMIT = 3;

    /**
     * Perform greedy selection on the provided items and add chosen items to the provided knapSack.
     * This method mutates the knapSack parameter and does not return it.
     *
     * @param items list of candidate items (will be sorted by value/weight)
     * @param knapSack target knapsack to populate
     * @param deterministic if true, pick deterministically; otherwise use randomized selection
     */
    public static void run(ArrayList<Item> items, KnapSack knapSack, boolean deterministic) {

        // Calculate the value-to-weight ratio for each item
        ArrayList<Item> sortedItems = valueToWeight(items);
        if (deterministic) {
            for (Item i : sortedItems) {
                if (knapSack.getTotalWeight() + i.getWeight() <= knapSack.getCapacity()) {
                    knapSack.addItem(i);
                }
            }
        } else {
            int pickLimit = DEFAULT_PICK_LIMIT;
            Random random = new Random();
            ArrayList<Item> candidates = new ArrayList<>(sortedItems);

            for (int idx = 0; idx < candidates.size(); idx++) {
                // we pick randomly the min between the pick limit
                // and the number of items left to choose from
                int chooseNumber = Math.min(pickLimit, candidates.size());
                int index = random.nextInt(chooseNumber);
                Item item = candidates.get(index);

                if (knapSack.getTotalWeight() + item.getWeight() <= knapSack.getCapacity()) {
                    knapSack.addItem(item);
                    candidates.remove(index);
                }
            }
        }


    }

    private static ArrayList<Item> valueToWeight(ArrayList<Item> items) {
        items.sort((a, b) -> Double.compare(
                b.getValue() / b.getWeight(),
                a.getValue() / a.getWeight()
        ));

        return items;
    }
}
