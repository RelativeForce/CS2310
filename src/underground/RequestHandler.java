package underground;

/**
 * This class provides concrete implementation for the @{link Controller} by
 * instancing the @{link Metro} and calling its methods.
 * 
 * @author David_Wightman 159102257 
 *
 */
public class RequestHandler implements Controller{
	/**
	 * The {@link Metro} we will be instancing in this {@link RequestHandler}
	 */
	private Metro metro;
	
	/**
	 * Create a new {@link RequestHandler}
	 * 
	 * @param input
	 * 				The {@link Metro} representing the the whole system whose methods
	 * 				will be called and returned as strings.
	 */
	public RequestHandler(Metro input){
		metro = input;
		
	}

	/**
	 * Calls the {@link metro} outputAllStrings method and returns it as a String
	 */
	public String listAllTermini() {
		return metro.outputAllStations();
		
	}

	/**
	 * Calls the {@link metro} listStationsInLine method and returns it as a String
	 * 
	 * @param line
	 * 			User inputted string naming the {@link Line} they want returned.
	 */
	public String listStationsInLine(String line) {
		StringBuilder sb = new StringBuilder();
		metro.listStationsOnLine(line).forEach(linename ->sb.append(linename.getName()).append("\n"));
		
		return sb.toString();
		
		
	}

	/**
	 * Calls the {@link metro} ListAllDirectlyConnectedLines method
	 * and returns it as a String
	 * 
	 * @param line
	 * 			User inputted string naming the {@link Line} they want 
	 * 			connection information returned for.
	 */
	public String listAllDirectlyConnectedLines(String line) {
		StringBuilder sb = new StringBuilder();
		metro.getAdjacentLines(line).forEach(linename ->sb.append(linename.getName()).append("\n"));
		
		return sb.toString();
	}

	/**
	 * Calls the @{link metro} findPath method and return its values as a string.
	 * 
	 * @param stationA
	 * 			User inputted value for the first station to check rhe path between
	 * 
	 * @param stationB
	 * 			User inputted value for the second station to check the path between
	 */			
	public String showPathBetween(String stationA, String stationB) {
	Station	statA = new Station(stationA);
	Station	statB = new Station(stationB);	
	return metro.findPath(statA, statB).toString();
		 
	}

}
