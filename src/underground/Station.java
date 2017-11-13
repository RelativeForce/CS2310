package underground;

public class Station {

	private String name;

	public Station(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Station) {
			return ((Station) obj).name.equals(this.name);
		}

		return false;

	}
}
