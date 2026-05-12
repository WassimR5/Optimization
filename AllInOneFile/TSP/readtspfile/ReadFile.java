package TSP.readtspfile;

import java.io.*;
import java.util.*;

import TSP.model.City;


/**
 * Parser for TSPLIB .tsp files focusing on NODE_COORD_SECTION.
 *
 * <p>The parser locates the literal line "NODE_COORD_SECTION", then reads subsequent
 * lines expected to be: id x y. Parsing is intentionally minimal: malformed input
 * results in exceptions. Use only for well-formed TSPLIB instances.
 */
public class ReadFile {
    /**
     * Parse a .tsp file and return the list of cities in the NODE_COORD_SECTION.
     *
     * @param path path to the .tsp file
     * @return list of City objects described in the file
     * @throws IOException on I/O errors
     */
    public static List<City> read(String path) throws IOException {

        List<City> cities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            boolean nodeSection = false;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.equals("NODE_COORD_SECTION")) {
                    nodeSection = true;
                    continue;
                }

                if (line.equals("EOF")) {
                    break;
                }

                if (nodeSection) {

                    String[] parts = line.split("\\s+");

                    int id = Integer.parseInt(parts[0]);
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);

                    cities.add(new City(id, x, y));
                }
            }
        }

        return cities;
    }
}
