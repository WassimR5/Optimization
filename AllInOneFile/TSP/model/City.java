package TSP.model;

/**
 * Simple 2D point representing a city in TSP instances.
 * Fields are package-private for performance; distanceTo implements Euclidean distance.
 */
public class City {

    int id;
    double x;
    double y;

    public City(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * Euclidean distance to another city.
     *
     * @param other target city
     * @return Euclidean distance
     */
    public double distanceTo(City other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
