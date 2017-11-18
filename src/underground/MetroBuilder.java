package underground;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MetroBuilder
{
	/**
	 * The {@link Map} which maps a {@link String} representing line names to a
	 * {@link Set} containing {@link String} representations of stations names. 
	 */
	private final Map<String, Set<String>> lineInfo;
	/**
	 * 
	 */
	public MetroBuilder()
	{
		lineInfo = new HashMap<>();
	}
	/**
	 * 
	 * @param lineName
	 * @return
	 */
	private Set<Station> getStationsOf(final String lineName)
	{
		return lineInfo.get(lineName)
				.stream()
				.map(Station::new)
				.collect(Collectors.toSet());
	}
	/**
	 * 
	 * @return
	 */
	private Map<String, Line> createLines()
	{
		final Map<String, Set<String>> ajecentInfo = new HashMap<>();
		final Map<String, Line> lines = new HashMap<>();
		for(Map.Entry<String, Set<String>> e: lineInfo.entrySet())
		{
			final Set<Station> stations = getStationsOf(e.getKey());
			lines.put(
					e.getKey(),
					new Line(
							e.getKey(),
							Collections.unmodifiableSet(stations),
							null)); //TODO insert adjacnt lines.
		} //for
		return Collections.unmodifiableMap(lines);
	}
	/**
	 * 
	 * @param lineName
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
	 * 			<code>null</code>
	 */
	public final void addLine(final String lineName)
			throws
			NullPointerException
	{
		//Can't add a null value.
		if(lineName == null)
			throw new NullPointerException("A line name cannot be null.");
		
		//Check if the line exists before creating it.
		if(!lineInfo.containsKey(lineName))
			lineInfo.put(lineName, new HashSet<>());
	}
	/**
	 * 
	 * @param stationName
	 * @param lineName
	 * @throws NullPointerException
	 */
	public final void addStation(
			final String stationName,
			final String lineName)
			throws
			NullPointerException
	{
		//Cannot add a null value as a station.
		if(stationName == null)
			throw new NullPointerException("");
		/*
		 * Make sure the line exists before adding the station to it.
		 * 
		 * May throw NullPointerException.
		 */
		addLine(lineName);
		//Add the station to the specified line.
		lineInfo.get(lineName).add(stationName);
	}
	public final Metro build()
	{
		return new Metro(null, createLines());
	}
}
