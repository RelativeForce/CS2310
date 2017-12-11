package underground;

/**
 * 
 * The {@code Main} class is the class which contains the entry point for this
 * program.
 * 
 * <p>
 * The entry point of this program is the {@link #main(String...)} method.
 * </p>
 * 
 * @author 	159014260 John Berg
 * @author 	159029448 Joshua Eddy
 * @author	159102257 David Wightman
 * @since 	29/10/2017
 * @version 11/12/2017
 */
public class Main {
	/**
	 * The main method of the program.
	 * 
	 * <p>
	 * Running the main method will start the program.
	 * </p>
	 * 
	 * <p>
	 * When run, the MTRsystem_partial.csv file will be read to construct a
	 * {@link Metro} object from the contents of the file, then a {@link TUI}
	 * and a {@link RequestHandler} will also be set up allow for interaction
	 * which he {@link Metro} representing the read file.
	 * </p>
	 * 
	 * @param args The {@link string} objects passed as arguments to the
	 * 		program.
	 */
	public static void main(String...args){
		
		//Construct a Metro using a MetroBuilder.
		final MetroBuilder builder = new MetroBuilder();
		
		/*
		 * Read the MTRsystem_partial.csv file line by line and iterate through
		 * each line.
		 */
		for(final String s: TextFileReader.scanFile(
				"Resources",
				"MTRsystem_partial.csv"))
		{
			//Read the comma separated values into the builder.
			final String[] str = s.split(",");
			for(int i = 1; i < str.length; ++i)
				builder.addStation(str[i], str[0]);
		}
		
		//Setup the TUI, controller and build the Metro from the builder.
		final TUI tui = new TUI(new RequestHandler(builder.build()));
	}
}
