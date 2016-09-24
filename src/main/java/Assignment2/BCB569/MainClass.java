package Assignment2.BCB569;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Assignment1.BCB569.AminoAcid;
import Assignment1.BCB569.Vector;

public class MainClass {

	public static Map<String,Double> atomradii;
	static {
		atomradii = new HashMap<String, Double>();
		atomradii.put("H", 1.2);
		atomradii.put("N", 1.55);
		atomradii.put("C", 1.70);
		atomradii.put("O", 1.52);
		atomradii.put("S", 1.80);
		
	}
	
	public static void main(String[] args) throws IOException{
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
        PrintWriter pw = new PrintWriter("BCB3HW2Results.txt");        
        String line = br.readLine();
        List<String> lineInPDBFile = new ArrayList<String>();
        List<Atom> atomByPosition = new ArrayList<Atom>();
        while(line != null)
        {
        	lineInPDBFile.add(line);
        	line = br.readLine();
        }
        br.close();
        line = "";
        //pw.println("Question 1:");
        //pw.println("Residue Sequence\tAtom Name\tCoordinate X\tCoordinate Y\tCoordinate Z");
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
        		if(Assignment1.BCB569.MainClass.atomNames.contains(atomName))
        		{
        			aminoAcidBackbone.put(atomName, coordinate);
        		}
        		
        		//if(!elementSymbol.equals("H"))
        		//{
        			atoms.put(atomName, new Atom(coordinate,null,atomicNumber));
        			atomByPosition.add(new Atom(atomName,coordinate,atomradii.get(elementSymbol),atomicNumber,null));
        		//}
        		
        		//Store last Residue
        		if(!recordTypeNext.equals("ATOM"))
        		{
        			aminoAcids.add(new AminoAcid(rSequence, rChain, rName,elementSymbol,aminoAcidBackbone.get("N"), aminoAcidBackbone.get("CA"), aminoAcidBackbone.get("C"),atoms));
        		}
        	}
        }
        atomByPosition = CalculateSolventAccessibleArea(atomByPosition, 1.4, 500,null);
        Double proteinArea = 0d;
        pw.println("Area of atoms of the protein");
        pw.println("Atom Number\tAtom Name\tArea");
        for(Atom atom : atomByPosition)
        {
        	pw.println(atom.getAtomicNumber()+"\t"+atom.getAtomName()+"\t"+atom.getArea());
        	proteinArea +=atom.getArea();
        }
        pw.println("Area of residues of the protein");
        pw.println("Residue Number\tResidue Name\tArea");
        for(AminoAcid aa:aminoAcids)
        {
        	Double aaArea = 0d;
        	for(Entry<String, Atom> a:aa.getAtoms().entrySet())
        	{
        		aaArea +=atomByPosition.get(a.getValue().getAtomicNumber()-1).getArea();
        	}
        	pw.println(aa.getrSequence()+"\t"+aa.getrName()+"\t"+aaArea);
        }
        pw.println("The total area of the protein is "+proteinArea);
        pw.close();
        
    }
	
	public static List<Atom> CalculateSolventAccessibleArea(List<Atom> atomByPosition,Double probeRadii,Integer numberOfPoints, PrintWriter pw)
	{
		//Map<Integer, Double> areaOfAtoms
		List<Vector> spherePoints = generateSpherePoints(numberOfPoints);
		Double constant = 4.0 * Math.PI / spherePoints.size();
		for(int i=0;i<atomByPosition.size();i++)
		{
			//System.out.println(i);
			Atom atomI = atomByPosition.get(i);
			//String atomName = atom
			List<Integer> neighborAtomsPositions = findNeighborIndices(atomByPosition,probeRadii, i);
			/*if(i ==0)
			{
				System.out.println(neighborAtomsPositions.toString());
			}*/
			//n_neighbor = len(neighbor_indices)
			Integer j_closest_neighbor = 0;
			Double radius = probeRadii + atomI.getAtomicRadii();
			Integer n_accessible_point = 0;
			for(Vector point : spherePoints)
			{
			    boolean is_accessible = true;
			    Vector test_point = point.multiply(radius).add(atomI.getPosition());
			    //cycled_indices = range(j_closest_neighbor, n_neighbor)
			    //        cycled_indices.extend(range(j_closest_neighbor))
			    List<Integer> cycledIndex = range(j_closest_neighbor, neighborAtomsPositions.size());
			    cycledIndex.addAll(range(0,j_closest_neighbor));
			    if(i==0)
			    {
			    	//System.out.println(cycledIndex);
			    }
			    for(Integer j : cycledIndex)
			    {
			    	//System.out.println(neighborAtomsPositions.get(j));
			    	Atom atom2 = atomByPosition.get(neighborAtomsPositions.get(j));
			    	Double r = atom2.getAtomicRadii() + probeRadii;
			    	Double diff = atom2.getPosition().subtract(test_point).length();
			    	if(Math.pow(diff, 2) < Math.pow(r, 2))
			    	{
			    		j_closest_neighbor = j;
			    		is_accessible = false;
			    		break;
			    	}
			    	
			    }
			    if(is_accessible)
			    {
			    	n_accessible_point += 1;
			    }
			}
			Double area = constant * n_accessible_point * Math.pow(radius, 2);
			atomI.setArea(area);
			//atomByPosition.add(i, atomI);
			//System.out.println(atomI.getAtomicNumber()+"\t"+area);
		}
		return atomByPosition;
	}
	
	
	public static List<Vector> generateSpherePoints(Integer numberOfPoints)
	{
		List<Vector> points = new ArrayList<Vector>();
		Double u = Math.PI * (3 - Math.sqrt(5));
		Double v = 2d /numberOfPoints;
		for(int k = 0;k<numberOfPoints;k++)
		{
			Double y = k * v - 1 + (v / 2);
			Double r = Math.sqrt(1 - y*y);
			Double phi = k * u;
			points.add(new Vector(Math.cos(phi)*r, y, Math.sin(phi)*r));
		}
		return points;
	}
	
	public static List<Integer> findNeighborIndices(List<Atom> atomByPosition,Double probeRadii,Integer atomPosition)
	{
		List<Integer> neighborAtomsPositions = new ArrayList<Integer>();
		//System.out.println(atomPosition);
		Atom atom = atomByPosition.get(atomPosition);
		//System.out.println(atom.getAtomicRadii());
		Double radius = atom.getAtomicRadii() + probeRadii + probeRadii;
		for(int i=0;i<atomByPosition.size();i++)
		{
			Atom atom1 = atomByPosition.get(i);
			if(atom1.getAtomicNumber() != atom.getAtomicNumber())
			{
				Double dist = atom1.getPosition().distance(atom.getPosition());
				if(dist < radius+atom1.getAtomicRadii())
				{
					neighborAtomsPositions.add(i);
				}
			}

		}
		return neighborAtomsPositions;

	}
	
	public static List<Integer> range(Integer start, Integer stop)
	{
		List<Integer> result = new ArrayList<Integer>();

	   for(int i=0;i<stop-start;i++)
	      result.add(start+i);

	   return result;
	}
	
}
