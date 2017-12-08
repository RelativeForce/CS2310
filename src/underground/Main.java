package underground;

public class Main {

	public Main() {

	}

	public static void main(String...args){
		
		
		final MetroBuilder builder = new MetroBuilder();
		final String[] strs = TextFileReader.scanFile("Resources", "MTRsystem_partial.csv");
		for(final String s: strs)
		{
			final String[] str = s.split(",");
			for(int i = 1; i < str.length; ++i)
				builder.addStation(str[i], str[0]);
		}
		final TUI tui = new TUI(new RequestHandler(builder.build()));
	}
}
