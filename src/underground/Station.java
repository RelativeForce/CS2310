package underground;

/**
 * The {@code Station} class is a class which represents a station inside an
 * underground.
 * 
 * <p>
 * The {@code Station} class is a wrapper around a {@link String} object, the
 * {@code Station} class exist for better semantics when modelling an
 * underground system.
 * </p>
 * 
 * @author 	nnnnnnnnn Joshua Eddy 
 * @author 	159014260 John Berg
 * @since 	13/11/2017
 * @version 22/11/2017
 */
public class Station {
	//=========================================================================
	//Fields.
	/**
	 * The {@link String} which represents the name of<code>this</code>
	 * {@code Station}.
	 */
	private String name;
	//=========================================================================
	//Constructors.
	/**
	 * Construct a {@code Station} with a specified name.
	 * 
	 * @param name The {@link String} representing the name of the created
	 * 			{@code Station}.
	 */
	public Station(final String name) {
		this.name = name;
	}
	//=========================================================================
	//Methods.
	/**
	 * Get the name of <code>this</code> {@code Station}.
	 * 
	 * @return The name of <code>this</code> {@code Station}.
	 */
	public String getName() {
		return name;
	}
	//=========================================================================
	//Overriden methods.
	/**
	 * Compare <code>this</code> {@code Station} with another {@link Object}
	 * the check if the equal.
	 * 
	 * <p>
	 * Another object will equal <code>this</code> if and only if it is and
	 * instance of {@code Station}, and the name of that {@code Station} is
	 * equal to the name of <code>this</code>.
	 * </p>
	 * 
	 * @param o The object to compare <code>this</code> against.
	 * @return <code>true</code> if <code>o</code> is an instance of the
	 * 			{@code Station} class, and the name of <code>this</code>
	 * 			and <code>o</code> equal.
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Station) {
			return ((Station) obj).name.equals(this.name);
		}

		return false;
	}
	/**
	 * Get the hash code of <code>this</code> {@code Station}.
	 * 
	 * <p>
	 * The hashCode of <code>this</code> is the hash code of the name which
	 * is generated from the {@link String} which represents the name of
	 * <code>this</code> {@code Station}.
	 * </p>
	 * 
	 * @return The hash code of <code>this</code> {@code Station}.
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	/**
	 * Get the {@link String} representation of <code>this</code>
	 * {@code Station}.
	 * 
	 * @return The name of <code>this</code> {@code Station}.
	 */
	@Override
	public final String toString()
	{
		return name;
	}
}
