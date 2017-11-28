package underground;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * The {@code StationTest} is a test class for the {@link Station} class using
 * the JUnit testing framework.
 * 
 * <p>
 * {@code StationTest} will test the constructors and methods of the
 * {@link Station} class. A constructor or method in the {@link Station} class
 * may have multiple test cases to test different scenarios.
 * </p>
 * 
 * <p>
 * The tests are formatted as: "public final void test
 * {@code <nameOfTest>} [_TestCase]". Test cases are denoted using the
 * {@code @Test} annotation.
 * </p>
 * 
 * @author 	159014260 John Berg
 * @since	28/11/2017
 * @version	28/11/2017
 */
public class StationTest
{
	//=========================================================================
	//Static fields.
	/**
	 * The {@link String} which represents the name of the {@link Station}
	 * object to be tested.
	 * 
	 * <p>
	 * The default name of a {@link Station}.
	 * <p>
	 */
	private static final String MOCK_STATION_NAME = "Station";
	/**
	 * The {@link String} which represent the name of another {@link Station}
	 * object.
	 * 
	 * <p>
	 * used when a {@link Station} must be compared against a {@link String}
	 * or {@link Station} object not constructed.
	 * </p>
	 * 
	 */
	private static final String OTHER_STATION_NAME = "Not the same Station";
	/**
	 * The {@link Station} which is the main object used for testing.
	 * 
	 * <p>
	 * {@link Station} is immutable and cannot be changed, hence there is no
	 * need to reconstruct this {@link Station}.
	 * </p>
	 */
	private static final Station MOCK_STATION = new Station(MOCK_STATION_NAME);
	//=========================================================================
	//Tests.
	/**
	 * Test the {@link Station#Station(String)} constructor.
	 * 
	 * <p>
	 * This test should succeed only if {@link Station#Station(String)}
	 * throws a {@link NullPointerException} when invoked with a {@link String}
	 * argument of <code>null</code>.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testConstructor_NullArg()
	{
		new Station(null);
	}
	/**
	 * Test the {@link Station#getName()} method.
	 * 
	 * <p>
	 * This test should only pass if the {@link String} returned by
	 * {@link Station#getName()} is equal to the {@link String} used to
	 * construct the {@link Station} object.
	 * </p>
	 */
	@Test
	public final void testGetName_Equal()
	{
		assertEquals(MOCK_STATION_NAME, MOCK_STATION.getName());
	}
	/**
	 * Test the {@link Station#getName()} method.
	 * 
	 * <p>
	 * This test should only pass if the {@link String} returned by the
	 * {@link Station#getName()} method, is not equal to a {@link String}
	 * which was not used to construct the {@link Station} object.
	 * </p>
	 */
	@Test
	public final void testGetName_NotEqual()
	{
		assertNotEquals(OTHER_STATION_NAME, MOCK_STATION.getName());
	}
	/**
	 * Test the {@link Station#equals(Object)} method.
	 * 
	 * <p>
	 * This test will only pass if {@link Station#equals(Object)} returns
	 * <code>false</code> when the {@link Object} argument is
	 * <code>null</code>.
	 * </p>
	 */
	@Test
	public final void testEquals_NullArg()
	{
		assertNotEquals(null, MOCK_STATION);
	}
	/**
	 * Test the {@link Station#equals(Object)} method.
	 * 
	 * <p>
	 * This test should only pass if {@link Station#equals(Object)} returns
	 * <code>false</code> when comparing a {@link Station} to an object which
	 * is not an instance of {@link Station}.
	 * </p>
	 */
	@Test
	public final void testEquals_DifferentType()
	{
		assertNotEquals(new Object(), MOCK_STATION);
	}
	/**
	 * Test the {@link Station#equals(Object)} method.
	 * 
	 * <p>
	 * This test should only pass if {@link Station#equals(Object)} returns
	 * <code>false</code> when comparing tow {@link Station} objects which
	 * were constructed with unequal {@link String} objects.
	 * </p>
	 */
	@Test
	public final void testEquals_NotEqual()
	{
		final Station otherStation = new Station(OTHER_STATION_NAME);
		assertNotEquals(otherStation, MOCK_STATION);
	}
	/**
	 * Test the {@link Station#equals(Object)} method.
	 * 
	 * <p>
	 * This test should only pass if {@link Station#equals(Object)} returns
	 * <code>true</code> when comparing two {@link Station} objects which were
	 * constructed with equal {@link String} objects.
	 * </p>
	 */
	@Test
	public final void testEquals_Equal()
	{
		final Station otherStation = new Station(MOCK_STATION_NAME);
		assertEquals(otherStation, MOCK_STATION);
	}
	/**
	 * Test the {@link Station#hashCode()} method.
	 * 
	 * <p>
	 * This test should only pass if {@link Station#hashCode()} returns an
	 * <code>int</code> which is not equal to the {@link String#hashCode()} of
	 * a {@link String} which was not used to construct the {@link Station}.
	 */
	@Test
	public final void testHashCode_NotEqualString()
	{
		assertNotEquals(OTHER_STATION_NAME.hashCode(), MOCK_STATION.hashCode());
	}
	/**
	 * Test the {@link Station#hashCode()} method.
	 * 
	 * <p>
	 * This test should only pass if {@link Station#hashCode()} returns an
	 * <code>int</code> which is equal to the {@link String#hashCode()} of the
	 * {@link String} which was used to construct the {@link Station}.
	 */
	@Test
	public final void testHashCode_EqualString()
	{
		assertEquals(MOCK_STATION_NAME.hashCode(), MOCK_STATION.hashCode());
	}
	/**
	 * Test the {@link Station#hashCode()} method.
	 * 
	 * <p>
	 * This test will only pass if two {@link Station} objects which were not
	 * constructed with equal {@link String} objects, produce <code>int</code>
	 * values that do not equal using the {@link Station#hashCode()} method.
	 * </p>
	 * 
	 * <p>
	 * Note:
	 * </p>
	 * 
	 * <p>
	 * Unequal object may return the same <code>int</code> value using the
	 * {@link Object#hashCode()} method, the {@link String} objects are
	 * intentionally chosen to so that they do not have the same
	 * hash code.
	 * </p>
	 */
	@Test
	public final void testHashCode_NotEqual()
	{
		final Station OtherStation = new Station(OTHER_STATION_NAME);
		assertNotEquals(OtherStation.hashCode(), MOCK_STATION.hashCode());
	}
	/**
	 * Test the {@link Station#hashCode()} method.
	 * 
	 * <p>
	 * This test will only pass if two {@link Station} objects which were
	 * constructed with equal {@link String} objects, produce <code>int</code>
	 * values that are equal using the {@link Station#hashCode()} method.
	 * </p>
	 */
	@Test
	public final void testHashCode_Equal()
	{
		final Station otherStation = new Station(MOCK_STATION_NAME);
		assertEquals(otherStation.hashCode(), MOCK_STATION.hashCode());
	}
	/**
	 * Test the {@link Station#toString()} method.
	 * 
	 * <p>
	 * This test will pass only if the {@link Station#toString()} returns a
	 * {@link String} which is equal to the {@link String} used to construct
	 * the {@link Station} object.
	 * </p>
	 */
	@Test
	public final void testToString()
	{
		assertEquals(MOCK_STATION_NAME, MOCK_STATION.toString());
	}
}
