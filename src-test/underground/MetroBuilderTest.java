package underground;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * The {@code MetroBuilderTest} is a test class for the {@link MetroBuilder}
 * class using the JUnit testing framework.
 * 
 * <p>
 * {@code MetroBuilderTest} will test the constructors and methods of the
 * {@link MetroBuilder} class. A constructor or method in the {@link Line}
 * class may have multiple test cases to test different scenarios.
 * </p>
 * 
 * <p>
 * The tests are formatted as: "public final void test
 * {@code <nameOfTest>} [_TestCase]". Test cases are denoted using the
 * {@code @Test} annotation.
 * </p>
 * 
 * @author 	159014260 John Berg
 * @since 	09/12/2017
 * @version 14/12/2017
 */
public class MetroBuilderTest
{
	//=========================================================================
	//Static fields.
	/**
	 * The {@link String} which represent the name of a {@link Station} in the
	 * {@link MetroBuilder}.
	 */
	private static final String STATION_NAME_A = "a";
	/**
	 * The {@link String} which represent the name of a {@link Line} in the
	 * {@link MetroBuilder}.
	 */
	private static final String LINE_NAME_A = "A";
	/**
	 * The {@link String} which represent the name of a {@link Line} in the
	 * {@link MetroBuilder}.
	 */
	private static final String LINE_NAME_B = "B";
	//=========================================================================
	//Fields.
	/**
	 * The {@link MetroBuilder} object used in the test cases.
	 */
	private MetroBuilder mockMetroBuilder;
	//=========================================================================
	//Before.
	/**
	 * Setup the {@link #mockMetroBuilder} object before running each test
	 * case.
	 * 
	 * <p>
	 * Setting up the {@link #mockMetroBuilder} involves constructing a new
	 * {@link MetroBuilder}.
	 * </p>
	 */
	@Before
	public final void setup()
	{
		mockMetroBuilder = new MetroBuilder();
	}
	//=========================================================================
	//Tests.
	/**
	 * Test the {@link MetroBuilder#addLine(String)} method.
	 * 
	 * <p>
	 * This test will only pass if {@link MetroBuilder#addLine(String)} method
	 * throws a {@link NullPointerException} when provided a <code>null</code>
	 * argument.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testAddLine_NullArg()
	{
		mockMetroBuilder.addLine(null);
	}
	/**
	 * Test the {@link MetroBuilder#addLine(String)} method.
	 * 
	 * <p>
	 * This test will only pass if {@link MetroBuilder#addLine(String)} method
	 * allows the {@link MetroBuilder} object to produce a {@link Metro} which
	 * contains the {@link Line} represented by the {@link string} argument.
	 * </p>
	 */
	@Test
	public final void testAddLine_Sucess()
	{
		mockMetroBuilder.addLine(LINE_NAME_A);
		mockMetroBuilder.addStation(STATION_NAME_A, LINE_NAME_A);
		assertFalse(
				mockMetroBuilder.build()
				.listStationsOnLine(LINE_NAME_A)
				.isEmpty());
	}
	/**
	 * Test the {@link MetroBuilder#addStation(String, String)} method.
	 * 
	 * <p>
	 * This test will only pass if the 
	 * {@link MetroBuilder#addStation(String, String)} method throws a
	 * {@link NullPointerException} when provided a <code>null</code> value as
	 * the first argument.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testAddStation_NullArg0()
	{
		mockMetroBuilder.addStation(null, LINE_NAME_A);
	}
	/**
	 * Test the {@link MetroBuilder#addStation(String, String)} method.
	 * 
	 * <p>
	 * This test will only pass if the 
	 * {@link MetroBuilder#addStation(String, String)} method throws a
	 * {@link NullPointerException} when provided a <code>null</code> value as
	 * the second argument.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testAddStation_NullArg1()
	{
		mockMetroBuilder.addStation(STATION_NAME_A, null);
	}
	/**
	 * Test the {@link MetroBuilder#addStation(String, String)} method.
	 * 
	 * <p>
	 * This test will only pass if the 
	 * {@link MetroBuilder#addStation(String, String)} method 
	 * </p>
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testBuild_NoStations()
	{
		mockMetroBuilder.addLine(LINE_NAME_A);
		mockMetroBuilder.build();
	}
	/**
	 * Test the {@link MetroBuilder#addStation(String, String)} method.
	 * 
	 * <p>
	 * This test will only pass if the 
	 * {@link MetroBuilder#addStation(String, String)} method 
	 * </p>
	 */
	@Test
	public final void testAddStation_StationAdded()
	{
		mockMetroBuilder.addStation(STATION_NAME_A, LINE_NAME_A);
		assertTrue(mockMetroBuilder.build().listStationsOnLine(LINE_NAME_A)
				.contains(new Station(STATION_NAME_A)));
	}
	/**
	 * Test the {@link MetroBuilder#addStation(String, String)} method.
	 * 
	 * <p>
	 * This test will only pass if the 
	 * {@link MetroBuilder#addStation(String, String)} method produces a
	 * {@link Metro} object which contains no {@link Line} objects.
	 * </p>
	 */
	@Test(expected = NullPointerException.class)
	public final void testBuild_NoLines()
	{
		mockMetroBuilder.build().listStationsOnLine(LINE_NAME_A);
	}
	/**
	 * Test the {@link MetroBuilder#addStation(String, String)} method.
	 * 
	 * <p>
	 * This test will only pass if the 
	 * {@link MetroBuilder#addStation(String, String)} method produces a
	 * {@link Metro} object which contains the {@link Line} corresponding to
	 * the {@link String} which represent it int he {@link #mockMetroBuilder}
	 * object.
	 * </p>
	 */
	@Test
	public final void testBuild_hasLines()
	{
		mockMetroBuilder.addLine(LINE_NAME_A);
		mockMetroBuilder.addStation(STATION_NAME_A, LINE_NAME_A);
		assertTrue(mockMetroBuilder.build()
				.listStationsOnLine(LINE_NAME_A)
				.contains(new Station(STATION_NAME_A)));
	}
	/**
	 * Test the {@link MetroBuilder#addStation(String, String)} method.
	 * 
	 * <p>
	 * This test will only pass if the 
	 * {@link MetroBuilder#addStation(String, String)} method produces a
	 * {@link Metro} object which contains a {@link Line} object which has an
	 * adjacent {@link Line}.
	 * </p>
	 */
	@Test
	public final void testBuild_AdjacentLines()
	{
		mockMetroBuilder.addLine(LINE_NAME_A);
		mockMetroBuilder.addLine(LINE_NAME_B);
		mockMetroBuilder.addStation(STATION_NAME_A, LINE_NAME_A);
		mockMetroBuilder.addStation(STATION_NAME_A, LINE_NAME_B);
		assertTrue(mockMetroBuilder.build()
				.getAdjacentLines(LINE_NAME_A)
				.stream()
				.map(Line::getName)
				.anyMatch(LINE_NAME_B::equals));
	}
}
