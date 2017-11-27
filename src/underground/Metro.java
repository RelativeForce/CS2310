package underground;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
	 * Constructs a new {@link Metro}.
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
	 * @param start
	 *            {@link Station}
	 * @param end
	 *            {@link Station}
	 * @return {@link Queue} path
	 */
	public Queue<Station> findPath(Station start, Station end) {

		Stack<Line> linePath = new Stack<>();
		Set<Line> traversed = new HashSet<>();

		// The first line that will be checked
		Line startLine = stationLineLookUp.get(start).iterator().next();

		// If there is a line path get the path
		if (searchAdjacent(linePath, traversed, startLine, end)) {
			return getPathFromLines(linePath, start, end);
		}

		return null;

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
	private LinkedList<Station> getPathOnLine(Line line, Station a, Station b) {

		// The path between the two stations
		LinkedList<Station> path = new LinkedList<>();

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

		// If b is at the start of the path. Reverse the path.
		if (path.peek() == b) {

			Stack<Station> temp = new Stack<>();

			path.forEach(station -> temp.push(station));

			path.clear();

			while (!temp.isEmpty()) {
				path.add(temp.pop());
			}

		}

		return path;
	}

	private Queue<Station> getPathFromLines(Stack<Line> linePath, Station start, Station end) {

		// Holds the full path from the start station to the end station.
		LinkedList<Station> fullPath = new LinkedList<>();

		Station previousStationNode = start;
		Station nextStationNode = null;
		Line previousLine = null;

		// Iterate through each line of the line path from.
		for (Line line : linePath) {

			// If there is a previous line.
			if (previousLine != null) {

				// Set the next node as the first intersecting node between the current line and
				// the previous line.
				nextStationNode = line.getIntersectingStationsOf(previousLine.getName()).iterator().next();

				// Get the path along the previous line from the previous node to the current
				// node.
				LinkedList<Station> path = getPathOnLine(previousLine, previousStationNode, nextStationNode);

				// Remove the station that will start the next leg of the full path along the
				// current line.
				previousStationNode = path.removeLast();

				// Add the path to the full path
				fullPath.addAll(path);
			}

			// Set the current line as the previous line.
			previousLine = line;

		}

		// Add the last leg of the path from the last node to the end station
		fullPath.addAll(getPathOnLine(previousLine, previousStationNode, end));

		return fullPath;
	}

	/**
	 * Performs one stage of a recursive depth first search across all the
	 * {@link Line} in the {@link Metro}.
	 * 
	 * @param linePath
	 *            {@link Stack} path of {@link Line}s that have been.
	 * @param traversed
	 *            {@link Set} of {@link Line}s that have been traversed.
	 * @param current
	 *            The current {@link Line} being evaluated.
	 * @param target
	 *            The target {@link Station} that this DFS is attempting to find.
	 * @return Whether a path was found or not.
	 */
	private boolean searchAdjacent(Stack<Line> linePath, Set<Line> traversed, Line current, Station target) {

		linePath.push(current);
		traversed.add(current);

		// If the target station is on the current line.
		if (current.contains(target)) {
			return true;
		}

		final Set<Line> validLines = new HashSet<>();

		final Set<Line> currentLines = getAdjacentLines(current);

		final Set<Line> targetLines = stationLineLookUp.get(target);

		// Check if the line is part of the current
		for (Line line : currentLines) {

			// If the set of lines connected to the target station then add the line to the
			// line path as it line
			if (targetLines.contains(line)) {
				linePath.push(line);
				return true;
			}

			// If the line has not already been traversed add it as a valid child.
			if (!traversed.contains(line)) {
				validLines.add(line);
			}

		}

		// Iterate through each valid child line. Return true if a path is found using a
		// child line.
		for (Line line : validLines) {
			if (searchAdjacent(linePath, traversed, line, target)) {
				return true;
			}
		}

		// If there are no valid child lines then return to parent line.
		linePath.pop();
		return false;
	}

	/**
	 * Retrieves the {@link Set} of {@link Line}s that connect to the specified
	 * {@link Line}.
	 * 
	 * @param line
	 *            {@link Line}
	 * @return {@link Set} of {@link Line}s.
	 */
	private Set<Line> getAdjacentLines(Line line) {

		final Set<Line> currentLines = new HashSet<>();

		// Add all the lines to the set of current lines.
		line.getAdjacentLineNames().forEach(lineName -> currentLines.add(lines.get(lineName)));

		return currentLines;
	}

}