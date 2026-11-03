# Metro Navigation System

A beginner-friendly, console-based Java project that finds the shortest route between metro stations using **Dijkstra's shortest-path algorithm**.

## Features
- Displays the available sample metro stations.
- Accepts source and destination stations.
- Finds the shortest route using Dijkstra's algorithm.
- Displays the route, next station, total distance, and an illustrative travel-time estimate.

## Technologies
- Java
- Collections Framework (`Map`, `List`, `PriorityQueue`, `HashMap`)
- Dijkstra's algorithm
- Graph data structure

## How to run

1. Install JDK 8 or later.
2. Open a terminal in this project folder.
3. Compile:

   ```bash
   javac MetroNavigationSystem.java
   ```

4. Run:

   ```bash
   java MetroNavigationSystem
   ```

## Sample stations
Miyapur, JNTU College, KPHB Colony, Kukatpally, Balanagar, Moosapet, Bharat Nagar, Erragadda, ESI Hospital, Ameerpet, Punjagutta, Irrum Manzil, Khairatabad, Lakdikapul, Assembly, Nampally, Gandhi Bhavan, Osmania Medical College, MG Bus Station.

## Important note
The station connections and distances in this project are illustrative sample data for demonstrating graph traversal and Dijkstra's algorithm. They are not official metro route, distance, or timing data.

## Future improvements
- Add a graphical user interface.
- Load station data from a file or database.
- Add ticket fare calculation.
- Use verified metro data for real route and timing information.
