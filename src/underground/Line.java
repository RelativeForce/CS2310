package underground;

import java.util.Set;

public class Line {

	private String name;

	private final Set<Station> stations;

	private final Set<Line> adjacentLines;

	public Line(String name, Set<Station> stations, Set<Line> adjasentLines) {
		this.stations = stations;
		this.name = name;
		this.adjacentLines = adjasentLines;
	}
	
	public boolean contains(Line line) {
		return adjacentLines.contains(line);
	}
	
	public boolean contains(Station station) {
		return stations.contains(station);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		
		// TODO Display all the names of the stations and adjacent line.
		
		return name;
	}

}
