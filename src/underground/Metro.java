package underground;

import java.util.Map;
import java.util.Set;

public class Metro {
	
	private Set<Station> allStations;
	
	private Map<String, Line> lines;
	
	public Metro(Set<Station> allStation, Map<String, Line> lines) {
		this.allStations = allStation;
		this.lines = lines;
	}
	
	

}
