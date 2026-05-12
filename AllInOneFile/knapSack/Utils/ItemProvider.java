package knapSack.Utils;



import java.util.ArrayList;
import java.util.Random;

import knapSack.models.Item;



/**
 * Deterministic item generator for experiments and tests.
 *
 * <p>Generates a fixed number of Item objects using a constant Random seed to ensure
 * reproducible sequences across runs. The distribution is uniform on [0, maxWeight] and [0, maxValue].
 */
public class ItemProvider {

    /**
     * Create deterministic random items for benchmarks.
     *
     * @param maxWeight upper bound (exclusive) for generated weights
     * @param maxValue upper bound (exclusive) for generated values
     * @param numberOfItems number of items to generate
     * @return mutable ArrayList of generated Item instances
     *
     * Implementation details: Uses Random with fixed seed (123) to produce repeatable item lists.
     */
    public static ArrayList<Item> createItem(double maxWeight, double maxValue, int numberOfItems) {
        ArrayList<Item> items = new ArrayList<>();
        Random random = new Random(123);
        for (int i = 0; i < numberOfItems; i++) {
            double weight = random.nextDouble(maxWeight + 1);
            double value = random.nextDouble(maxValue + 1);
            items.add(new Item(weight, value));
        }
        return items;
    }
}
