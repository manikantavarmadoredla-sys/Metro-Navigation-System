import java.util.*;

/**
 * Console-based Metro Navigation System using Dijkstra's shortest-path algorithm.
 * Distance values are illustrative sample data, not official metro timings.
 */
public class MetroNavigationSystem {

    private static class Edge {
        String station;
        int distanceKm;

        Edge(String station, int distanceKm) {
            this.station = station;
            this.distanceKm = distanceKm;
        }
    }

    private static class Node implements Comparable<Node> {
        String station;
        int distance;

        Node(String station, int distance) {
            this.station = station;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private static final Map<String, List<Edge>> metroMap = new LinkedHashMap<>();

    private static void addStation(String station) {
        metroMap.putIfAbsent(station, new ArrayList<>());
    }

    private static void addRoute(String from, String to, int distanceKm) {
        addStation(from);
        addStation(to);
        metroMap.get(from).add(new Edge(to, distanceKm));
        metroMap.get(to).add(new Edge(from, distanceKm));
    }

    private static void buildSampleMetroMap() {
        addRoute("Miyapur", "JNTU College", 3);
        addRoute("JNTU College", "KPHB Colony", 2);
        addRoute("KPHB Colony", "Kukatpally", 2);
        addRoute("Kukatpally", "Balanagar", 3);
        addRoute("Balanagar", "Moosapet", 2);
        addRoute("Moosapet", "Bharat Nagar", 2);
        addRoute("Bharat Nagar", "Erragadda", 2);
        addRoute("Erragadda", "ESI Hospital", 2);
        addRoute("ESI Hospital", "Ameerpet", 3);
        addRoute("Ameerpet", "Punjagutta", 2);
        addRoute("Punjagutta", "Irrum Manzil", 2);
        addRoute("Irrum Manzil", "Khairatabad", 2);
        addRoute("Khairatabad", "Lakdikapul", 2);
        addRoute("Lakdikapul", "Assembly", 2);
        addRoute("Assembly", "Nampally", 2);
        addRoute("Nampally", "Gandhi Bhavan", 2);
        addRoute("Gandhi Bhavan", "Osmania Medical College", 2);
        addRoute("Osmania Medical College", "MG Bus Station", 2);
    }

    private static void showStations() {
        System.out.println("\nAvailable stations:");
        for (String station : metroMap.keySet()) {
            System.out.println("- " + station);
        }
    }

    private static void findShortestRoute(String source, String destination) {
        if (!metroMap.containsKey(source) || !metroMap.containsKey(destination)) {
            System.out.println("Invalid station name. Please choose a station from the list.");
            return;
        }

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (String station : metroMap.keySet()) {
            distances.put(station, Integer.MAX_VALUE);
        }

        distances.put(source, 0);
        queue.offer(new Node(source, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.distance > distances.get(current.station)) {
                continue;
            }

            if (current.station.equals(destination)) {
                break;
            }

            for (Edge edge : metroMap.get(current.station)) {
                int newDistance = current.distance + edge.distanceKm;

                if (newDistance < distances.get(edge.station)) {
                    distances.put(edge.station, newDistance);
                    previous.put(edge.station, current.station);
                    queue.offer(new Node(edge.station, newDistance));
                }
            }
        }

        if (distances.get(destination) == Integer.MAX_VALUE) {
            System.out.println("No route found.");
            return;
        }

        List<String> route = new ArrayList<>();
        String station = destination;

        while (station != null) {
            route.add(station);
            station = previous.get(station);
        }
        Collections.reverse(route);

        int totalDistance = distances.get(destination);
        // Illustrative estimate using an assumed average speed of 30 km/h.
        int estimatedMinutes = (int) Math.ceil((totalDistance / 30.0) * 60);

        System.out.println("\n========== ROUTE DETAILS ==========");
        System.out.println("From: " + source);
        System.out.println("To: " + destination);
        System.out.println("\nShortest route:");

        for (int i = 0; i < route.size(); i++) {
            System.out.print(route.get(i));
            if (i < route.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\n\nNext station: "
                + (route.size() > 1 ? route.get(1) : "You are already at your destination"));
        System.out.println("Total distance: " + totalDistance + " km");
        System.out.println("Estimated travel time: " + estimatedMinutes
                + " minutes (illustrative estimate)");
        System.out.println("===================================");
    }

    public static void main(String[] args) {
        buildSampleMetroMap();

        Scanner scanner = new Scanner(System.in);
        System.out.println("===== METRO NAVIGATION SYSTEM =====");

        while (true) {
            System.out.println("\n1. View all stations");
            System.out.println("2. Find shortest route");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showStations();
                    break;
                case 2:
                    showStations();
                    System.out.print("\nEnter source station exactly as shown: ");
                    String source = scanner.nextLine().trim();
                    System.out.print("Enter destination station exactly as shown: ");
                    String destination = scanner.nextLine().trim();
                    findShortestRoute(source, destination);
                    break;
                case 3:
                    System.out.println("Thank you for using Metro Navigation System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }
}
