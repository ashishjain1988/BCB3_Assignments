package Assignment1.BCB569;

public class AminoAcid {

	String rSequence;
	String rChain;
	String rName;
	Vector backboneN;
	Vector backboneCA;
	Vector backboneC;
	
	
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
	public Vector getBackboneN() {
		return backboneN;
	}
	public void setBackboneN(Vector backboneN) {
		this.backboneN = backboneN;
	}
	public Vector getBackboneCA() {
		return backboneCA;
	}
	public void setBackboneCA(Vector backboneCA) {
		this.backboneCA = backboneCA;
	}
	public Vector getBackboneC() {
		return backboneC;
	}
	public void setBackboneC(Vector backboneC) {
		this.backboneC = backboneC;
	}
	public AminoAcid(String rSequence, String rChain, String rName, Vector backboneN, Vector backboneCA,
			Vector backboneC) {
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
