package underground;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * The {@code LineTest} is a test class for the {@link Line} class using
 * the JUnit testing framework.
 * 
 * <p>
 * {@code LineTest} will test the constructors and methods of the
 * {@link Line} class. A constructor or method in the {@link Line} class
 * may have multiple test cases to test different scenarios.
 * </p>
 * 
 * <p>
 * The tests are formatted as: "public final void test
 * {@code <nameOfTest>} [_TestCase]". Test cases are denoted using the
 * {@code @Test} annotation.
 * </p>
 * 
 * @author 	159014269 John Berg
 * @since	28/11/2017
 * @version 09/11/2017
 */
public class LineTest
{
	//========================================================================
	//Static fields.
	/**
	 * A {@link String} representing the name of a {@link Station} object.
	 */
	private static final String STATION_NAME_A = "Station A";
	/**
	 * A {@link String} representing the name of a {@link Station} object.
	 */
	private static final String STATION_NAME_B = "Station B";
	/**
	 * A {@link String} representing the name of a {@link Station} object.
	 */
	private static final String STATION_NAME_C = "Station C";
	/**
	 * A {@link Station} to be used in the tests.
	 */
	private static final Station STATION_A = new Station(STATION_NAME_A);
	/**
	 * A {@link Station} to be used in the tests.
	 */
	private static final Station STATION_B = new Station(STATION_NAME_B);
	/**
	 * A {@link Station} to be used in the tests.
	 */
	private static final Station STATION_C = new Station(STATION_NAME_C);
	/**
	 * The {@link String} representing the name of a {@link Line}.
	 */
	private static final String NON_EXISTING_LINE_NAME = "Line C";
	/**
	 * The {@link String} representing the name of a {@link Line}.
	 */
	private static final String MOCK_LINE_NAME_A = "Line A";
	/**
	 * The {@link String} representing the name of a {@link Line}.
	 */
	private static final String MOCK_LINE_NAME_B = "Line B";
	/**
	 * The {@link Line} which does not connect to any other {@link Line}
	 * objects, for testing situations where a {@link Line} does not
	 * have any shared {@link Station} objects with other lines.
	 */
	private static final Line NON_EXISTING_LINE = new Line(
			NON_EXISTING_LINE_NAME,
			new HashSet<Station>()
			{{
				add(new Station("x"));
			}},
			Collections.emptyMap());
	/**
	 * The {@link Line} object to use in tests.
	 */
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
	/**
	 * The {@link Line} object to use in tests.
	 */
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
		new Line(null, Collections.emptySet(), Collections.emptyMap());
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
	/**
	 * Test the {@link Line#contains(Line)} method/
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#contains(Line)} method
	 * throws a {@link NullPointerException} when providing it with a
	 * <code>null</code> argument.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testContains_LineNullArg()
	{
		final Line line = null;
		MOCK_LINE_A.contains(line);
	}
	/**
	 * Test the {@link Line#contains(Line)} method/
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#contains(Line)} method
	 * returns <code>false</code> when called with a {@link Line} object
	 * which is not adjacent the the {@link Line} object.
	 * </p>
	 */
	@Test
	public final void testContains_LineNotExisting()
	{
		assertFalse(MOCK_LINE_A.contains(NON_EXISTING_LINE));
	}
	/**
	 * Test the {@link Line#contains(Line)} method/
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#contains(Line)} method
	 * returns <code>true</code> when provided a {@link Line} which is
	 * intersecting with the {@link Line} object.
	 * </p>
	 */
	@Test
	public final void testContains_LineExists()
	{
		assertTrue(MOCK_LINE_A.contains(MOCK_LINE_B));
	}
	/**
	 * Test the {@link Line#contains(Station)} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#contains(Station)} throws a
	 * {@link NullPointerException} when provided a <code>null</code>
	 * argument.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testContains_StationNullArg()
	{
		final Station station = null;
		MOCK_LINE_A.contains(station);
	}
	/**
	 * Test the {@link Line#contains(Station)} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#contains(Station)} returns
	 * <code>false</code> when providing a {@link Station} object which does
	 * not exist in the {@link Line}.
	 * </p>
	 */
	@Test
	public final void testContains_StationsNotExisting()
	{
		assertFalse(MOCK_LINE_A.contains(STATION_C));
	}
	/**
	 * Test the {@link Line#contains(Station)} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#contains(Station)} returns
	 * <code>true</code> when provided a {@link Station} object which exists
	 * in the {@link Line} object.
	 * </p>
	 */
	@Test
	public final void testContains_StationExists()
	{
		assertTrue(MOCK_LINE_A.contains(STATION_A));
	}
	/**
	 * Test the {@link Line#getName()} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#getName()} method returns a
	 * {@link String} which is not equal to a {@link String} which was not used
	 * to construct the {@link Line} object.
	 * </p>
	 */
	@Test
	public final void testGetName_NotEqualString()
	{
		assertNotEquals(NON_EXISTING_LINE_NAME, MOCK_LINE_A.getName());
	}
	/**
	 * Test the {@link Line#getName()} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#getName()} method returns a
	 * {@link String} which is equal to a {@link String} which was used to
	 * construct the {@link Line} object.
	 * </p>
	 */
	@Test
	public final void testGetName_EqualString()
	{
		assertEquals(MOCK_LINE_NAME_A, MOCK_LINE_A.getName());
	}
	/**
	 * Test the {@link Line#firstTerminal()} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#firstTerminal()} returns the
	 * {@link Station} object which was the first {@link Station} to be
	 * inserted.
	 * </p>
	 * 
	 * <p>
	 * This method expects a {@link LinkedHashSet} to be used as the
	 * {@link Set}, using any other {@link Set} sublclass means the order of
	 * insertion cannot be predicted.
	 * </p>
	 */
	@Test
	public final void testFirstTerminal()
	{
		assertEquals(STATION_A, MOCK_LINE_A.firstTerminal());
	}
	/**
	 * Test the {@link Line#lastTerminal()} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#lasTerminal()} returns the
	 * {@link Station} object which was the last {@link Station} to be
	 * inserted.
	 * </p>
	 * 
	 * <p>
	 * This method expects a {@link LinkedHashSet} to be used as the
	 * {@link Set}, using any other {@link Set} sublclass means the order of
	 * insertion cannot be predicted.
	 * </p>
	 */
	@Test
	public final void testLastTerminal()
	{
		assertEquals(STATION_B, MOCK_LINE_A.lastTerminal());
	}
	/**
	 * Test the {@link Line#getStations()} methods.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#getStations()} method returns
	 * a {@link Set} which is empty.
	 * </p>
	 */
	@Test
	public final void testGetStations_NotEmpty()
	{
		assertFalse(MOCK_LINE_A.getStations().isEmpty());
	}
	/**
	 * Test the {@link Line#getStations()} methods.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#getStations()} method
	 * returns a {@link Set} which is contains a {@link Station} which was used
	 * to construct the {@link Line} object.
	 * </p>
	 */
	@Test
	public final void testGetStations_Contains()
	{
		assertTrue(MOCK_LINE_A.getStations().contains(STATION_A));
	}
	/**
	 * Test the {@link Line#getAdjacentLineNames()} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#getAdjacentLineNames()}
	 * method returns a {@link Set} which is empty, when a {@link Line} has no
	 * adjacent lines.
	 * </p>
	 */
	@Test
	public final void testGetAdjacentLines_Empty()
	{
		assertTrue(NON_EXISTING_LINE.getAdjacentLineNames().isEmpty());
	}
	/**
	 * Test the {@link Line#getAdjacentLineNames()} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#getAdjacentLineNames()}
	 * method returns a {@link Set} which is not empty, when a {@link Line} has
	 * at least one adjacent {@link Line}.
	 * </p>
	 */
	@Test
	public final void testGetAdjacent_NotEmpty()
	{
		assertFalse(MOCK_LINE_A.getAdjacentLineNames().isEmpty());
	}
	/**
	 * Test the {@link Line#getAdjacentLineNames()} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#getAdjacentLineNames()}
	 * method returns a {@link Set} which contains a {@link String}
	 * representing a {@link Line} object which is adjacent to another
	 * {@link Line} object.
	 * </p>
	 */
	@Test
	public final void testGetAdjacent_Contains()
	{
		assertTrue(
				MOCK_LINE_A.getAdjacentLineNames().contains(MOCK_LINE_NAME_B));
	}
	/**
	 * Test the {@link Line#getIntersectingStationsOf(String)} method.
	 * 
	 * <p>
	 * This test will only pass if
	 * {@link Line#getIntersectingStationsOf(String)} method throws a
	 * {@link NullPointerException} when provided a <code>null</code> argument.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testGetIntersectionOf_NullArg()
	{
		MOCK_LINE_A.getIntersectingStationsOf(null);
	}
	/**
	 * Test the {@link Line#getIntersectingStationsOf(String)} method.
	 * 
	 * <p>
	 * This test will only pass if
	 * {@link Line#getIntersectingStationsOf(String)} method returns a
	 * {@link Set} which is empty, when finding the intersection of a
	 * {@link Line} which is not adjacent to the {@link Line}.
	 * </p>
	 */
	@Test
	public final void testGetIntersectionOf_NotExisting()
	{
		assertTrue(
				MOCK_LINE_A.getIntersectingStationsOf(
						NON_EXISTING_LINE_NAME).isEmpty());
	}
	/**
	 * Test the {@link Line#getIntersectingStationsOf(String)} method.
	 * 
	 * <p>
	 * This test will only pass if
	 * {@link Line#getIntersectingStationsOf(String)} method returns a
	 * {@link Set} of {@link Station} objects whichn is not empty when provided
	 * a {@link Line} which is adjacent to the {@link Line}.
	 * </p>
	 */
	@Test
	public final void testGetIntersectionOf_Exists()
	{
		assertFalse(
				MOCK_LINE_A.getIntersectingStationsOf(
						MOCK_LINE_NAME_B).isEmpty());
	}
	/**
	 * Test the {@link Line#equals(Object)} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#equals(Object)} method
	 * throws a {@link NullPointerException} when provided a <code>null</code>
	 * argument.
	 * </p>
	 */
	@Test
	public final void testEquals_NullArg()
	{
		assertFalse(MOCK_LINE_A.equals(null));
	}
	/**
	 * Test the {@link Line#equals(Object)} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#equals(Object)} method
	 * returns <code>false</code> when provided an {@link Object} which is not
	 * a {@link Line}.
	 * </p>
	 */
	@Test
	public final void testEquals_DifferentType()
	{
		assertFalse(MOCK_LINE_A.equals(new Object()));
	}
	/**
	 * Test the {@link Line#equals(Object)} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#equals(Object)} method
	 * returns <code>false</code> for {@link Line} objects which contain
	 * different stations, intersections and has the same name.
	 * </p>
	 */
	@Test
	public final void testEquals_NotEqual()
	{
		assertNotEquals(MOCK_LINE_B, MOCK_LINE_A);
	}
	/**
	 * Test the {@link Line#equals(Object)} method.
	 * 
	 * <p>
	 * This test will only pass if the {@link Line#equals(Object)} method
	 * returns <code>true</code> when comparing {@link Line} objects which
	 * have been provided the same constructor arguments.
	 * </p>
	 */
	@Test
	public final void testEquals_Equal()
	{
		assertEquals(MOCK_LINE_A, setupMockLine());
	}
	/**
	 * Test the {@link Line#hashCode()} method.
	 * 
	 * <p>
	 * This test will pass if the {@link Line#hashCode()} method returns the
	 * different <code>int</code> hash value for object which do not equal.
	 * </p>
	 * 
	 * <p>
	 * The hash value may be equal but is more likely not to be.
	 * </P>
	 */
	@Test
	public final void testHashCode_NotEqual()
	{
		assertNotEquals(MOCK_LINE_A.hashCode(), MOCK_LINE_B.hashCode());
	}
	/**
	 * Test the {@link Line#hashCode()} method.
	 * 
	 * <p>
	 * This test will pass if the {@link Line#hashCode()} method returns the
	 * same <code>int</code> hash value for object which equal.
	 * </p>
	 */
	@Test
	public final void testHashCode_Equal()
	{
		assertEquals(MOCK_LINE_A, setupMockLine());
	}
	/**
	 * Test the {@link Line#toString()} method.
	 * 
	 * <p>
	 * This test will only pass if {@link Line#toString()} method returns a
	 * {@link String} which does not contain the {@link String} representation
	 * of a {@link Station} which does not exist in the {@link Line}.
	 * </p>
	 */
	@Test
	public final void testToString_Empty()
	{
		assertFalse(NON_EXISTING_LINE.toString().contains(STATION_NAME_A));
	}
	/**
	 * Test the {@link Line#toString()} method.
	 * 
	 * <p>
	 * This test will only pass if {@link Line#toString()} method returns a
	 * {@link String} which contains one of the {@link Station} objects
	 * {@link String} representations, when the {@link Line} object contains
	 * at least one {@link Station}.
	 * </p>
	 */
	@Test
	public final void testToString_NotEmpty()
	{
		assertTrue(MOCK_LINE_A.toString().contains(STATION_NAME_A));
	}
	//=========================================================================
	//Methods.
	/**
	 * A helper method for generating a {@link Line} object which is equal
	 * to {@link #MOCK_LINE_A} but does not have reference equality.
	 * 
	 * @return The {@link Line} which is equal to {@link #MOCK_LINE_A}.
	 */
	private static Line setupMockLine()
	{
		final Map<String, Set<Station>> intersections = new HashMap<>();
		final Set<Station> stations = new LinkedHashSet<>();
		intersections.put(MOCK_LINE_NAME_B, new HashSet<>());
		intersections.get(MOCK_LINE_NAME_B).add(STATION_B);
		stations.add(STATION_A);
		stations.add(STATION_B);
		return new Line(MOCK_LINE_NAME_A, stations, intersections);
	}
}
