package Assignment1.BCB569;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class AminoAcid {

	String rSequence;
	String rChain;
	String rName;
	Vector3D backboneN;
	Vector3D backboneCA;
	Vector3D backboneC;
	
	
	public String getrSequence() {
		return rSequence;
	}
	public void setrSequence(String rSequence) {
		this.rSequence = rSequence;
	}
	public String getrChain() {
		return rChain;
	}
	public void setrChain(String rChain) {
		this.rChain = rChain;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public Vector3D getBackboneN() {
		return backboneN;
	}
	public void setBackboneN(Vector3D backboneN) {
		this.backboneN = backboneN;
	}
	public Vector3D getBackboneCA() {
		return backboneCA;
	}
	public void setBackboneCA(Vector3D backboneCA) {
		this.backboneCA = backboneCA;
	}
	public Vector3D getBackboneC() {
		return backboneC;
	}
	public void setBackboneC(Vector3D backboneC) {
		this.backboneC = backboneC;
	}
	public AminoAcid(String rSequence, String rChain, String rName, Vector3D backboneN, Vector3D backboneCA,
			Vector3D backboneC) {
		super();
		this.rSequence = rSequence;
		this.rChain = rChain;
		this.rName = rName;
		this.backboneN = backboneN;
		this.backboneCA = backboneCA;
		this.backboneC = backboneC;
	}
	public AminoAcid() {
		super();
	}
}
