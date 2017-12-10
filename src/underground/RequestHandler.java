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
	 * Calls the {@link metro} listStationsInLine method and attempts to
	 *  return it as a String, if the user inputted information provides an error
	 *  return the message of the caught exception.
	 * 
	 * @param line
	 * 			User inputted string naming the {@link Line} they want returned.
	 */
	public String listStationsInLine(String line) {
		try{
		StringBuilder sb = new StringBuilder();
		metro.listStationsOnLine(line).forEach(linename ->sb.append(linename.getName()).append("\n").append("\t"));
		
		return sb.toString();
		}
		catch(Exception e) {
			return e.getMessage();
		}
		
	}

	/**
	 * Calls the {@link metro} ListAllDirectlyConnectedLines method
	 * and tries to return it as a String if the user inputted information provides an error
	 *  return the message of the caught exception.
	 * 
	 * @param line
	 * 			User inputted string naming the {@link Line} they want 
	 * 			connection information returned for.
	 */
	public String listAllDirectlyConnectedLines(String line) {
		try {
		StringBuilder sb = new StringBuilder();
		metro.getAdjacentLines(line).forEach(linename ->sb.append(linename.getName()).append("\n").append("\t"));
		
		return sb.toString();
		}
		catch (Exception e) {
			return e.getMessage();
		}

	}

	/**
	 * Calls the @{link metro} findPath method ,convert its values into a string 
	 * using its toString method inside @{link metro}, if the user inputted information 
	 * provides an error return the message of the caught exception.
	 * 
	 * @param stationA
	 * 			User inputted value for the first station to check rhe path between
	 * 
	 * @param stationB
	 * 			User inputted value for the second station to check the path between
	 */			
	public String showPathBetween(String stationA, String stationB) {
		try{
			StringBuilder sb = new StringBuilder();
	Station	statA = new Station(stationA);
	Station	statB = new Station(stationB);	
	metro.findPath(statA, statB).forEach(linename ->sb.append(linename.getName()).append("\n").append("\t"));;
	//return metro.findPath(statA, statB).toString();	
	return sb.toString();
		}
		catch(Exception e){
		return e.getMessage();
		}
		
		 
	}

}
