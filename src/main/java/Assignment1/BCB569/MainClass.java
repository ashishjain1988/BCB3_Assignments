package Assignment1.BCB569;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Assignment2.BCB569.Atom;

/**
 * The Class MainClass.
 *
 * @author Ashish Jain
 */
public class MainClass 
{
	
	/** The atom names. */
	public static List<String> atomNames = Arrays.asList("N","CA","C");
    
    /**
     * The main method.
     *
     * @param args the arguments
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main( String[] args ) throws IOException
    {
    	if(args.length != 1)
    	{
    		System.err.println("Please enter PDB file Path.");
    		System.exit(0);
    	}
    	
    	if(!new File(args[0]).exists())
    	{
    		System.err.println("PDB File path is not correct");
    		System.exit(0);
    	}
    	
    	String rName, rChain, rSequence;
    	rName = rChain = rSequence = "";
        List<AminoAcid> aminoAcids = new ArrayList<AminoAcid>();
        Map<String, Atom> atoms = new HashMap<String, Atom>();
        Map<String, Vector> aminoAcidBackbone = new HashMap<String, Vector>();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        PrintWriter pw = new PrintWriter("BCB3HW1Results.txt");        
        String line = br.readLine();
        List<String> lineInPDBFile = new ArrayList<String>();
        while(line != null)
        {
        	lineInPDBFile.add(line);
        	line = br.readLine();
        }
        br.close();
        line = "";
        pw.println("Question 1:");
        pw.println("Residue Sequence\tAtom Name\tCoordinate X\tCoordinate Y\tCoordinate Z");
        for(int i=0;i<lineInPDBFile.size();i++)
        {
        	line = lineInPDBFile.get(i);
        	String recordType = line.substring(0, 4);
        	if(recordType.equals("ATOM"))
        	{
        		String recordTypeNext = lineInPDBFile.get(i+1).substring(0, 4);
        		String atomName = line.substring(12,16).trim();
        		String elementSymbol = line.substring(76,78).trim();
        		String resName = line.substring(17,20).trim();
        		String resChain = line.substring(21,22).trim();
        		String resSequence = line.substring(22,26).trim();
        		Integer atomicNumber = Integer.parseInt(line.substring(6,11).trim());
        		Vector coordinate = new Vector(Double.valueOf(line.substring(31,38).trim()), Double.valueOf(line.substring(38,46).trim()), Double.valueOf(line.substring(46,54).trim()));
        		
        		//Condition for first residue
        		if((rName.equals("") && rChain.equals("") && rSequence.equals("")))
        		{
        			rName = resName;
        			rChain = resChain;
        			rSequence = resSequence;
        		
        		}else if(!(resName.equals(rName) && resChain.equals(rChain) && resSequence.equals(rSequence)))
        		{
        			if(aminoAcidBackbone.size() == 3)
        			{
        				//if(!aminoAcids.contains(new AminoAcid(rSequence, rChain, rName, aminoAcidBackbone.get("N"), aminoAcidBackbone.get("CA"), aminoAcidBackbone.get("C"))))
        				{
        					aminoAcids.add(new AminoAcid(rSequence, rChain, rName, elementSymbol,aminoAcidBackbone.get("N"), aminoAcidBackbone.get("CA"), aminoAcidBackbone.get("C"),atoms));
        					aminoAcidBackbone = new HashMap<String, Vector>();
        					atoms = new HashMap<String, Atom>();
        				}
        			}
        			rName = resName;
        			rChain = resChain;
        			rSequence = resSequence;
        		}
        		
        		//Question 1:
        		if(atomNames.contains(atomName))
        		{
        			pw.println(resSequence+"\t"+atomName+"\t"+coordinate.getX()+"\t"+coordinate.getY()+"\t"+coordinate.getZ());
        			aminoAcidBackbone.put(atomName, coordinate);
        		}
        		
        		//if(!elementSymbol.equals("H"))
        		//{
        			atoms.put(atomName, new Atom(coordinate,null,atomicNumber));
        		//}
        		
        		//Store last Residue
        		if(!recordTypeNext.equals("ATOM"))
        		{
        			aminoAcids.add(new AminoAcid(rSequence, rChain, rName,elementSymbol,aminoAcidBackbone.get("N"), aminoAcidBackbone.get("CA"), aminoAcidBackbone.get("C"),atoms));
        		}
        	}
        }
        //System.out.println(aminoAcids.size());
        pw.println("Question 2:");
        CalculateMeanAndSDofAngles(aminoAcids, pw);
        AminoAcid aa29 = aminoAcids.get(28);
        AminoAcid aa30 = aminoAcids.get(29);
        AminoAcid aa31 = aminoAcids.get(30);
        //Question 3:
        pw.println("Question 3:");
        pw.println("The Phi, Psi and Omega angles of 30th Residue is "+AminoAcid.phiAngle(aa29, aa30)+","+AminoAcid.psiAngle(aa30, aa31)+","+AminoAcid.omegaAngle(aa30, aa29));
        //Vector rot = aa30.getBackboneCA().crossProduct(aa30.getBackboneC());
        //Vector id = new Vector(rot.getX()/(aa30.getBackboneC().length()*aa30.getBackboneCA().length()*Math.sin(Math.toRadians(Vector.angle(aa30.getBackboneCA(), aa30.getBackboneC())))), rot.getY()/rot.length()*Math.sin(Math.toRadians(Vector.angle(aa30.getBackboneCA(), aa30.getBackboneC()))), rot.getZ()/rot.length()*Math.sin(Math.toRadians(Vector.angle(aa30.getBackboneCA(), aa30.getBackboneC()))));
        //System.out.println(id);
        //System.out.println(aa29.getBackboneCA().crossProduct(aa29.getBackboneN()).divide(aa29.getBackboneCA().crossProduct(aa29.getBackboneN()).length()));
        PrintWriter pw1 = new PrintWriter("2GB1-new.pdb");
        List<AminoAcid> rotatedAminoAcids = rotation(aminoAcids, Math.toRadians(AminoAcid.phiAngle(aa29, aa30)), Math.toRadians(AminoAcid.psiAngle(aa30, aa31)));
        for(int i=0;i<lineInPDBFile.size();i++)
        {
        	line = lineInPDBFile.get(i);
        	String recordType = line.substring(0, 4);
        	if(recordType.equals("ATOM"))
        	{
        		String atomName = line.substring(12,16).trim();
        		String resSequence = line.substring(22,26).trim();
        		AminoAcid aminoAcid =rotatedAminoAcids.get(Integer.parseInt(resSequence)-1);
        		Vector vector = aminoAcid.getAtoms().get(atomName).getPosition();
        		line = line.replace(line.substring(31,38).trim(), String.format( "%.3f", vector.getX()));
        		line = line.replace(line.substring(38,46).trim(), String.format( "%.3f", vector.getY()));
        		line = line.replace(line.substring(46,54).trim(), String.format( "%.3f", vector.getZ()));
        	}
        	pw1.println(line);
        }
        pw1.close();
        pw.println("Question 4b:");
        List<Object> result = minimumDistance(rotatedAminoAcids);
        pw.println("The minimum distance is "+result.get(0)+" and the pair of atoms are "+result.get(1));
        pw.println("Question 5:");
        calculateSideChainTorsionAngles(aminoAcids, pw);
       
        pw.close();
    }
    
    /**
     * Calculate mean and S dof angles.
     *
     * @param aminoAcids the amino acids
     * @param pw the pw
     */
    //Question 2:
    public static void CalculateMeanAndSDofAngles(List<AminoAcid> aminoAcids, PrintWriter pw)
    {
    	List<Double> bondLengthsNCA = new ArrayList<Double>();
    	List<Double> bondLengthsCAC = new ArrayList<Double>();
    	List<Double> bondLengthsCN = new ArrayList<Double>();
    	
    	List<Double> bondAnglesNCAC = new ArrayList<Double>();
    	List<Double> bondAnglesCACN = new ArrayList<Double>();
    	List<Double> bondAnglesCNCA = new ArrayList<Double>();
    	
    	List<Double> distanceCA = new ArrayList<Double>();
    	
    	for(int i=0;i<aminoAcids.size()-1;i++)
    	{
    		AminoAcid aa = aminoAcids.get(i);
    		AminoAcid aa_next = aminoAcids.get(i+1);
    		
    		bondLengthsCAC.add(aa.getBackboneCA().distance(aa.getBackboneC()));
    		bondLengthsCN.add(aa.getBackboneC().distance(aa_next.getBackboneN()));
    		bondLengthsNCA.add(aa.getBackboneN().distance(aa.getBackboneCA()));
    		
    		bondAnglesNCAC.add(Vector.angle(aa.getBackboneN().subtract(aa.getBackboneCA()),aa.getBackboneCA().subtract(aa.getBackboneC())));
    		bondAnglesCACN.add(Vector.angle(aa.getBackboneCA().subtract(aa.getBackboneC()), aa.getBackboneC().subtract(aa_next.getBackboneN())));
    		bondAnglesCNCA.add(Vector.angle(aa.getBackboneC().subtract(aa_next.getBackboneN()), aa_next.getBackboneN().subtract(aa_next.getBackboneCA())));
    		
    		distanceCA.add(aa.getBackboneCA().distance(aa_next.getBackboneCA()));
    	}
    	
    	AminoAcid aa = aminoAcids.get(aminoAcids.size()-1);
    	Double bondLengthNCA = aa.getBackboneN().distance(aa.getBackboneCA());
		Double bondLengthCAC = aa.getBackboneCA().distance(aa.getBackboneC());
		bondLengthsCAC.add(bondLengthCAC);
		bondLengthsNCA.add(bondLengthNCA);
		Double bondAngleNCAC = Vector.angle(aa.getBackboneN().subtract(aa.getBackboneCA()), aa.getBackboneCA().subtract(aa.getBackboneC()));
		bondAnglesNCAC.add(bondAngleNCAC);
		
    	pw.println("The Mean of Bond Lengths Ni-CAi, CAi-Ci, Ci-N(i+1) "+Mean(bondLengthsNCA) +","+Mean(bondLengthsCAC)+","+Mean(bondLengthsCN));
    	pw.println("The Standard Deviation of Bond Lengths Ni-CAi, CAi-Ci, Ci-N(i+1) "+SDV(bondLengthsNCA) +","+SDV(bondLengthsCAC)+","+SDV(bondLengthsCN));
    	pw.println("The Mean of Bond Angles Ni-CAi-Ci, CAi-Ci-N(i+1), Ci-N(i+1)-CA(i+1) "+Mean(bondAnglesNCAC) +","+Mean(bondAnglesCACN)+","+Mean(bondAnglesCNCA));
    	pw.println("The Standard Deviation of Bond Angles Ni-CAi-Ci, CAi-Ci-N(i+1), Ci-N(i+1)-CA(i+1) "+SDV(bondAnglesNCAC) +","+SDV(bondAnglesCACN)+","+SDV(bondAnglesCNCA));
    	pw.println("The Mean of distance between CAi-CA(i+1) "+Mean(distanceCA));
    	pw.println("The Standard Deviation of of distance between CAi-CA(i+1) "+SDV(distanceCA));
    }
    
