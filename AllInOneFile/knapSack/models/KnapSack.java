
package knapSack.models;

import java.util.ArrayList;

/**
 * Simple container for knapsack state: capacity, item list, and aggregates (weight/value).
 * Provides methods to add items and update the whole item list; aggregates are maintained.
 *
 * <p>Invariant: totalWeight and totalValue reflect the items list and are recomputed on update.
 */
public class KnapSack {

    private double capacity = 0;
    private ArrayList<Item> items;
    private double totalWeight = 0;
    private double totalValue = 0;

    
    public KnapSack(){
        items = new ArrayList<>();
    }
   

    public KnapSack(double capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public double getCapacity() {
        return capacity;
    }

    public int getNumberOfItems() {
        return items.size();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Returns the total weight of items currently in the knapsack.
     */
    public double getTotalWeight(){
        return totalWeight;
    }

    /**
     * Add an item to the knapsack and update aggregated totals (weight and value).
     * The method assumes caller ensures feasibility when required.
     */
    public void addItem(Item item) {
        items.add(item);
        totalWeight += item.getWeight();
        totalValue += item.getValue();
    }

    /**
     * Replace current items with the given list and recompute total weight and total value.
     * This mutates the knapsack state.
     */
    public void updateItemsList(ArrayList<Item> items) {
        this.items = items;
        totalWeight = items.stream().mapToDouble(Item::getWeight).sum();
        totalValue = items.stream().mapToDouble(Item::getValue).sum();
    }

    /**
     * Returns the total value of items currently in the knapsack.
     */
    public double getTotalValue(){
        return totalValue;
    }

    

    
}