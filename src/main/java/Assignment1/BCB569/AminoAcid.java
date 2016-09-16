package Assignment1.BCB569;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author Ashish Jain
 *
 */
public class AminoAcid implements Comparable<AminoAcid>{

	String rSequence;
	String rChain;
	String rName;
	String elementSymbol;
	Vector backboneN;
	Vector backboneCA;
	Vector backboneC;
	Map<String, Vector> atoms;
	
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
	
	public Map<String, Vector> getAtoms() {
		return atoms;
	}
	public void setAtoms(Map<String, Vector> atoms) {
		this.atoms = atoms;
	}

	public String getElementSymbol() {
		return elementSymbol;
	}
	public void setElementSymbol(String elementSymbol) {
		this.elementSymbol = elementSymbol;
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

	public AminoAcid(String rSequence, String rChain, String rName, String elementSymbol, Vector backboneN,
			Vector backboneCA, Vector backboneC, Map<String, Vector> atoms) {
		super();
		this.rSequence = rSequence;
		this.rChain = rChain;
		this.rName = rName;
		this.elementSymbol = elementSymbol;
		this.backboneN = backboneN;
		this.backboneCA = backboneCA;
		this.backboneC = backboneC;
		this.atoms = atoms;
	}
	public int compareTo(AminoAcid o) {
		if(rSequence.equals(o.getrSequence()) && rChain.equals(o.getrChain()) && rName.equals(o.getrName()))
		{
			return 0;
		}else
		{
			return 1;
		}
	}
	@Override
	public boolean equals(Object obj) {
		AminoAcid o = (AminoAcid) obj;
		if(rSequence.equals(o.getrSequence()) && rChain.equals(o.getrChain()) && rName.equals(o.getrName()))
		{
			return true;
		}else
		{
			return false;
		}
	}
	@Override
	public String toString() {
		
		return rSequence +" "+rName+" "+rChain +" "+backboneC+" "+backboneCA+" "+backboneN;
				
	}
	
	public static List<Object> getMinimumDistanceBetweenAtomsOfResidues(AminoAcid aa1,AminoAcid aa2, boolean isSame)
    {
    	Double distance = 1000d;
    	String atomsName = "";
    	List<Object> result =  new ArrayList<Object>();
		Map<String, Vector> atoms1 = aa1.getAtoms();
		Map<String, Vector> atoms2 = aa2.getAtoms();
		for(Entry<String, Vector> entry1 : atoms1.entrySet())
		{
			for(Entry<String, Vector> entry2 : atoms2.entrySet())
			{
				if((!entry1.getKey().equals(entry2.getKey()) || isSame == false) && (!entry1.getKey().startsWith("H") && !entry2.getKey().startsWith("H")))
				{
					Double dist = entry1.getValue().distance(entry2.getValue());
					if(dist < distance)
					{
						distance = dist;
						atomsName = aa1.getrName()+"-"+entry1.getKey()+":"+aa2.getrName()+"-"+entry2.getKey();
					}
				}
			}
		}
    	result.add(distance);
    	result.add(atomsName);
    	return result;
    }
	
	public static double phiAngle(AminoAcid prev, AminoAcid current)
    {
    	return Vector.getTorsionalAngle(prev.getBackboneC(), current.getBackboneN(), current.getBackboneCA(), current.getBackboneC());
    }
    
    public static double psiAngle(AminoAcid current, AminoAcid next)
    {
    	return Vector.getTorsionalAngle(current.getBackboneN(), current.getBackboneCA(), current.getBackboneC(), next.getBackboneN());
    }
    
    public static double omegaAngle(AminoAcid current, AminoAcid next)
    {
    	return Vector.getTorsionalAngle(current.getBackboneCA(), current.getBackboneN(), next.getBackboneC(), next.getBackboneCA());
    }
	
	
}
