package underground;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Performs a set of predefined tests on the the {@link Metro}.
 * 
 * @author 159029448 Joshua_Eddy
 *
 */
class Test_Metro {

	/**
	 * Holds the sample {@link Metro} and other data that will be used for each test
	 * in this {@link Test_Metro}.
	 */
	private Sample data;

	/**
	 * Re constructs the {@link Test_Metro#data} ensuring that all the test run with
	 * the same data set.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		data = new Sample();
	}

	/**
	 * Tests that {@link Metro#outputAllStations()} returns the correct string when
	 * the {@link Metro} is constructed with the {@link SampleData}.
	 */
	@Test
	void test_outputAllStations() {

		String output = data.metro.outputAllStations();

		StringBuilder trueOutput = new StringBuilder();
		trueOutput.append(data.a.getName());
		trueOutput.append("\n");
		trueOutput.append(data.b.getName());
		trueOutput.append("\n");
		trueOutput.append(data.c.getName());
		trueOutput.append("\n");
		trueOutput.append(data.d.getName());
		trueOutput.append("\n");
		trueOutput.append(data.e.getName());
		trueOutput.append("\n");
		trueOutput.append(data.f.getName());
		trueOutput.append("\n");
		trueOutput.append(data.g.getName());
		trueOutput.append("\n");
		trueOutput.append(data.h.getName());
		trueOutput.append("\n");

		assertTrue(trueOutput.toString().equals(output));

	}

	/**
	 * Holds all the fields that will be used for {@link Test_Metro}.
	 * 
	 * @author 159029448 Joshua_Eddy
	 *
	 */
	private final class Sample {

		public final Station a;
		public final Station b;
		public final Station c;
		public final Station d;
		public final Station e;
		public final Station f;
		public final Station g;
		public final Station h;

		public final Metro metro;
		public final Line line1;
		public final Line line2;

		public Sample() {

			// All the stations in the metro
			a = new Station("a");
			b = new Station("b");
			c = new Station("c");
			d = new Station("d");
			e = new Station("e");
			f = new Station("f");
			g = new Station("g");
			h = new Station("h");

			// Holds all the stations on line 1
			Set<Station> line1_Stations = new LinkedHashSet<>();
			line1_Stations.add(a);
			line1_Stations.add(b);
			line1_Stations.add(c);
			line1_Stations.add(d);

			// Holds all the stations on line 2
			Set<Station> line2_Stations = new LinkedHashSet<>();
			line2_Stations.add(e);
			line2_Stations.add(f);
			line2_Stations.add(g);
			line2_Stations.add(h);

			// Holds the line names
			String line1_Name = "line1";
			String line2_Name = "line2";

			// The stations that are shared between both lines
			Set<Station> stationsInCommon = new HashSet<>();
			stationsInCommon.add(d);

			// The map of lines that are adjacent to line1 to the stations they share with
			// line1
			Map<String, Set<Station>> adj1 = new HashMap<>();
			adj1.put(line2_Name, stationsInCommon);

			// The map of lines that are adjacent to line2 to the stations they share with
			// line2
			Map<String, Set<Station>> adj2 = new HashMap<>();
			adj2.put(line1_Name, stationsInCommon);

			// The lines of the Metro
			line1 = new Line(line1_Name, line1_Stations, adj1);
			line2 = new Line(line2_Name, line2_Stations, adj2);

			// The map of the line names to thrie line object.
			Map<String, Line> lines = new HashMap<>();
			lines.put(line1_Name, line1);
			lines.put(line2_Name, line2);

			metro = new Metro(lines);

		}

	}
}