    /**
     * Rotation.
     *
     * @param aminoAcids the amino acids
     * @param PhiAngle the phi angle
     * @param PsiAngle the psi angle
     * @return the list
     */
    //Question 4a:
    public static List<AminoAcid> rotation(List<AminoAcid> aminoAcids,Double PhiAngle, Double PsiAngle)
    {
    	List<AminoAcid> newAminoAcids = new ArrayList<AminoAcid>();
    	//Double [][] newRotationMatrix = {{Math.cos(-PsiAngle),Math.sin(-PsiAngle),0d},{-Math.sin(-PsiAngle)*Math.cos(-PhiAngle),Math.cos(-PsiAngle)*Math.cos(-PhiAngle),-Math.sin(-PhiAngle)},{-Math.sin(-PsiAngle)*Math.sin(-PhiAngle),Math.sin(-PsiAngle)*Math.cos(-PhiAngle),Math.cos(-PhiAngle)}};
    	Double [] [] xRotationMatrix = {{1d,0d,0d},{0d,Math.cos(-PhiAngle),-Math.sin(-PhiAngle)},{0d,Math.sin(-PhiAngle),Math.cos(-PhiAngle)}};
    	//Double [] [] yRotationMatrix = {{Math.cos(-PhiAngle),0d,Math.sin(-PhiAngle)},{0d,1d,0d},{-Math.sin(-PhiAngle),0d,Math.cos(-PhiAngle)}};
    	//Double [] [] zRotationMatrix = {{Math.cos(-PhiAngle),-Math.sin(-PhiAngle),0d},{Math.sin(-PhiAngle),Math.cos(-PhiAngle),0d},{0d,0d,1d}};
    	
    	for(int i=0;i<30;i++)
    	{
    		AminoAcid aa = aminoAcids.get(i);
    		Map<String, Atom> atoms = aa.getAtoms();
    		Map<String, Atom> newAtoms = new HashMap<String, Atom>();
    		for(Entry<String, Atom> atom : atoms.entrySet())
    		{
    			String atomName = atom.getKey();
    			Vector atomVector = atom.getValue().getPosition();
    			Vector afterRotation = Vector.multiplyVectorMatrix(xRotationMatrix,atomVector.toArray());
    			//afterRotation = Vector.multiplyVectorMatrix(yRotationMatrix, afterRotation.toArray());
    			//afterRotation = Vector.multiplyVectorMatrix(zRotationMatrix, afterRotation.toArray());
    			newAtoms.put(atomName, new Atom(afterRotation,null,atom.getValue().getAtomicNumber()));
    		}
    		AminoAcid newAA = aa;
    		newAA.setAtoms(newAtoms);
    		newAminoAcids.add(newAA);
    	}
    	
    	/*AminoAcid aa1 = aminoAcids.get(29);
		Map<String, Vector> atoms1 = aa1.getAtoms();
		Map<String, Vector> newAtoms1 = new HashMap<String, Vector>();
		for(Entry<String, Vector> atom : atoms1.entrySet())
		{
			String atomName = atom.getKey();
			Vector atomVector = atom.getValue();
			if(atomNames.contains(atomName))
			{
				Vector afterRotation = Vector.multiplyVectorMatrix(xRotationMatrix,atomVector.toArray());
				//afterRotation = Vector.multiplyVectorMatrix(yRotationMatrix, afterRotation.toArray());
				afterRotation = Vector.multiplyVectorMatrix(zRotationMatrix1, afterRotation.toArray());
				newAtoms1.put(atomName, afterRotation);
			}else
			{
				newAtoms1.put(atomName, atomVector);
			}
		}
		AminoAcid newAA1 = aa1;
		newAA1.setAtoms(newAtoms1);
		newAminoAcids.add(newAA1);*/
    	//newAminoAcids.add(aminoAcids.get(29));
    	
    	//Double [] [] xRotationMatrix1 = {{1d,0d,0d},{0d,Math.cos(-PsiAngle),-Math.sin(-PsiAngle)},{0d,Math.sin(-PsiAngle),Math.cos(-PsiAngle)}};
    	//Double [] [] yRotationMatrix1 = {{Math.cos(-PsiAngle),0d,Math.sin(-PsiAngle)},{0d,1d,0d},{-Math.sin(-PsiAngle),0d,Math.cos(-PsiAngle)}};
    	Double [] [] zRotationMatrix1 = {{Math.cos(-PsiAngle),-Math.sin(-PsiAngle),0d},{Math.sin(-PsiAngle),Math.cos(-PsiAngle),0d},{0d,0d,1d}};
    	
    	for(int i=30;i<aminoAcids.size();i++)
    	{
    		AminoAcid aa = aminoAcids.get(i);
    		Map<String, Atom> atoms = aa.getAtoms();
    		Map<String, Atom> newAtoms = new HashMap<String, Atom>();
    		for(Entry<String, Atom> atom : atoms.entrySet())
    		{
    			String atomName = atom.getKey();
    			Vector atomVector = atom.getValue().getPosition();
    			Vector afterRotation = Vector.multiplyVectorMatrix(zRotationMatrix1, atomVector.toArray());
    			//afterRotation = Vector.multiplyVectorMatrix(yRotationMatrix1, afterRotation.toArray());
    			//afterRotation = Vector.multiplyVectorMatrix(zRotationMatrix1, afterRotation.toArray());
    			newAtoms.put(atomName, new Atom(afterRotation, null, atom.getValue().getAtomicNumber()));
    		}
    		AminoAcid newAA = aa;
    		newAA.setAtoms(newAtoms);
    		newAminoAcids.add(newAA);
    	}
    	return newAminoAcids;
    }
    
