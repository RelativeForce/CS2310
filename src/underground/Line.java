package underground;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * The {@code Line} class is a class which models a line in an underground,
 * where every line has a name, stations and lines which connect directly
 * to it.
 * 
 * @author 	159014260 John Berg
 * @author 	159029448 Joshua Eddy
 * @since 	13/11/2017
 * @version 21/20/2017
 * @see Station
 */
public class Line {
	//=========================================================================
	//Static fields.
	/**
	 * The {@link String} which is used to separate the {@link #name} of a
	 * {@code Line} and the {@link Station} objects within a the {@code Line}.
	 * 
	 * @see #toString()
	 */
	private static final String LINE_COLON = ": ";
	/**
	 * The {@link String} which indicates the traveling direction
	 * between two {@link Station} objects.
	 */
	private static final String STATION_SEPARATOR  = " <-> ";
	/**
	 * The {@link String} which contains the error message for when creating
	 * a {@code Line} with an {@link Set#isEmpty()} {@link Set} of
	 * {@link Station} objects.
	 */
	private static final String NO_STATION_MESSAGE =
			"A line must have at least one station";
	/**
	 * The {@link String} representing the error message for when attempting to
	 * use a <code>null</code> value as a {@link Station} object.
	 */
	private static final String NULL_STATION_MESSAGE =
			"Station argument was null, and null is not a valid station.";
	/**
	 * The {@link String} representing the error message for when a
	 * {@code Line} is created with a <code>null<code> argument as a name.
	 */
	private static final String NULL_NAME =
			"String argument representing the Line name was null, and a Line"
			+ "canot have null as a name.";
	/**
	 * The {@link String} representing the error message for when a
	 * {@code Line} is created with a <code>null<code> argument as a
	 * {@link Set}.
	 */
	private static final String NULL_STATION_SET =
			"The Set of Station objects in a Line cannot be null.";
	/**
	 * The {@link String} representing the error message for when a
	 * {@code Line} is created with a <code>null<code> argument as a
	 * {@link Map}.
	 */
	private static final String NULL_ADJACENT_LINES =
			"The Map representing afjacent lines and shared stations cannot"
			+ "be null";
	//=========================================================================
	//Fields.
	/**
	 * The {@link String} representing the name of <code>this</code>
	 * {@code Line}.
	 */
	private final String name;
	/**
	 * The {@link Station} which is the first termini of the {@code Line}.
	 */
	private final Station firstTermini;
	/**
	 * The {@link Station} which is the first termini of the {@code Line}.
	 */
	private final Station lastTermini;
	/**
	 * The {@link Set} of {@link Station} objects which exist within
	 * <code>this</code> {@code Line}.
	 */
	private final Set<Station> stations;
	/**
	 * The {@link Map} of {@link String} object which represent the names of
	 * lines which exist adjacent to <code>this</code> {@code Line}. The values
	 * of {@link Map} is a {@link Set} which contains the {@link Station}
	 * objects that both <code>this</code> {@link Line} and an adjacent
	 * {@link Line}.
	 * 
	 * <p>
	 * An adjacent {@code Line} is a line which shares at least one
	 * {@link Station} object.
	 * </p>
	 */
	private final Map<String, Set<Station>> adjacentLines;
	//=========================================================================
	//Constructors.
	/**
	 * Create a new {@code Line} object.
	 * 
	 * <p>
	 * Construct a {@code Line} object by defining the name of the {@code Line}
	 * , the {@link Station} object that exist in the {@code Line}, and the
	 * {@link String} objects which represent other {@link Line} objects that
	 * the created {@code Line} is directly connected to.
	 * </p>
	 * 
	 * @param name The name of the {@code Line} to be created.
	 * @param stations The {@link Set} of {@link Station} objects which exist
	 * 			on the {@code Line} to be created.
	 * @param adjasentLines The {@link Map} of {@link String} objects which
	 * 			represents the {@code Line} objects which are directly
	 * 			connected to the created {@code Line}. The values of the
	 * 			{@link Map} are the {@link Station} objects which are are
	 * 			shared between the created {@code Line} and the adjacent
	 * 			{@code Line}.
	 * @see Station
	 */
	public Line(
			String name,
			Set<Station> stations,
			Map<String, Set<Station>> adjasentLines)
	{
		if(name == null)
			throw new NullPointerException(NULL_NAME);
		if(stations == null)
			throw new NullPointerException(NULL_STATION_SET);
		if(adjasentLines == null)
			throw new NullPointerException(NULL_ADJACENT_LINES);
		if(stations.isEmpty())
			throw new IllegalArgumentException(NO_STATION_MESSAGE);
		this.name = name;
		/*
		 * The sets containing information about the Line cannot be modified,
		 * as the Line class is immutable.
		 */
		this.stations = Collections.unmodifiableSet(
				new LinkedHashSet<>(stations));
		this.adjacentLines = Collections.unmodifiableMap(
				new HashMap<>(adjasentLines));
		final Station[] stationArray = stations.toArray(
				new Station[stations.size()]);
		
		//Set the first and last termini.
		firstTermini = stationArray[0];
		lastTermini = stationArray[stationArray.length - 1];

	}
	//=========================================================================
	//Methods.
	/**
	 * Check if a {@code Line} exists adjacent to <code>this</code>
	 * {@code Line}.
	 * 
	 * @param line The {@code Line} to check if it exists within
	 * 			<code>this</code>.
	 * @return <code>true</code> if the {@link #name} of <code>line</code>
	 * 			exists inside the {@link #adjacentLines}, otherwise, returns
	 * 			<code>false</code>.
	 */
	public final boolean contains(final Line line) {
		return adjacentLines.containsKey(line.name);
	}
	/**
	 * Check if a {@code Station} exists in <code>this</code> {@code Line}.
	 * 
	 * @param station The {@link Station} the check if it exists on
	 * 			<code>this</code> {@code Line}.
	 * @return <code>true</code> if <code>station</code> exists in
	 * 			<code>this</code> {@code Line}, otherwise, returns
	 * 			<code>false</code>.
	 */
	public boolean contains(Station station)
	{
		if(station == null)
			throw new NullPointerException(NULL_STATION_MESSAGE);
		return stations.contains(station);
	}
	/**
	 * Get the name of <code>this</code>.
	 * 
	 * @return The {@link String} which represents the name of
	 * 			<code>this</code> {@code Line}.
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Get the first terminal of <code>this</code>.
	 * 
	 * @return The {@link Station} representing the first terminal.
	 */
	public Station firstTerminal()
	{
		return firstTermini;
	}
	/**
	 * Get the last terminal of <code>this</code>.
	 * 
	 * @return The {@link Station} representing the last terminal.
	 */
	public Station lastTerminal()
	{
		return lastTermini;
	}
	/**
	 * Get the {@link Station} objects which exist in <code>this</code>
	 * {@code Line}.
	 * 
	 * @return The {@link Set} of {@link Station} objects which
	 * 			<code>this</code> {@code Line} contains.
	 */
	public final Set<Station> getStations()
	{
		return stations;
	}
	/**
	 * Get the names of the {@code Line} object which exist adjacent to
	 * <code>this</code>.
	 * 
	 * @return The {@link Set} of {@link String} objects which represent the
	 * 			names of the {@code Line} objects which are directly connected
	 * 			to <code>this</code> {@code Line}.
	 */
	public final Set<String> getAdjacentLineNames()
	{
		return adjacentLines.keySet();
	}
	/**
	 * Get the {@link Station} objects which exist on <code>this</code>
	 * {@link Line}, and another specified {@code Line}.
	 * 
	 * <p>
	 * The returned {@link Set} is unmodifiable.
	 * </p>
	 * 
	 * @param lineName The {@link String} representing a {@code Line} to get
	 * 			the intersecting {@link Station} objects from.
	 * @return The {@link Set} containing the {@link Station} objects which
	 * 			exist on both <code>this</code> {@code Line} and the
	 * 			{@code Line} which is represented by <code>lineName</code>.
	 */
	public final Set<Station> getIntersectingStationsOf(final String lineName)
	{
		if(lineName == null)
			throw new NullPointerException(NULL_NAME);
		if(adjacentLines.containsKey(lineName))
			return Collections.unmodifiableSet(adjacentLines.get(lineName));
		return Collections.emptySet();
	}
	//=========================================================================
	//Overriden methods.
	/**
	 * Compare <code>this</code> to another {@link Object} for equality.
	 * 
	 * <p>
	 * For an object to equal <code>this</code>, the object must be an instance
	 * of the {@code Line} class, then the object must also:
	 * <ol>
	 * 		<li>Have a name which are equal.</li>
	 * 		<li>Contain the same {@link Station} objects.</li>
	 * 		<li>Contain the same names of adjacent lines</li>
	 * </ol>
	 * </p>
	 * 
	 * @param o The {@link Object} to compare <code>this</code> against.
	 * @return <code>true</code> <code>o</code> is an instance of {@code Line}
	 * 			and both <code>this</code> and <code>o</code> have names that
	 * 			equal, contain the same {@link Station} object, and contain the
	 * 			same adjacent lines. 
	 */
	@Override
	public final boolean equals(final Object o)
	{
		if(o instanceof Line)
		{
			final Line otherLine = (Line) o;
			return name.equals(otherLine.name)
					&& stations.equals(otherLine.stations)
					&& adjacentLines.equals(otherLine.adjacentLines);
		}
		return false;
	}
	/**
	 * Get the hash code of <code>this</code> {@code Line}.
	 * 
	 * @return The hash code of <code>this</code> {@code Line}.
	 */
	@Override
	public final int hashCode()
	{
		return name.hashCode() ^ stations.size() ^ adjacentLines.size();
	}
	/**
	 * Get the {@link String} representation of <code>this</code> {@code Line},
	 * which details the name of the line and the {@link Station} objects that
	 * exist on <code>this</code> {@code Line}.
	 * 
	 * @return The {@link String} representation of <code>this</code>
	 * 			{@code Line}.
	 * @see Station
	 */
	@Override
	public final String toString()
	{
		final StringBuilder sb = new StringBuilder()
				.append(name)
				.append(LINE_COLON);
		/*
		 * Use the Iterator to iterate through all of the Station objects
		 * inside of stations.
		 * 
		 * Between each Station, a STATION_SEPARATOR should be inserted,
		 * as there exists no separator between the name of this line and the
		 * first Station, the first Station (if it exists) is a special case
		 * and should be appended to the StringBuilder without appending a
		 * LINE_SEPARATOR before it.
		 */
		Iterator<Station> iter = stations.iterator();
		if(iter.hasNext())
			sb.append(iter.next());
		while(iter.hasNext())
			sb.append(STATION_SEPARATOR)
			.append(iter.next());
		return sb.toString();
	}
}
