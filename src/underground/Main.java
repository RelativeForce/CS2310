package underground;

public class Main {

	public Main() {

	}

	public static void main(String...args){
		
		
		final MetroBuilder builder = new MetroBuilder();
		builder.addLine("A");
		builder.addStation("a", "A");
		final TUI tui = new TUI(new RequestHandler(builder.build()));
	}

}
