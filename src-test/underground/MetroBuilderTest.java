package underground;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author 	159014260 John Berg
 * @since 	09/12/2017
 * @version 09/12/2017
 */
public class MetroBuilderTest
{
	//=========================================================================
	//Fields.
	private MetroBuilder mockMetroBuilder;
	//=========================================================================
	//Before.
	@Before
	public final void setup()
	{
		mockMetroBuilder = new MetroBuilder();
	}
	//=========================================================================
	//Tests.
	@Test(expected = NullPointerException.class)
	public final void testAddLine_NullArg()
	{
		mockMetroBuilder.addLine(null);
	}
	@Test(expected = IllegalArgumentException.class)
	public final void testAddLine_NoStations()
	{
		mockMetroBuilder.addLine("A");
		mockMetroBuilder.build();
	}
	@Test
	public final void testAddLine_Sucess()
	{
		mockMetroBuilder.addLine("A");
		mockMetroBuilder.addStation("a", "A");
		assertFalse(
				mockMetroBuilder.build().listStationsOnLine("A").isEmpty());
	}
	@Test(expected = NullPointerException.class)
	public final void testAddStation_NullArg0()
	{
		mockMetroBuilder.addStation(null, "A");
	}
	@Test(expected = NullPointerException.class)
	public final void testAddStation_NullArg1()
	{
		mockMetroBuilder.addStation("a", null);
	}
	@Test
	public final void testAddStation_StationAdded()
	{
		mockMetroBuilder.addStation("a", "A");
		assertTrue(mockMetroBuilder.build().listStationsOnLine("A")
				.contains(new Station("a")));
	}
	@Test
	public final void testBuild_NoLines()
	{
		//assertTrue(mockMetroBuilder.build().li)
	}
}
