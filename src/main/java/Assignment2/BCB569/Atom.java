package Assignment2.BCB569;

import Assignment1.BCB569.Vector;

/**
 * @author Ashish Jain
 * 
 * The Class Atom.
 */
public class Atom {

	/** The atom name. */
	String atomName;
	
	/** The position. */
	Vector position;
	
	/** The atomic radii. */
	Double atomicRadii;
	
	/** The atomic number. */
	Integer atomicNumber;
	
	/** The area. */
	Double area;

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Vector getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Vector position) {
		this.position = position;
	}

	/**
	 * Gets the atomic radii.
	 *
	 * @return the atomic radii
	 */
	public Double getAtomicRadii() {
		return atomicRadii;
	}

	/**
	 * Sets the atomic radii.
	 *
	 * @param atomicRadii the new atomic radii
	 */
	public void setAtomicRadii(Double atomicRadii) {
		this.atomicRadii = atomicRadii;
	}

	/**
	 * Gets the atomic number.
	 *
	 * @return the atomic number
	 */
	public Integer getAtomicNumber() {
		return atomicNumber;
	}

	/**
	 * Sets the atomic number.
	 *
	 * @param atomicNumber the new atomic number
	 */
	public void setAtomicNumber(Integer atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	
	/**
	 * Gets the atom name.
	 *
	 * @return the atom name
	 */
	public String getAtomName() {
		return atomName;
	}

	/**
	 * Sets the atom name.
	 *
	 * @param atomName the new atom name
	 */
	public void setAtomName(String atomName) {
		this.atomName = atomName;
	}

	/**
	 * Gets the area.
	 *
	 * @return the area
	 */
	public Double getArea() {
		return area;
	}

	/**
	 * Sets the area.
	 *
	 * @param area the new area
	 */
	public void setArea(Double area) {
		this.area = area;
	}

	/**
	 * Instantiates a new atom.
	 *
	 * @param v the v
	 * @param atomicRadii the atomic radii
	 * @param atomicNumber the atomic number
	 */
	public Atom(Vector v, Double atomicRadii, Integer atomicNumber) {
		super();
		this.position = v;
		this.atomicRadii = atomicRadii;
		this.atomicNumber = atomicNumber;
	}

	/**
	 * Instantiates a new atom.
	 *
	 * @param atomName the atom name
	 * @param position the position
	 * @param atomicRadii the atomic radii
	 * @param atomicNumber the atomic number
	 * @param area the area
	 */
	public Atom(String atomName, Vector position, Double atomicRadii, Integer atomicNumber, Double area) {
		super();
		this.atomName = atomName;
		this.position = position;
		this.atomicRadii = atomicRadii;
		this.atomicNumber = atomicNumber;
		this.area = area;
	}

	/**
	 * Instantiates a new atom.
	 */
	public Atom() {
		super();
	}
}