    /**
     * Minimum distance.
     *
     * @param rotatedAminoAcids the rotated amino acids
     * @return the list
     */
    //Question 4b:
    public static List<Object> minimumDistance(List<AminoAcid> rotatedAminoAcids)
    {
    	//Map<String, Double> atomsDistances = new HashMap<String, Double>();
    	Double distance = 1000d;
    	String atomsName = "";
    	List<Object> result;
    	List<Object> finalResult =  new ArrayList<Object>();
    	for(int i=0;i<rotatedAminoAcids.size()-1;i++)
    	{
    		//Calculate Distance between each atom in a residue
    		AminoAcid aa1 = rotatedAminoAcids.get(i);
    		result = AminoAcid.getMinimumDistanceBetweenAtomsOfResidues(aa1, aa1, true);
    		if(distance > (Double)result.get(0))
    		{
    			distance = (Double)result.get(0);
    			atomsName = (String)result.get(1);
    		}
    		for(int j=i+1;j<rotatedAminoAcids.size();j++)
    		{
    			//Calculate Distance between atoms from other residues
    			AminoAcid aa2 = rotatedAminoAcids.get(j);
    			result = AminoAcid.getMinimumDistanceBetweenAtomsOfResidues(aa1, aa2, false);
        		if(distance > (Double)result.get(0))
        		{
        			distance = (Double)result.get(0);
        			atomsName = (String)result.get(1);
        		}
    		}
    	}
    	//Calculate distance between the atoms of last residue. 
    	AminoAcid aa1 = rotatedAminoAcids.get(rotatedAminoAcids.size()-1);
    	result = AminoAcid.getMinimumDistanceBetweenAtomsOfResidues(aa1, aa1, true);
		if(distance > (Double)result.get(0))
		{
			distance = (Double)result.get(0);
			atomsName = (String)result.get(1);
		}
    	
		finalResult.add(distance);
    	finalResult.add(atomsName);
    	return finalResult;
    }
    
    
    
