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

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class MainClass 
{
	public static List<String> atomNames = Arrays.asList("N","CA","C");
    public static void main( String[] args ) throws IOException
    {
    	String rName,rChain,rSequence = "";
        List<AminoAcid> aminoAcids = new ArrayList<AminoAcid>();
        Map<String, Vector3D> aminoAcidsAtoms = new HashMap<String, Vector3D>();
    	
        BufferedReader br = new BufferedReader(new FileReader(""));
        PrintWriter pw = new PrintWriter("");        
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
        			//String
        		}
        	}
        	line = br.readLine();
        }
        
        br.close();
        pw.close();
    }
    
    
    public static Double getTorsionalAngle(Vector3D p0,Vector3D p1,Vector3D p2,Vector3D p3)
    {
    	Vector3D v01 = p0.subtract(p1);
    	Vector3D v32 = p2.subtract(p3);
    	Vector3D v12 = p1.subtract(p2);
    	Vector3D v0 = v12.crossProduct(v01);
		Vector3D v3 = v12.crossProduct(v32);
		Double a = Vector3D.angle(v0,v3);
		if(Vector3D.dotProduct(v0.crossProduct(v3), v12) > 0)
		{
			a = -a;
		}
		return Math.toDegrees(a);
    }
}
