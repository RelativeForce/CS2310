package underground;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
 * @auhor David_Wightman 159102257
 *
 */
public final class Metro {

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
	public List<Station> findPath(Station start, Station end) {

		// Check parameters
		if (start == null) {
			throw new NullPointerException("Start cannot be null.");
		} else if (end == null) {
			throw new NullPointerException("End cannot be null.");
		} else if (!allStations.contains(start)) {
			throw new NullPointerException(start.getName() + " does not exist in this Metro.");
		} else if (!allStations.contains(end)) {
			throw new NullPointerException(end.getName() + " does not exist in this Metro.");
		}

		// Holds the lines that the path from the start to end stations traverses
		Stack<Line> linePath = new Stack<>();

		// THe set of lines that have been traversed by the path
		Set<Line> traversed = new HashSet<>();

		// The first line that will be checked
		Line startLine = stationLineLookUp.get(start).iterator().next();

		// If there is a line path get the path
		if (searchAdjacent(linePath, traversed, startLine, end)) {

			// Return the path
			return configurePath(linePath, start, end);
		}

		return null;

	}

	/**
	 * Lists all the {@link Station}s on a {@link Line}.
	 */
	public List<Station> listStationsOnLine(String lineName) {

		final List<Station> stations = new LinkedList<>();

		Line line = lines.get(lineName);

		// Check parameters
		if (line == null) {
			throw new NullPointerException(lineName + " does not exist in this Metro.");
		}

		// For each station add it to the output on a new line.
		line.getStations().forEach(s -> stations.add(s));
		return stations;
	}

	/**
	 * Outputs all the {@link Station}s into a string separated by "\n"
	 * 
	 * @return List of all stations
	 */
	public String outputAllStations() {
		StringBuilder output = new StringBuilder();

		lines.forEach((lineName, line) -> {

			output.append(lineName).append(": ").append("");

		});

		return output.toString();
	}

	/**
	 * Retrieves the {@link Set} of {@link Line}s that connect to the specified
	 * {@link Line}.
	 * 
	 * @param line
	 *            {@link Line}
	 * @return {@link Set} of {@link Line}s.
	 */
	public Set<Line> getAdjacentLines(String lineName) {

		final Set<Line> currentLines = new HashSet<>();

		Line line = lines.get(lineName);

		// Check parameters
		if (line == null) {
			throw new NullPointerException(lineName + " does not exist in this Metro.");
		}

		// Add all the lines to the set of current lines.
		line.getAdjacentLineNames().forEach(current -> currentLines.add(lines.get(current)));

		return currentLines;
	}

	/**
	 * Retrieves the {@link Set} of {@link Line}s that connect to the specified
	 * {@link Line}.
	 * 
	 * @param line
	 *            {@link Line}
	 * @return {@link Set} of {@link Line}s.
	 */
	public Set<Line> getAdjacentLines(Line line) {

		final Set<Line> currentLines = new HashSet<>();

		// Add all the lines to the set of current lines.
		line.getAdjacentLineNames().forEach(lineName -> currentLines.add(lines.get(lineName)));

		return currentLines;
	}

	/**
	 * Retrieves the {@link Station}s along one {@link Line} until the common
	 * {@link Station} is reached.
	 * 
	 * @param line
	 *            {@link Line} both {@link Station}s are on.
	 * @param start
	 *            {@link Station} A
	 * @param end
	 *            {@link Station} b
	 * @return {@link Queue} path between the two stations.
	 */
	private LinkedList<Station> getPathOnLine(Line line, Station start, Station end) {

		// The path between the two stations
		LinkedList<Station> path = new LinkedList<>();

		// Whether the path contains 'start' already
		boolean containsStart = false;

		// Whether the path contains 'end' already
		boolean containsEnd = false;

		/**
		 * Adds all the stations between 'start' and 'end' to the path
		 */
		for (Station station : line.getStations()) {

			// Whether the current station is 'start'
			final boolean isStart = station.equals(start);

			// Whether the current station is 'end'
			final boolean isEnd = station.equals(end);

			// If both stations are in the path stop iterating through the stations
			if (containsStart && containsEnd) {
				break;
			}

			/*
			 * If the current station is 'start' or 'end' OR the the path contains 'start' or
			 * 'end' add the current station to the path.
			 */
			if (containsStart || containsEnd || isStart || isEnd) {

				// Set the contains variables if the current station is 'start' or 'end'
				if (isStart) {
					containsStart = true;
				} else if (isEnd) {
					containsEnd = true;
				}
				path.add(station);
			}

		}

		// If 'end' is at the start of the path. Reverse the path.
		if (path.peek().equals(end)) {
			return reversePath(path);
		}

		return path;
	}

	/**
	 * Converts the path created by the DFS
	 * {@link Metro#searchAdjacent(Stack, Set, Line, Station)} into a {@link List}
	 * of {@link Station}s.
	 * 
	 * @param linePath
	 *            The {@link Stack} of {@link Line}s that are on the path from the
	 *            start {@link Station} to the end {@link Station}.
	 * @param start
	 *            {@link Station}
	 * @param end
	 *            {@link Station}
	 * @return {@link List} of {@link Station}s representing the path.
	 */
	private List<Station> configurePath(Stack<Line> linePath, Station start, Station end) {

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
	 * Reverses the specified {@link LinkedList} of {@link Station}s.
	 * 
	 * @param path
	 *            {@link LinkedList} of {@link Station}s
	 * @return reversed {@link LinkedList} of {@link Station}s
	 */
	private LinkedList<Station> reversePath(LinkedList<Station> path) {

		LinkedList<Station> reversePath = new LinkedList<>();

		/*
		 * Remove the last element from the path and add it to the reversed path
		 */
		while (!path.isEmpty()) {
			reversePath.add(path.removeLast());
		}

		return reversePath;
	}

}