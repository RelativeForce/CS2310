package underground;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * <code>MetroBuilder</code> is a class which 
 * 
 * 
 * 
 * @author 	159014260 John Berg
 * @since 	13/11/2017
 * @version 20/11/2017
 * @see 	Metro
 */
public class MetroBuilder
{
	//=========================================================================
	//Static fields.
	/**
	 * The {@link String} representing an message for when a <code>null</code>
	 * value is being added to a <code>MetroBuilder</code> which represents a
	 * station name.
	 */
	private static final String LINE_NAME_NOT_NULL =
			"A line name cannot be null";
	/**
	 * 
	 * The {@link String} representing an message for when a <code>null</code>
	 * value is being added to a <code>MetroBuilder</code> which represents a
	 * station name.
	 */
	private static final String STATION_NAME_NOT_NULL =
			"A station name cannot be null";
	//=========================================================================
	//Fields.
	/**
	 * The {@link Map} which maps a {@link String} representing line names to a
	 * {@link Set} containing {@link String} representations of stations names. 
	 */
	private final Map<String, Set<String>> lineInfo;
	//=========================================================================
	//Constructors.
	/**
	 * Create a new <code>MetroBuilder</code> which is empty.
	 */
	public MetroBuilder()
	{
		lineInfo = new HashMap<>();
	}
	//=========================================================================
	//Methods.
	/**
	 * Get the {@link Station} objects of a {@link Line} which exists inside
	 * <code>this</code>
	 * 
	 * <p>
	 * <b>For internal use only!</b>
	 * </p>
	 * 
	 * @param lineName The {@link String} which represents the {@link Line}
	 * 			object to get the {@link Station} objects from.
	 * @return A {@link Set} of {@link Station} objects, which belong to the
	 * 			{@link Line} which is represented by <code>lineName</code>.
	 */
	private Set<Station> getStationsOf(final String lineName)
	{
		return lineInfo.get(lineName)
				.stream()
				.map(Station::new)
				.collect(Collectors.toSet());
	}
	/**
	 * Create a {@link Set} of the {@link Station} objects which have been
	 * defined.
	 * 
	 * <p>
	 * The {@link Set} returned by this method is unmodifiable, and does not
	 * allow insertion or removal.
	 * </p>
	 * 
	 * <p>
	 * <b>For internal use only!</b>
	 * </p>
	 * 
	 * @return The {@link Set} of {@link Station} objects which are represented
	 * 			inside <code>this</code>.
	 */
	private Set<Station> createStations()
	{
		final Set<Station> stations = new HashSet<>();
		/*
		 * Create Station objects using the names of the stations, then add
		 * the newly created Station objects to the stations Set. 
		 */
		for(Set<String> stationNames: lineInfo.values())
			stationNames.stream()
			.map(Station::new)
			.forEach(stations::add);
		return Collections.unmodifiableSet(stations);
	}
	/**
	 * 
	 * 
	 * 
	 * @return The {@link Map} which contains the {@link String} and the
	 * 			corresponding {@link Line} object.
	 */
	private Map<String, Line> createLines()
	{
		//
		final Map<String, Set<String>> ajecentInfo = new HashMap<>();
		final Map<String, Line> lines = new HashMap<>();
		
		//Go through all the entries in lineInfo
		for(Map.Entry<String, Set<String>> e: lineInfo.entrySet())
		{
			final Set<Station> stations = getStationsOf(e.getKey());
			lines.put(
					e.getKey(),
					new Line(
							e.getKey(),
							Collections.unmodifiableSet(stations),
							findAdjecentLinesTo(e.getKey())));
		} //for
		return Collections.unmodifiableMap(lines);
	}
	/**
	 * 
	 * @param lineName The {@link String} representation of a {@link Line}
	 * 			object to find the adjacent {@link Line} objects.
	 * @return
	 */
	private Set<String> findAdjecentLinesTo(final String lineName)
	{
		/*
		 * Maintain a list of the adjacent lines, and the stations which are
		 * contained in lineName.
		 */
		final Set<String> adjacentLines = new HashSet<>();
		final Set<String> stations = lineInfo.get(lineName);
		
		//Search all the lines for lines directly connected to lineName.
		for(final Map.Entry<String, Set<String>> e: lineInfo.entrySet())
			/*
			 * Add the line to the adjacent lines if the lines share at least
			 * one station in common.
			 */
			if(e.getValue().stream().anyMatch(stations::contains))
				adjacentLines.add(e.getKey());
		//A line cannot be adjacent to itself, remove it.
		adjacentLines.remove(lineName);
		return Collections.unmodifiableSet(adjacentLines);
	}
	/**
	 * Add a new line to the Metro.
	 * 
	 * <p>
	 * Adding new line will be created if the name of the line to be created
	 * does not already exist.
	 * </p>
	 * 
	 * @param lineName The name of the line to be added.
	 * @throws NullPointerException If <code>lineName</code> is
	 * 			<code>null</code>.
	 */
	public final void addLine(final String lineName)
			throws
			NullPointerException
	{
		//Can't add a null value.
		if(lineName == null)
			throw new NullPointerException(LINE_NAME_NOT_NULL);
		
		//Check if the line exists before creating it.
		if(!lineInfo.containsKey(lineName))
			lineInfo.put(lineName, new HashSet<>());
	}
	/**
	 * Add a {@link String} representing a {@link Station} to a {@link Line}
	 * 
	 * 
	 * 
	 * @param stationName The {@link String} representation to station to be
	 * 			added.
	 * @param lineName The {@link String} object which represents the line
	 * 			that <code>stationName</code> should be added to.
	 * @throws NullPointerException If either <code>stationName</code> or
	 * 			<code>lineName</code> is <code>null</code>.
	 */
	public final void addStation(
			final String stationName,
			final String lineName)
			throws
			NullPointerException
	{
		//Cannot add a null value as a station.
		if(stationName == null)
			throw new NullPointerException(STATION_NAME_NOT_NULL);
		/*
		 * Make sure the line exists before adding the station to it.
		 * 
		 * May throw NullPointerException.
		 */
		addLine(lineName);
		//Add the station to the specified line.
		lineInfo.get(lineName).add(stationName);
	}
	/**
	 * Create a {@link Metro} object.
	 * 
	 * <p>
	 * The {@link Line} objects contained in the output {@link Metro} object
	 * created from the {@link String} representations added to
	 * <code>this</code> using the {@link #addLine(String)} method.
	 * </p>
	 * 
	 * <p>
	 * The {@link Station} objects contained in the output {@link Metro} object
	 * is created from the {@link String} ...
	 * </p>
	 * 
	 * @return The {@link Metro} which <code>this</code> represents.
	 * @see #addLine(String)
	 * @see #addStation(String, String)
	 */
	public final Metro build()
	{
		return new Metro(createStations(), createLines());
	}
}
