package Assignment1.BCB569;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Assignment2.BCB569.Atom;

/**
 * The Class AminoAcid.
 *
 * @author Ashish Jain
 */
public class AminoAcid implements Comparable<AminoAcid>{

	/** The r sequence. */
	String rSequence;
	
	/** The r chain. */
	String rChain;
	
	/** The r name. */
	String rName;
	
	/** The element symbol. */
	String elementSymbol;
	
	/** The backbone N. */
	Vector backboneN;
	
	/** The backbone CA. */
	Vector backboneCA;
	
	/** The backbone C. */
	Vector backboneC;
	
	/** The atoms. */
	Map<String, Atom> atoms;
	
	/**
	 * Gets the r sequence.
	 *
	 * @return the r sequence
	 */
	public String getrSequence() {
		return rSequence;
	}
	
	/**
	 * Sets the r sequence.
	 *
	 * @param rSequence the new r sequence
	 */
	public void setrSequence(String rSequence) {
		this.rSequence = rSequence;
	}
	
	/**
	 * Gets the r chain.
	 *
	 * @return the r chain
	 */
	public String getrChain() {
		return rChain;
	}
	
	/**
	 * Sets the r chain.
	 *
	 * @param rChain the new r chain
	 */
	public void setrChain(String rChain) {
		this.rChain = rChain;
	}
	
	/**
	 * Gets the r name.
	 *
	 * @return the r name
	 */
	public String getrName() {
		return rName;
	}
	
	/**
	 * Sets the r name.
	 *
	 * @param rName the new r name
	 */
	public void setrName(String rName) {
		this.rName = rName;
	}
	
	/**
	 * Gets the backbone N.
	 *
	 * @return the backbone N
	 */
	public Vector getBackboneN() {
		return backboneN;
	}
	
	/**
	 * Sets the backbone N.
	 *
	 * @param backboneN the new backbone N
	 */
	public void setBackboneN(Vector backboneN) {
		this.backboneN = backboneN;
	}
	
	/**
	 * Gets the backbone CA.
	 *
	 * @return the backbone CA
	 */
	public Vector getBackboneCA() {
		return backboneCA;
	}
	
	/**
	 * Sets the backbone CA.
	 *
	 * @param backboneCA the new backbone CA
	 */
	public void setBackboneCA(Vector backboneCA) {
		this.backboneCA = backboneCA;
	}
	
	/**
	 * Gets the backbone C.
	 *
	 * @return the backbone C
	 */
	public Vector getBackboneC() {
		return backboneC;
	}
	
	/**
	 * Sets the backbone C.
	 *
	 * @param backboneC the new backbone C
	 */
	public void setBackboneC(Vector backboneC) {
		this.backboneC = backboneC;
	}
	
	/**
	 * Gets the atoms.
	 *
	 * @return the atoms
	 */
	public Map<String, Atom> getAtoms() {
		return atoms;
	}
	
	/**
	 * Sets the atoms.
	 *
	 * @param atoms the atoms
	 */
	public void setAtoms(Map<String, Atom> atoms) {
		this.atoms = atoms;
	}

	/**
	 * Gets the element symbol.
	 *
	 * @return the element symbol
	 */
	public String getElementSymbol() {
		return elementSymbol;
	}
	
	/**
	 * Sets the element symbol.
	 *
	 * @param elementSymbol the new element symbol
	 */
	public void setElementSymbol(String elementSymbol) {
		this.elementSymbol = elementSymbol;
	}
	
	/**
	 * Instantiates a new amino acid.
	 *
	 * @param rSequence the r sequence
	 * @param rChain the r chain
	 * @param rName the r name
	 * @param backboneN the backbone N
	 * @param backboneCA the backbone CA
	 * @param backboneC the backbone C
	 */
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
	
	/**
	 * Instantiates a new amino acid.
	 */
	public AminoAcid() {
		super();
	}

	/**
	 * Instantiates a new amino acid.
	 *
	 * @param rSequence the r sequence
	 * @param rChain the r chain
	 * @param rName the r name
	 * @param elementSymbol the element symbol
	 * @param backboneN the backbone N
	 * @param backboneCA the backbone CA
	 * @param backboneC the backbone C
	 * @param atoms the atoms
	 */
	public AminoAcid(String rSequence, String rChain, String rName, String elementSymbol, Vector backboneN,
			Vector backboneCA, Vector backboneC, Map<String, Atom> atoms) {
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
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(AminoAcid o) {
		if(rSequence.equals(o.getrSequence()) && rChain.equals(o.getrChain()) && rName.equals(o.getrName()))
		{
			return 0;
		}else
		{
			return 1;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return rSequence +" "+rName+" "+rChain +" "+backboneC+" "+backboneCA+" "+backboneN;
				
	}
	
	/**
	 * Gets the minimum distance between atoms of residues.
	 *
	 * @param aa1 the aa 1
	 * @param aa2 the aa 2
	 * @param isSame the is same
	 * @return the minimum distance between atoms of residues
	 */
	public static List<Object> getMinimumDistanceBetweenAtomsOfResidues(AminoAcid aa1,AminoAcid aa2, boolean isSame)
    {
    	Double distance = 1000d;
    	String atomsName = "";
    	List<Object> result =  new ArrayList<Object>();
		Map<String, Atom> atoms1 = aa1.getAtoms();
		Map<String, Atom> atoms2 = aa2.getAtoms();
		for(Entry<String, Atom> entry1 : atoms1.entrySet())
		{
			for(Entry<String, Atom> entry2 : atoms2.entrySet())
			{
				if((!entry1.getKey().equals(entry2.getKey()) || isSame == false) && (!entry1.getKey().startsWith("H") && !entry2.getKey().startsWith("H")))
				{
					Double dist = entry1.getValue().getPosition().distance(entry2.getValue().getPosition());
					if(dist < distance)
					{
						distance = dist;
						atomsName = entry1.getKey()+" of "+aa1.getrName()+" "+aa1.getrSequence()+" and "+entry2.getKey()+" of "+aa2.getrName()+" "+aa2.getrSequence();
					}
				}
			}
		}
    	result.add(distance);
    	result.add(atomsName);
    	return result;
    }
	
	/**
	 * Phi angle.
	 *
	 * @param prev the prev
	 * @param current the current
	 * @return the double
	 */
	public static double phiAngle(AminoAcid prev, AminoAcid current)
    {
    	return Vector.getTorsionalAngle(prev.getBackboneC(), current.getBackboneN(), current.getBackboneCA(), current.getBackboneC());
    }
    
    /**
     * Psi angle.
     *
     * @param current the current
     * @param next the next
     * @return the double
     */
    public static double psiAngle(AminoAcid current, AminoAcid next)
    {
    	return Vector.getTorsionalAngle(current.getBackboneN(), current.getBackboneCA(), current.getBackboneC(), next.getBackboneN());
    }
    
    /**
     * Omega angle.
     *
     * @param current the current
     * @param next the next
     * @return the double
     */
    public static double omegaAngle(AminoAcid current, AminoAcid next)
    {
    	return Vector.getTorsionalAngle(current.getBackboneCA(), current.getBackboneN(), next.getBackboneC(), next.getBackboneCA());
    }
	
	
}
