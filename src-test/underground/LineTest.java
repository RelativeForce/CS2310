package underground;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author 	159014269 John Berg
 * @since	28/11/2017
 * @version 28/11/2017
 */
public class LineTest
{
	//========================================================================
	//Static fields.
	private static final String STATION_NAME_A = "Station A";
	private static final String STATION_NAME_B = "Station B";
	private static final String STATION_NAME_C = "Station C";
	private static final Station STATION_A = new Station(STATION_NAME_A);
	private static final Station STATION_B = new Station(STATION_NAME_B);
	private static final Station STATION_C = new Station(STATION_NAME_C);
	private static final String NON_EXISTING_LINE_NAME = "Line C";
	private static final String MOCK_LINE_NAME_A = "Line A";
	private static final String MOCK_LINE_NAME_B = "Line B";
	private static final Line NON_EXISTING_LINE = new Line(
			NON_EXISTING_LINE_NAME,
			Collections.EMPTY_SET,
			Collections.EMPTY_MAP);
	private static final Line MOCK_LINE_A = new Line(
			MOCK_LINE_NAME_A,
			new LinkedHashSet<Station>()
			{{
				//Add stations A and B to the line.
				add(STATION_A);
				add(STATION_B);
			}},
			new HashMap<String, Set<Station>>()
			{{
				//Line A intersects line B at station B.
				final HashSet<Station> stations = new HashSet<>();
				stations.add(STATION_B);
				put(MOCK_LINE_NAME_B, stations);
			}});
	private static final Line MOCK_LINE_B = new Line(
			MOCK_LINE_NAME_B,
			new LinkedHashSet<Station>()
			{{
				//Add station B and C to the line.
				add(STATION_B);
				add(STATION_C);
			}},
			new HashMap<String, Set<Station>>()
			{{
				//Line B intersects line A at station B.
				final HashSet<Station> stations = new HashSet<>();
				stations.add(STATION_B);
				put(MOCK_LINE_NAME_A, stations);
			}});
	//=========================================================================
	//Tests.
	/**
	 * Test the {@link Line#Line(String, java.util.Set, java.util.Map)
	 * constructor.
	 * 
	 * <p>
	 * This test will only pass if the
	 * {@link Line#Line(String, java.util.Set, java.util.Map) constructor
	 * throws a {@link NullPointerException}, when the first {@link String}
	 * argument is <code>null</code>.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testConstructor_NullArg0()
	{
		new Line(null, Collections.EMPTY_SET, Collections.EMPTY_MAP);
	}
	/**
	 * Test the {@link Line#Line(String, java.util.Set, java.util.Map)
	 * constructor.
	 * 
	 * <p>
	 * This test will only pass if the
	 * {@link Line#Line(String, java.util.Set, java.util.Map) constructor
	 * throws a {@link NullPointerException}, when the second {@link String}
	 * argument is <code>null</code>.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testConstructor_NullArg1()
	{
		new Line("", null, Collections.EMPTY_MAP);
	}
	/**
	 * Test the {@link Line#Line(String, java.util.Set, java.util.Map)
	 * constructor.
	 * 
	 * <p>
	 * This test will only pass if the
	 * {@link Line#Line(String, java.util.Set, java.util.Map) constructor
	 * throws a {@link NullPointerException}, when the third {@link String}
	 * argument is <code>null</code>.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testConstructor_NullArg2()
	{
		new Line("", Collections.EMPTY_SET, null);
	}
	@Test(expected = NullPointerException.class)
	public final void testContains_LineNullArg()
	{
		final Line line = null;
		MOCK_LINE_A.contains(line);
	}
	@Test
	public final void testContains_LineNotExisting()
	{
		assertFalse(MOCK_LINE_A.contains(NON_EXISTING_LINE));
	}
	@Test
	public final void testContains_LineExists()
	{
		assertTrue(MOCK_LINE_A.contains(NON_EXISTING_LINE));
	}
	@Test(expected = NullPointerException.class)
	public final void testContains_StationNullArg()
	{
	}
	@Test
	public final void testContains_StationsNotExisting()
	{
	}
	@Test
	public final void testContains_StationExists()
	{
		
	}
	@Test
	public final void testGetName_NotEqualString()
	{
		
	}
	@Test
	public final void testGetName_EqualString()
	{
		
	}
	@Test
	public final void testGetStations_Empty()
	{
		
	}
	@Test
	public final void testGetStations_NotEmpty()
	{
		
	}
	@Test
	public final void testGetStations_Contains()
	{
		
	}
	@Test
	public final void testGetAdjacentLines_Empty()
	{
		
	}
	@Test
	public final void testGetAdjacent_NotEmpty()
	{
		
	}
	@Test
	public final void testGetAdjacent_Contains()
	{
		
	}
	@Test(expected = NullPointerException.class)
	public final void testGetIntersectionOf_NullArg()
	{
		
	}
	@Test
	public final void testGetIntersectionOf_NotExisting()
	{
		
	}
	@Test
	public final void testGetIntersectionOf_Exists()
	{
		
	}
	@Test
	public final void testEquals_NullArg()
	{
		
	}
	@Test
	public final void testEquals_DifferentType()
	{
		
	}
	@Test
	public final void testEquals_NotEqual()
	{
		
	}
	@Test
	public final void testEquals_Equal()
	{
		
	}
	@Test
	public final void testHashCode_NotEqual()
	{
		
	}
	@Test
	public final void testHashCode_Equal()
	{
		
	}
	@Test
	public final void testToString_Empty()
	{
		
	}
	@Test
	public final void testToString_NotEmpty()
	{
		
	}
}
