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

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.io.PDBFileReader;

public class MainClass 
{
	public static List<String> atomNames = Arrays.asList("N","CA","C");
    public static void main( String[] args ) throws IOException
    {
    	String rName, rChain, rSequence;
    	rName = rChain = rSequence = "";
        List<AminoAcid> aminoAcids = new ArrayList<AminoAcid>();
        Map<String, Vector> aminoAcidBackbone = new HashMap<String, Vector>();
    	PDBFileReader pdbFileReader = new PDBFileReader();
    	//Structure structure = pdbFileReader.getStructure("/home/jain/BitBucket_Code/python-scripts/2GB1.pdb");
        BufferedReader br = new BufferedReader(new FileReader("/home/jain/BitBucket_Code/python-scripts/2GB1.pdb"));
        PrintWriter pw = new PrintWriter("output.txt");        
        String line = br.readLine();
        while(line != null)
        {
        	String recordType = line.substring(0, 4);
        	if(recordType.equals("ATOM"))
        	{
        		String atomName = line.substring(12,16).trim();
        		if(atomNames.contains(atomName))
        		{
        			String resName = line.substring(17,20);
        			String resChain = line.substring(21,22);
        			String resSequence = line.substring(22,26);
        			Vector coordinate = new Vector(Double.valueOf(line.substring(31,38).trim()), Double.valueOf(line.substring(38,46).trim()), Double.valueOf(line.substring(46,54).trim()));
        			if(!(resName.equals(rName) && resChain.equals(rChain) && resSequence.equals(rSequence)))
        			{
        				rName = resName;
        				rChain = resChain;
        				rSequence = resSequence;
        			}
        			pw.println(resSequence+"\t"+atomName+"\t"+coordinate.getX()+"\t"+coordinate.getY()+"\t"+coordinate.getZ());
        			aminoAcidBackbone.put(atomName, coordinate);
        			if(aminoAcidBackbone.size() == 3)
        			{
        				if(!aminoAcids.contains(new AminoAcid(rSequence, rChain, rName, aminoAcidBackbone.get("N"), aminoAcidBackbone.get("CA"), aminoAcidBackbone.get("C"))))
        				{
        					aminoAcids.add(new AminoAcid(rSequence, rChain, rName, aminoAcidBackbone.get("N"), aminoAcidBackbone.get("CA"), aminoAcidBackbone.get("C")));
        					aminoAcidBackbone = new HashMap<String, Vector>();
        				}
        			}
        		}
        	}
        	line = br.readLine();
        }
        CalculateMeanAndSDofAngles(aminoAcids, pw);
        AminoAcid aa29 = aminoAcids.get(28);
        AminoAcid aa30 = aminoAcids.get(29);
        AminoAcid aa31 = aminoAcids.get(30);
        pw.println("The Phi, Psi and Omega angles of 30th Residue is "+phiAngle(aa29, aa30)+","+psiAngle(aa30, aa31)+","+omegaAngle(aa30, aa29));
        
        br.close();
        pw.close();
    }
    
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
    		Double bondLengthNCA = aa.getBackboneN().distance(aa.getBackboneCA());
    		Double bondLengthCAC = aa.getBackboneCA().distance(aa.getBackboneC());
    		Double bondLengthCN = aa.getBackboneC().distance(aa_next.getBackboneN());
    		
    		bondLengthsCAC.add(bondLengthCAC);
    		bondLengthsCN.add(bondLengthCN);
    		bondLengthsNCA.add(bondLengthNCA);
    		
    		Double bondAngleNCAC = Vector.angle(aa.getBackboneN().subtract(aa.getBackboneCA()), aa.getBackboneCA().subtract(aa.getBackboneC()));
    		Double bondAngleCACN = Vector.angle(aa.getBackboneCA().subtract(aa.getBackboneC()), aa.getBackboneC().subtract(aa_next.getBackboneN()));
    		Double bondAngleCNCA = Vector.angle(aa.getBackboneC().subtract(aa_next.getBackboneN()), aa_next.getBackboneN().subtract(aa_next.getBackboneCA()));
    		
    		bondAnglesNCAC.add(bondAngleNCAC);
    		bondAnglesCACN.add(bondAngleCACN);
    		bondAnglesCNCA.add(bondAngleCNCA);
    		
    		distanceCA.add(aa.getBackboneCA().distance(aa_next.getBackboneCA()));
    	}
    	
    	AminoAcid aa = aminoAcids.get(aminoAcids.size()-1);
    	Double bondLengthNCA = aa.getBackboneN().distance(aa.getBackboneCA());
		Double bondLengthCAC = aa.getBackboneCA().distance(aa.getBackboneC());
		bondLengthsCAC.add(bondLengthCAC);
		bondLengthsNCA.add(bondLengthNCA);
		Double bondAngleNCAC = Vector.angle(aa.getBackboneN().subtract(aa.getBackboneCA()), aa.getBackboneCA().subtract(aa.getBackboneC()));
		bondAnglesNCAC.add(bondAngleNCAC);
		
		Double sum = 0d;
    	for(Double d:bondLengthsNCA)
    	{
    		sum +=d;
    	}
    	pw.println("The Mean of Bond Lengths Ni-CAi, CAi-Ci, Ci-N(i+1) "+Mean(bondLengthsNCA) +","+Mean(bondLengthsCAC)+","+Mean(bondLengthsCN));
    	pw.println("The Standard Deviation of Bond Lengths Ni-CAi, CAi-Ci, Ci-N(i+1) "+SDV(bondLengthsNCA) +","+SDV(bondLengthsCAC)+","+SDV(bondLengthsCN));
    	pw.println("The Mean of Bond Angles Ni-CAi-Ci, CAi-Ci-N(i+1), Ci-N(i+1)-CA(i+1) "+Mean(bondAnglesNCAC) +","+Mean(bondAnglesCACN)+","+Mean(bondAnglesCNCA));
    	pw.println("The Standard Deviation of Bond Angles Ni-CAi-Ci, CAi-Ci-N(i+1), Ci-N(i+1)-CA(i+1) "+SDV(bondAnglesNCAC) +","+SDV(bondAnglesCACN)+","+SDV(bondAnglesCNCA));
    	pw.println("The Mean of distance between CAi-CA(i+1) "+Mean(distanceCA));
    	pw.println("The Standard Deviation of of distance between CAi-CA(i+1) "+SDV(distanceCA));
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
