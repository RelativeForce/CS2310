package underground;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@code MetroBuilder} is a class which creates {@link Metro} objects.
 * 
 * <p>
 * The {@link Metro} produced by the {@code MetroBuilder} is equivelant to the
 * builder.
 * </p>
 * 
 * <p>
 * {@code MetroBuilder} is responsible for collecting information about a
 * {@link Metro} object, before producing the {@link Model} represented by the
 * {@code MetroBuilder}. The {@code MetroBuilder} also will pre-compute the
 * {@link Line} objects that will exist within thin the {@link Metro}, such as
 * the lines which are directly connected to a specific {@link Line}.
 * </p>
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
	 * The resulting {@link Set} is unmodifiable, and does not allow for
	 * insertion or removal.
	 * </p>
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
		return Collections.unmodifiableSet(new LinkedHashSet<>(
				lineInfo.get(lineName).stream()
				.map(Station::new)
				.collect(Collectors.toList())));
	}
	/**
	 * Create the {@link Map} of {@link String} to {@link Line} objects, where
	 * the {@link String} is the key which is the name of the corresponding
	 * value {@link Line}. 
	 * 
	 * <p>
	 * The output {@link Map} is unmodifiable, and does not allow for insertion
	 * or removal.
	 * </p>
	 * 
	 * <p>
	 * <b>For internal use only!</b>
	 * </p>
	 * 
	 * @return The {@link Map} which contains the {@link String} and the
	 * 			corresponding {@link Line} object.
	 */
	private Map<String, Line> createLines()
	{
		final Map<String, Line> lines = new HashMap<>(lineInfo.size());
		
		//Go through all the entries in lineInfo
		for(Map.Entry<String, Set<String>> e: lineInfo.entrySet())
		{
			final String lineName = e.getKey();
			final Set<Station> stations = getStationsOf(lineName);
			lines.put(
					lineName,
					new Line(
							lineName,
							stations,
							findAdjecentLinesTo(lineName)));
		} //for
		return Collections.unmodifiableMap(lines);
	}
	/**
	 * Find the names of the {@link Line} objects which exist adjacent to
	 * a specified {@link Line}.
	 * 
	 * @param lineName The {@link String} representation of a {@link Line}
	 * 			object to find the adjacent {@link Line} objects.
	 * @return The {@link Map} of {@link String} objects which represent the
	 * 			names of the {@link Line} objects which are directly connected
	 * 			to the {@link Line} represented by <code>lineName</code>. The
	 * 			values of the {@link Map} is the {@link Station} objects which
	 * 			exist on both the {@link Line} represented by
	 * 			<code>lineName</code> and the adjacent {@link Line}.
	 */
	private Map<String, Set<Station>> findAdjecentLinesTo(final String lineName)
	{
		/*
		 * Maintain a list of the adjacent lines, and the stations which are
		 * contained in lineName.
		 */
		final Map<String, Set<Station>> adjacentLines = new HashMap<>();
		final Set<String> stations = lineInfo.get(lineName);
		
		//Search all the lines for lines directly connected to lineName.
		for(final Map.Entry<String, Set<String>> e: lineInfo.entrySet())
		{
			//Get all the stations which intersect between two lines.
			final Set<Station> intersectingStations = e.getValue().stream()
					.filter(stations::contains)
					.map(Station::new)
					.collect(Collectors.toSet());
			/*
			 * If the set of intersecting stations is not empty, add the set
			 * and a the String representing the line to the adjacentLines map.
			 */
			if(!intersectingStations.isEmpty())
				adjacentLines.put(e.getKey(), intersectingStations);
		}
		//A line cannot be adjacent to itself, remove it.
		adjacentLines.remove(lineName);
		return Collections.unmodifiableMap(adjacentLines);
	}
	/**
	 * Add a new line to <code>this</code> {@code MetroBuilder}.
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
			lineInfo.put(lineName, new LinkedHashSet<>());
	}
	/**
	 * Add a {@link String} representing a {@link Station} to a {@link Line}
	 * 
	 * <p>
	 * If the {@link String} which represents the {@link Line} that the
	 * {@link Station} should be added to, does not exist, then the
	 * {@link Line} will be created before adding the {@link Station}.
	 * </p>
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
	 * is created from the {@link String} representations of {@link Line} and
	 * {@link Station}.
	 * </p>
	 * 
	 * @return The {@link Metro} which <code>this</code> represents.
	 * @see #addLine(String)
	 * @see #addStation(String, String)
	 */
	public final Metro build()
	{
		return new Metro(createLines());
	}
}
