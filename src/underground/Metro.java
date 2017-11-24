package underground;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Metro {

	private final Set<Station> allStations;
	private final Map<Station, Set<Line>> stationLineLookUp;
	private final Map<String, Line> lines;

	public Metro(Map<String, Line> lines) {
		final Set<Station> stations = new HashSet<>();
		final Map<Station, Set<Line>> stationToLine = new HashMap<>();
		for (final Map.Entry<String, Line> e : lines.entrySet()) {
			final Set<Station> stationsInLine = e.getValue().getStations();
			stations.addAll(stationsInLine);
			for (Station station : stationsInLine) {
				if (!stationToLine.containsKey(station))
					stationToLine.put(station, new HashSet<>());
				stationToLine.get(station).add(e.getValue());
			}
		}
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
		Queue<Station> path = new LinkedList<>();

		// Get line 'a' is on
		// Get line 'b' is on

		// Check if 'a' and 'b' are adjacent lines.

		/*
		 * > Just print the stations along one line until the common station is reached
		 * then print from that common station along the 'b' line.
		 */

		// Otherwise
		path = searchAdjacent(a, b);

		return path;
	}

	/**
	 * Lists all the {@link Station}s in the console.
	 */
	public void listStations() {
		allStations.forEach(station -> System.out.println(station.getName()));
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