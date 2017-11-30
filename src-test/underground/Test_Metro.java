package underground;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
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

	@Test
	void test_Pathing() {

		List<Station> path = data.metro.findPath(data.h, data.b);
		path.forEach(station -> System.out.println(station));
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
		public final Station j;
		public final Station k;
		public final Station l;
		public final Station m;
		public final Station n;

		public final Metro metro;
		public final Line line1;
		public final Line line2;
		public final Line line3;
		public final Line line4;

		public Sample() {

			// Line 1 a - b - c - d
			// Line 3 b - j - k - d
			// Line 2 d - e - f - g - h
			// Line 4 j - l - m - n - f

			// All the stations in the Metro
			a = new Station("a");
			b = new Station("b");
			c = new Station("c");
			d = new Station("d");
			e = new Station("e");
			f = new Station("f");
			g = new Station("g");
			h = new Station("h");
			j = new Station("j");
			k = new Station("k");
			l = new Station("l");
			m = new Station("m");
			n = new Station("n");

			// Holds the line names
			String line1_Name = "line1";
			String line2_Name = "line2";
			String line3_Name = "line3";
			String line4_Name = "line4";

			// The stations that are shared between lines 1 and 2
			Set<Station> line1_TO_line2 = new HashSet<>();
			line1_TO_line2.add(d);

			// The stations that are shared between lines 1 and 3
			Set<Station> line1_TO_line3 = new HashSet<>();
			line1_TO_line3.add(b);
			line1_TO_line3.add(d);

			// The stations that are shared between lines 2 and 3
			Set<Station> line2_TO_line3 = new HashSet<>();
			line2_TO_line3.add(d);

			// The stations that are shared between lines 2 and 3
			Set<Station> line3_TO_line4 = new HashSet<>();
			line3_TO_line4.add(j);

			Set<Station> line2_TO_line4 = new HashSet<>();
			line2_TO_line4.add(f);

			// The map of lines that are adjacent to line1 to the stations they share with
			// line1
			Map<String, Set<Station>> adj1 = new HashMap<>();
			adj1.put(line2_Name, line1_TO_line2);
			adj1.put(line3_Name, line1_TO_line3);

			// The map of lines that are adjacent to line2 to the stations they share with
			// line2
			Map<String, Set<Station>> adj2 = new HashMap<>();
			adj2.put(line1_Name, line1_TO_line2);
			adj2.put(line3_Name, line2_TO_line3);
			adj2.put(line4_Name, line2_TO_line4);

			// The map of lines that are adjacent to line3 to the stations they share with
			// line3
			Map<String, Set<Station>> adj3 = new HashMap<>();
			adj3.put(line2_Name, line2_TO_line3);
			adj3.put(line1_Name, line1_TO_line3);
			adj3.put(line4_Name, line3_TO_line4);

			// The map of lines that are adjacent to line3 to the stations they share with
			// line4
			Map<String, Set<Station>> adj4 = new HashMap<>();
			adj4.put(line3_Name, line3_TO_line4);
			adj4.put(line2_Name, line2_TO_line4);

			// The lines of the Metro
			line1 = new Line(line1_Name, line1_Stations(), adj1);
			line2 = new Line(line2_Name, line2_Stations(), adj2);
			line3 = new Line(line3_Name, line3_Stations(), adj3);
			line4 = new Line(line4_Name, line4_Stations(), adj4);

			// The map of the line names to thrie line object.
			Map<String, Line> lines = new HashMap<>();
			lines.put(line1_Name, line1);
			lines.put(line2_Name, line2);
			lines.put(line3_Name, line3);
			lines.put(line4_Name, line4);

			metro = new Metro(lines);

		}

		private Set<Station> line1_Stations() {

			Set<Station> stations = new LinkedHashSet<>();
			stations.add(a);
			stations.add(b);
			stations.add(c);
			stations.add(d);

			return stations;

		}

		private Set<Station> line2_Stations() {

			Set<Station> stations = new LinkedHashSet<>();

			stations.add(d);
			stations.add(e);
			stations.add(f);
			stations.add(g);
			stations.add(h);

			return stations;

		}

		private Set<Station> line3_Stations() {

			Set<Station> stations = new LinkedHashSet<>();

			stations.add(b);
			stations.add(j);
			stations.add(k);
			stations.add(d);

			return stations;

		}

		private Set<Station> line4_Stations() {

			Set<Station> stations = new LinkedHashSet<>();

			stations.add(j);
			stations.add(l);
			stations.add(m);
			stations.add(n);
			stations.add(f);

			return stations;

		}
	}
}
