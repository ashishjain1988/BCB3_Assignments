package Assignment1.BCB569;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Ashish Jain
 *
 */
public class MainClass 
{
	public static List<String> atomNames = Arrays.asList("N","CA","C");
    public static void main( String[] args ) throws IOException
    {
    	String rName, rChain, rSequence;
    	rName = rChain = rSequence = "";
        List<AminoAcid> aminoAcids = new ArrayList<AminoAcid>();
        Map<String, Vector> atoms = new HashMap<String, Vector>();
        Map<String, Vector> aminoAcidBackbone = new HashMap<String, Vector>();
        BufferedReader br = new BufferedReader(new FileReader("/home/jain/BitBucket_Code/2GB1.pdb"));
        PrintWriter pw = new PrintWriter("output.txt");        
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
        		String resName = line.substring(17,20);
        		String resChain = line.substring(21,22);
        		String resSequence = line.substring(22,26);
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
        					aminoAcids.add(new AminoAcid(rSequence, rChain, rName, aminoAcidBackbone.get("N"), aminoAcidBackbone.get("CA"), aminoAcidBackbone.get("C"),atoms));
        					aminoAcidBackbone = new HashMap<String, Vector>();
        					atoms = new HashMap<String, Vector>();
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
        			atoms.put(atomName, coordinate);
        		//}
        		
        		//Store last Residue
        		if(!recordTypeNext.equals("ATOM"))
        		{
        			aminoAcids.add(new AminoAcid(rSequence, rChain, rName, aminoAcidBackbone.get("N"), aminoAcidBackbone.get("CA"), aminoAcidBackbone.get("C"),atoms));
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
        pw.println("The Phi, Psi and Omega angles of 30th Residue is "+phiAngle(aa29, aa30)+","+psiAngle(aa30, aa31)+","+omegaAngle(aa30, aa29));
        pw.println("Question 5:");
        calculateSideChainTorsionAngles(aminoAcids, pw);
       
        pw.close();
    }
    
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
    
    public static void multiplyVectorMatrix(Double [][] rotationMatrix,Vector v)
    {
    	//Arrays.stream(rotationMatrix).mapToDouble(row ->IntStream.range(0, row.length).mapToDouble(col -> row[col] * vector[col]).sum()).toArray();
    }
    public static void rotation(List<AminoAcid> aminoAcids,PrintWriter pw)
    {
    	
    }
    
    //Question 5:
    public static void calculateSideChainTorsionAngles(List<AminoAcid> aminoAcids,PrintWriter pw)
    {
    	pw.println("Residue Name\tResidue Chain\tResidue Sequence\tSide Chain Angle\tValue");
    	for(AminoAcid aminoAcid :aminoAcids)
    	{
    		String residueName = aminoAcid.getrName();
    		//System.out.println(residueName);
    		Map<String, Vector> atoms = aminoAcid.getAtoms();
    		for(Entry<String, Map<String, List<String>>> chi_atom_entry: TorsonialAnglesList.chi_atoms.entrySet())
    		{
    			String angleName = chi_atom_entry.getKey();
    			if(chi_atom_entry.getValue().get(residueName) != null)
    			{
    				List<String> angleAtoms = chi_atom_entry.getValue().get(residueName);
    				//System.out.println(angleName+", "+residueName);
    				//System.out.println(atoms.get(angleAtoms.get(0))+","+atoms.get(angleAtoms.get(1))+","+atoms.get(angleAtoms.get(2))+","+atoms.get(angleAtoms.get(3)));
    				Double torsionalAngle = getTorsionalAngle(atoms.get(angleAtoms.get(0)), atoms.get(angleAtoms.get(1)), atoms.get(angleAtoms.get(2)), atoms.get(angleAtoms.get(3)));
    				pw.println(residueName+"\t"+aminoAcid.getrChain()+"\t"+aminoAcid.getrSequence()+"\t"+angleName+"\t"+torsionalAngle);
    			}
    		}
    	}
    }
    
    public static Double Mean(List<Double> list)
    {
    	Double sum = 0d;
    	for(Double d:list)
    	{
    		sum +=d;
    	}
    	return sum/list.size();
    }
    
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
    
    public static Double getTorsionalAngle(Vector p0,Vector p1,Vector p2,Vector p3)
    {
    	Vector v01 = p0.subtract(p1);
    	Vector v32 = p3.subtract(p2);
    	Vector v12 = p1.subtract(p2);
    	Vector v0 = v12.crossProduct(v01);
		Vector v3 = v12.crossProduct(v32);
		Double a = Vector.angle(v0,v3);
		if(v0.crossProduct(v3).dotProduct(v12) > 0)
		{
			a = -a;
		}
		return a;
    }
    
    
    public static double phiAngle(AminoAcid prev, AminoAcid current)
    {
    	return getTorsionalAngle(prev.getBackboneC(), current.getBackboneN(), current.getBackboneCA(), current.getBackboneC());
    }
    
    public static double psiAngle(AminoAcid current, AminoAcid next)
    {
    	return getTorsionalAngle(current.getBackboneN(), current.getBackboneCA(), current.getBackboneC(), next.getBackboneN());
    }
    
    public static double omegaAngle(AminoAcid current, AminoAcid next)
    {
    	return getTorsionalAngle(current.getBackboneCA(), current.getBackboneN(), next.getBackboneC(), next.getBackboneCA());
    }
    
}