    /**
     * Calculate side chain torsion angles.
     *
     * @param aminoAcids the amino acids
     * @param pw the pw
     */
    //Question 5:
    public static void calculateSideChainTorsionAngles(List<AminoAcid> aminoAcids,PrintWriter pw)
    {
    	pw.println("Residue Name\tResidue Chain\tResidue Sequence\tSide Chain Angle\tValue");
    	for(AminoAcid aminoAcid :aminoAcids)
    	{
    		String residueName = aminoAcid.getrName();
    		//System.out.println(residueName);
    		Map<String, Atom> atoms = aminoAcid.getAtoms();
    		for(Entry<String, Map<String, List<String>>> chi_atom_entry: TorsonialAnglesList.chi_atoms.entrySet())
    		{
    			String angleName = chi_atom_entry.getKey();
    			if(chi_atom_entry.getValue().get(residueName) != null)
    			{
    				List<String> angleAtoms = chi_atom_entry.getValue().get(residueName);
    				//System.out.println(angleName+", "+residueName);
    				//System.out.println(atoms.get(angleAtoms.get(0))+","+atoms.get(angleAtoms.get(1))+","+atoms.get(angleAtoms.get(2))+","+atoms.get(angleAtoms.get(3)));
    				Double torsionalAngle = Vector.getTorsionalAngle(atoms.get(angleAtoms.get(0)).getPosition(), atoms.get(angleAtoms.get(1)).getPosition(), atoms.get(angleAtoms.get(2)).getPosition(), atoms.get(angleAtoms.get(3)).getPosition());
    				pw.println(residueName+"\t"+aminoAcid.getrChain()+"\t"+aminoAcid.getrSequence()+"\t"+angleName+"\t"+torsionalAngle);
    			}
    		}
    	}
    }
    
    /**
     * Mean.
     *
     * @param list the list
     * @return the double
     */
    public static Double Mean(List<Double> list)
    {
    	Double sum = 0d;
    	for(Double d:list)
    	{
    		sum +=d;
    	}
    	return sum/list.size();
    }
    
    /**
     * Sdv.
     *
     * @param list the list
     * @return the double
     */
    public static Double SDV(List<Double> list)
    {
        double num=0.0;
        double deno = list.size() - 1;
        Double mean = Mean(list);
        for (Double d:list){
        	num+=Math.pow((d - mean),2);
        }
        return Math.sqrt(num/deno);
    }
    
    
}
