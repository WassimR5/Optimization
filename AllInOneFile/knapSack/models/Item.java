package knapSack.models;

/**
 * Represents an item with a weight and a value.
 * Instances are immutable after construction.
 *
 * <p>Two Item instances are equal when both weight and value are equal using
 * Double.compare semantics.
 */
public class Item {

    private final double weight;
    private final double value;

    public Item(double weight, double value) {
        this.weight = weight;
        this.value = value;
    }

    /** Returns the item weight. */
    public double getWeight() {
        return weight;
    }

    /** Returns the item value. */
    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Item)) return false;
        Item item = (Item) obj;
        return Double.compare(item.weight, weight) == 0 &&
               Double.compare(item.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long w = Double.doubleToLongBits(weight);
        long v = Double.doubleToLongBits(value);
        int result = (int) (w ^ (w >>> 32));
        result = 31 * result + (int) (v ^ (v >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("Item[weight=%.3f,value=%.3f]", weight, value);
    }
}

