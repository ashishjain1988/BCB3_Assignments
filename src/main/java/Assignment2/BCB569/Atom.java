package Assignment2.BCB569;

import Assignment1.BCB569.Vector;

public class Atom {

	String atomName;
	
	Vector position;
	
	Double atomicRadii;
	
	Integer atomicNumber;
	
	Double area;

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public Double getAtomicRadii() {
		return atomicRadii;
	}

	public void setAtomicRadii(Double atomicRadii) {
		this.atomicRadii = atomicRadii;
	}

	public Integer getAtomicNumber() {
		return atomicNumber;
	}

	public void setAtomicNumber(Integer atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	
	public String getAtomName() {
		return atomName;
	}

	public void setAtomName(String atomName) {
		this.atomName = atomName;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Atom(Vector v, Double atomicRadii, Integer atomicNumber) {
		super();
		this.position = v;
		this.atomicRadii = atomicRadii;
		this.atomicNumber = atomicNumber;
	}

	public Atom(String atomName, Vector position, Double atomicRadii, Integer atomicNumber, Double area) {
		super();
		this.atomName = atomName;
		this.position = position;
		this.atomicRadii = atomicRadii;
		this.atomicNumber = atomicNumber;
		this.area = area;
	}

	public Atom() {
		super();
		// TODO Auto-generated constructor stub
	}
}
