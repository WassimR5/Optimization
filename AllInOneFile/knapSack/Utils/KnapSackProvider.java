package knapSack.Utils;


import knapSack.models.KnapSack;

/**
 * Factory for creating KnapSack objects.
 *
 * <p>Provides a single place to centralize KnapSack construction. This helps
 * if additional setup or parameters are needed in the future.
 */
public class KnapSackProvider {
    public static KnapSack createKnapSack(double capacity) {
        return new KnapSack(capacity);
    }
}

