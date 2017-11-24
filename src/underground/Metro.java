package underground;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Metro {
	
	private final Set<Station> allStations;
	private final Map<Station, Set<Line>> stationLineLookUp;
	private final Map<String, Line> lines;
	
	public Metro(Map<String, Line> lines) {
		final Set<Station> stations = new HashSet<>();
		final Map<Station, Set<Line>> stationToLine = new HashMap<>();
		for(final Map.Entry<String, Line> e: lines.entrySet())
		{
			final Set<Station> stationsInLine = e.getValue().getStations();
			stations.addAll(stationsInLine);
			for(Station station: stationsInLine)
			{
				if(!stationToLine.containsKey(station))
					stationToLine.put(station, new HashSet<>());
				stationToLine.get(station).add(e.getValue());
			}
		}
		this.lines = lines;
		this.stationLineLookUp = Collections.unmodifiableMap(stationToLine);
		this.allStations = Collections.unmodifiableSet(stations);
	}
}
