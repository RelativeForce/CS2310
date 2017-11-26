package underground;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * This encapsulates the behaviours of the Metro which is a system of
 * interconnected @{@link Line}s containing a set of {@link Station}s.
 * 
 * @author Joshua_Eddy 159029448
 * @author John_Berg #########
 *
 */
public class Metro {

	/**
	 * The {@link Set} of all {@link Station}s in this {@link Metro}.
	 */
	private final Set<Station> allStations;

	/**
	 * The {@link Map} of {@link Station}s to their parent {@link Set} of
	 * {@link Line}(s). This exists as for the purpose of path finding a
	 * {@link Station} must know which line(s) it is a part of.
	 */
	private final Map<Station, Set<Line>> stationLineLookUp;

	/**
	 * The {@link Map} of {@link Line} name to their associated {@link Line}.
	 */
	private final Map<String, Line> lines;

	/**
	 * Constructs a new {@link Metro}
	 * 
	 * @param lines
	 *            The {@link Line}s that make up this {@link Metro}. These elements
	 *            will be pre-computed into varying collections in orderer to
	 *            increase path finding efficiency.
	 */
	public Metro(Map<String, Line> lines) {

		// The set of stations that will be used to construct allStations
		final Set<Station> stations = new HashSet<>();

		// The map of associations between stations and their lines.
		final Map<Station, Set<Line>> stationToLine = new HashMap<>();

		// Iterate through each line in the parameter line map.
		lines.forEach((lineName, line) -> {

			// Add all the stations on that line to the set of all stations.
			final Set<Station> stationsInLine = line.getStations();
			stations.addAll(stationsInLine);

			// Iterate through all the stations on the current line and add an association
			// for from the station back to its parent line(s).
			stationsInLine.forEach(station -> {

				if (!stationToLine.containsKey(station)) {
					stationToLine.put(station, new HashSet<>());
				}

				stationToLine.get(station).add(line);

			});
		});

		this.lines = lines;
		this.stationLineLookUp = Collections.unmodifiableMap(stationToLine);
		this.allStations = Collections.unmodifiableSet(stations);

	}

	/**
	 * Finds a path between two specified {@link Station}s and retrieves the
	 * {@link Queue} of {@link Station}s that denotes the path..
	 * 
	 * @param a
	 *            {@link Station}
	 * @param b
	 *            {@link Station}
	 * @return {@link Queue} path
	 */
	public Queue<Station> findPath(Station a, Station b) {

		// Holds the line that 'a' and 'b' have in common.
		Line commonLine = null;

		// Check if 'a' and 'b' are on the same line.
		for (Line line : stationLineLookUp.get(a)) {
			if (stationLineLookUp.get(b).contains(line)) {
				commonLine = line;
			}
		}

		// If there is a common line get the stations between them.
		if (commonLine != null) {
			return getPathOnLine(commonLine, a, b);
		}
		// Otherwise
		else {
			return searchAdjacent(a, b);
		}
	}

	/**
	 * Lists all the {@link Station}s in the console.
	 */
	public void listStations() {
		allStations.forEach(station -> System.out.println(station.getName()));
	}

	/**
	 * Outputs all the {@link Station}s into a string separated by "\n"
	 * 
	 * @return List of all stations
	 */
	public String outputAllStations() {
		StringBuilder output = new StringBuilder();

		// For each station add it to the output on a new line.
		allStations.forEach(s -> output.append(s).append(" \n"));
		return output.toString();
	}

	/**
	 * Retrieves the {@link Station}s along one {@link Line} until the common
	 * {@link Station} is reached.
	 * 
	 * @param line
	 *            {@link Line} both {@link Station}s are on.
	 * @param a
	 *            {@link Station} A
	 * @param b
	 *            {@link Station} b
	 * @return {@link Queue} path between the two stations.
	 */
	private Queue<Station> getPathOnLine(Line line, Station a, Station b) {

		Queue<Station> path = new LinkedList<>();

		/**
		 * As 'a' may be in either direction along the specified line from 'b' the line
		 * must be iterated in both directions.
		 */
		line.getStations().forEach(station -> {

			// Whether the path contains 'a' already
			final boolean containsA = path.contains(a);

			// Whether the path contains 'a' already
			final boolean containsB = path.contains(b);

			// Whether the current station is 'a' or 'b'
			final boolean isAorB = station.equals(a) || station.equals(b);

			// Whether the path contains 'a' OR 'b'
			final boolean containsAorB = containsA || containsB;

			// Whether the path contains 'a' AND 'b'
			final boolean containsAandB = containsA && containsB;

			/*
			 * If the current station is 'a' or 'b' OR the the path contains 'a' or 'b' but
			 * NOT both, add the current station to the path.
			 */
			if ((containsAorB && !containsAandB) || isAorB) {
				path.add(station);
			}
		});

		return path;
	}

	private Queue<Station> searchAdjacent(Station a, Station b) {

		Set<Line> aLines = stationLineLookUp.get(a);
		Set<Line> bLines = stationLineLookUp.get(b);

		Line aLine = null;
		Line bLine = null;

		// If a is not a junction station
		if (aLines.size() == 1) {
			aLine = aLines.toArray(new Line[1])[0];
		}

		// If b is not a junction station
		if (bLines.size() == 1) {
			bLine = bLines.toArray(new Line[1])[0];
		}

		// 'a' and 'b' are junction station
		if (aLine != null && bLine != null) {

		}
		// a is a junction station
		else if (aLine != null) {

		}
		// b is a junction station
		else if (bLine != null) {

		}

		return null;
	}

}